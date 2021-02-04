package isti.message;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import isti.message.impl.red.JMadRed;
import isti.message.impl.red.WireAnalogInfo;
import isti.message.util.Service;

public class MessageMADRED {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MessageMADRED.class);

	String HEADER;
	String MAC;
	int TYPE = 0;
	int REVISION;
	short POSITION;
	String DESCRIPTION;
	long  LONGITUDE;
	long LATITUDE;
	short DIGITAL_INFO;
	byte[] WIRE_DIGITAL_INFO;
	byte[] WIRE_ANALOG_INFO;
	long   Timestamp;
	int   armamento;
	byte[] Dummy;
	WireAnalogInfo wire;
	int CRC;
	Date Datas;
	byte[] mess;

	MessageMADRED(byte[] message) {

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

		WIRE_DIGITAL_INFO = Arrays.copyOfRange(message, 41, 49);//8

		WIRE_ANALOG_INFO =  Arrays.copyOfRange(message, 49, 77);//28

		
		  long Times= (long)Service.bytesToFloat(Arrays.copyOfRange(message, 77, 81));//4
		   
		   Instant epoch = Instant.parse("2000-01-01T00:00:00.00Z");
		   Instant later = epoch.plusSeconds( Times ) ; 
		    Times = later.getEpochSecond();
		     Datas = Date.from(later);
		     Timestamp = Datas.getTime() ;

		   armamento = Service.byteToInt(Arrays.copyOfRange(message, 81, 82));//1

		byte[] dummy = Arrays.copyOfRange(message, 82, 90);//9
		log.info(dummy);
		byte [] bCRC = Arrays.copyOfRange(message,  message.length-2, message.length) ;
		var = Arrays.copyOfRange(message, 0, message.length-2);//2


		int Temperatura1 = Service.TwobytesToint(Arrays.copyOfRange(message, 49, 51));//2
		int Temperatura2 =  Service.TwobytesToint(Arrays.copyOfRange(message, 51, 53));//2
		int ValoreCorrenteCavo1 = Service.TwobytesToint(Arrays.copyOfRange(message, 53, 55));//2
		int ValoreCorrenteCavo2 = Service.TwobytesToint(Arrays.copyOfRange(message, 55, 57));//2
		int ValoreCorrenteCavo3 = Service.TwobytesToint(Arrays.copyOfRange(message, 57, 59));//2
		int ValoreCorrenteCavo4 =  Service.TwobytesToint(Arrays.copyOfRange(message, 59, 61));//2

		int ValoreCorrenteCavo5 = Service.TwobytesToint(Arrays.copyOfRange(message, 61, 63));//2
		int ValoreCorrenteCavo6 = Service.TwobytesToint(Arrays.copyOfRange(message, 63, 65));//2
		int ValoreCorrenteCavo7 = Service.TwobytesToint(Arrays.copyOfRange(message, 65, 67));//2
		int ValoreCorrenteCavo8 =  Service.TwobytesToint(Arrays.copyOfRange(message, 67, 69));//2

		int ValoreCorrenteCavo9 = Service.TwobytesToint(Arrays.copyOfRange(message, 69, 71));//2
		int ValoreCorrenteCavo10 = Service.TwobytesToint(Arrays.copyOfRange(message, 71, 73));//2
		int ValoreCorrenteCavo11 = Service.TwobytesToint(Arrays.copyOfRange(message, 73, 75));//2
		int ValoreCorrenteCavo12 =  Service.TwobytesToint(Arrays.copyOfRange(message, 75, 77));//2

		wire = new WireAnalogInfo(Temperatura1,  Temperatura2,  ValoreCorrenteCavo1,  ValoreCorrenteCavo2,
				ValoreCorrenteCavo3,  ValoreCorrenteCavo4,  ValoreCorrenteCavo5,  ValoreCorrenteCavo6,
				ValoreCorrenteCavo7,  ValoreCorrenteCavo8,  ValoreCorrenteCavo9,  ValoreCorrenteCavo10,
				ValoreCorrenteCavo11, ValoreCorrenteCavo12);

		/*
		 * 2	INT	Valore temperatura 1
2	INT	Valore temperatura 2
2	INT	Valore corrente cavo 1


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
		log.info(Timestamp);
		log.info(toString());


	}


	public JMadRed getMadRed() {


		JMadRed jmadred = new JMadRed(MAC,HEADER,
				TYPE,
				REVISION,
				new Short(POSITION).toString(),
				DESCRIPTION,
				new Long(LONGITUDE).toString(),
				new Long(LATITUDE).toString(),
				new Short(DIGITAL_INFO).toString(),
				String.valueOf(Service.bytesToLong(WIRE_DIGITAL_INFO)), Timestamp, armamento,
				Base64.getEncoder().encodeToString(mess),
				String.valueOf(CRC));

		jmadred.setWIRE_ANALOG_INFO(wire);
		return jmadred;
	}


}
