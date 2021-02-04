package isti.message;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import isti.message.impl.ill.AnalogInfo;
import isti.message.impl.ill.JMADILL;
import isti.message.util.Service;

public class MessageMADILL {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MessageMADILL.class);
	
	String HEADER;
	String MAC;
	int TYPE = 0;
	int REVISION;
	short POSITION;
	String DESCRIPTION;
	long  LONGITUDE;
	long LATITUDE;
	short DIGITAL_INFO;
	byte[] ANALOG_INFO;
	long Timestamp;
	byte[] Dummy;
	int CRC;
	Date Datas;
	byte[] mess;
	
	AnalogInfo dinfo = new AnalogInfo();
	
	MessageMADILL(byte[] message) {

		mess = message;
		
		HEADER = String.valueOf( (char)(message[0]));//1
		byte[] bMAC = Arrays.copyOfRange(message, 1, 7);//6
		MAC = Service.getMacString(bMAC);
		TYPE  = message[7]& 0xff;;//1
		REVISION  = message[8]& 0xff;;//1
		
		byte[] var = Arrays.copyOfRange(message, 9, 11);//2
		POSITION =  Service.bytesToShort(var); 
		try {
			DESCRIPTION =  new String(Arrays.copyOfRange(message, 11, 31), "UTF-8").replaceAll("\\u0000", "");//20
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bLONGITUDE =    Arrays.copyOfRange(message, 31, 35);//4
		LONGITUDE = Service.bytesToLong(bLONGITUDE)/10000;
		byte[]  bLATITUDE =    Arrays.copyOfRange(message, 35, 39);//4
		LATITUDE = Service.bytesToLong(bLATITUDE)/10000;
		
		var = Arrays.copyOfRange(message, 39, 41);//2
		DIGITAL_INFO = Service.bytesToShort(var); 
		
		ANALOG_INFO =  Arrays.copyOfRange(message, 41, 53);//12
		
		  long Times = (long)Service.bytesToFloat(Arrays.copyOfRange(message, 53, 57));//4
		
		   Instant epoch = Instant.parse("2000-01-01T00:00:00.00Z");
		   Instant later = epoch.plusSeconds( Times ) ; 
		    Timestamp = later.getEpochSecond();  
		    
		byte[] dummy = Arrays.copyOfRange(message, 57, 65);//8
		log.info(dummy);
		byte [] bCRC = Arrays.copyOfRange(message,  message.length-2, message.length) ;
		var = Arrays.copyOfRange(message, 0, message.length-2);//2
		
		
		int ComandoLampada = Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 0, 2));//2
		int PotenzaLampada =  Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 2, 4));//2
		int VitaLampada = Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 4, 6));//2
		int FattorePotenza = Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 6, 8));//2
		int TensioneLampada = Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 8, 10));//2
		int CorrenteLampada =  Service.TwobytesToint(Arrays.copyOfRange(ANALOG_INFO, 10, 12));//2
		
		dinfo = new AnalogInfo(ComandoLampada, PotenzaLampada, VitaLampada,FattorePotenza, TensioneLampada, CorrenteLampada);
		
		/*
		 * 2	INT	Comando lampada
2	INT	Potenza lampada
2	INT	Durata totale vita lampada
2	INT	Tensione lampada
2	INT	Corrente lampada
2	INT	SCORTA

		 */
		
		CRC = Service.CRC(var);
		byte[] recCRC = Service.intToBytes(CRC);
		if(Arrays.equals(recCRC, bCRC)){
			log.info("CRC OK");
		}else{
			log.info("CRC KO");
		}
		
		log.info(CRC);
 		log.info(DESCRIPTION);
 		log.info("DATA"+ Timestamp);
 		log.info(toString());
		
		
	}

	@Override
	public String toString() {
		return "HEADER: " + HEADER + "\\n, MAC: " + MAC + "\\n, TYPE: " + TYPE + "\\n, REVISION: " + REVISION
				+ "\\n, POSITION: " + POSITION + "\\n, DESCRIPTION: " + DESCRIPTION + "\\n, LONGITUDE: " + LONGITUDE
				+ "\\n, LATITUDE: " + LATITUDE + "\\n, DIGITAL_INFO: " + DIGITAL_INFO + "\\n, ANALOG_INFO: "
				+ Arrays.toString(ANALOG_INFO) + "\\n, Dummy: " + Arrays.toString(Dummy) + "\\n, CRC: " + CRC;
	}
	
	public JMADILL getMadILL() {
		
		JMADILL madill = new JMADILL(MAC,HEADER,
				TYPE,
				REVISION,
				new Short(POSITION).toString(),
				DESCRIPTION,
				new Long(LONGITUDE).toString(),
				new Long(LATITUDE).toString(),
				new Short(DIGITAL_INFO).toString(),
				dinfo , Datas,
				Base64.getEncoder().encodeToString(mess),
				String.valueOf(CRC));
		
		return madill;
	}
	
	
	
}
