package isti.message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import isti.message.impl.cmad.CMADAnalogInfo;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.L;
import isti.message.impl.ill.JMADILL;
import isti.message.impl.red.JMadRed;
import isti.message.util.Service;




public class MessageCMAD {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MessageCMAD.class);
	
	String CMAD_HEADER;
	String MAC_ADR;
	int CMAD_TYPE = 0;
	int CMAD_REVISION;
	short CMAD_POSITION;
	String CMAD_DESCRIPTION;
	long  CMAD_LONGITUDE;
	long CMAD_LATITUDE;
	short CMAD_DIGITAL_INFO;
	byte[] CMAD_ANALOG_INFO;
	byte[] CMAD_Dummy;
	int CMAD_CRC;
	long   Timestamp;
	int   armamento;
	
	Date Datas;
	
	CMADAnalogInfo cCMAD_ANALOG_INFO;
	byte[] mess;
	List<JMadRed> listred = new ArrayList<>();
	List<JMADILL> listill = new ArrayList<>();
	
	public MessageCMAD(byte[] message) {

		mess = message;
		
		CMAD_HEADER = String.valueOf( (char)(message[0]));//1
		byte[] CMAD_MAC = Arrays.copyOfRange(message, 1, 7);//6
		MAC_ADR = Service.getMacString(CMAD_MAC);
		CMAD_TYPE  = message[7]& 0xff;;//1
		CMAD_REVISION  = message[8]& 0xff;;//1
		
		byte[] var = Arrays.copyOfRange(message, 9, 11);//2
		CMAD_POSITION =  Service.bytesToShort(var); 
		try {
			CMAD_DESCRIPTION =  new String(Arrays.copyOfRange(message, 11, 31), "UTF-8").replaceAll("\\u0000", "");//20
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] LONGITUDE =    Arrays.copyOfRange(message, 31, 35);//4
		CMAD_LONGITUDE = Service.bytesToLong(LONGITUDE)/10000;
		byte[]  LATITUDE =    Arrays.copyOfRange(message, 35, 39);//4
		CMAD_LATITUDE = Service.bytesToLong(LATITUDE)/10000;
		
		var = Arrays.copyOfRange(message, 39, 41);//2
		CMAD_DIGITAL_INFO = Service.bytesToShort(var); 
		
		CMAD_ANALOG_INFO =  Arrays.copyOfRange(message, 41, 81);//20
		
		
		float TempEst = (float) Service.TwobytesToint(Arrays.copyOfRange(message, 41, 43))/10;//2
		int Lux =  Service.TwobytesToint(Arrays.copyOfRange(message, 43, 45));//2
		 float jj = Service.TwobytesToLong(Arrays.copyOfRange(message, 43, 45));
		
		//byte[] var2 = Arrays.copyOfRange(message, 45, 47);
		float TempSuolo = (float) Service.TwobytesToint(Arrays.copyOfRange(message, 45, 47))/10;//2
		float TensioneL1 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 47, 49))/10;//2
		float TensioneL2 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 49, 51))/10;//2
		float TensioneL3 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 51, 53))/10;//2
		
		byte[] k = Arrays.copyOfRange(message, 43, 45);
		byte[] k1 = {k[0],k[1],0,0};
		int l =(ByteBuffer.wrap(k1).order(ByteOrder.LITTLE_ENDIAN).getInt());
		
		L Tensione = new L(TensioneL1,TensioneL2,TensioneL3);
		
		float CorrenteL1 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 53, 55))/100;//2
		float CorrenteL2 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 55, 57))/100;//2
		float CorrenteL3 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 57, 59))/100;//2
		
		L Corrente = new L(CorrenteL1,CorrenteL2,CorrenteL3);
		
		float PotenzaL1 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 59, 61))/10;//2
		float PotenzaL2 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 61, 63))/10;//2
		float PotenzaL3 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 63, 65))/10;//2
		
		L Potenza = new L(PotenzaL1,PotenzaL2,PotenzaL3);
		
		float PotenzaRL1 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 65, 67))/10;//2
		float PotenzaRL2 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 67, 69))/10;//2
		float PotenzaRL3 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 69, 71))/10;//2
		
		L PotenzaR = new L(PotenzaRL1,PotenzaRL2,PotenzaRL3);
		
		float FattPotenzaL1 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 71, 73))/100;//2
		float FattPotenzaL2 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 73, 75))/100;//2
		float FattPotenzaL3 = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 75, 77))/100;//2
		
		L FattPotenza = new L(FattPotenzaL1,FattPotenzaL2,FattPotenzaL3);
		
		float EnergiaA = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 77, 79))/10;//2
		float EnergiaR = (float)Service.TwobytesToint(Arrays.copyOfRange(message, 79, 81))/10;//2

		
		 cCMAD_ANALOG_INFO = new CMADAnalogInfo(TempEst,
			    Lux,
			    TempSuolo, Tensione,Corrente,Potenza,PotenzaR,FattPotenza,EnergiaA,EnergiaR);
		
		

		//int df = bytesToShort(Arrays.copyOfRange(message, 41, 43));//2
		 
		 long Times = (long)Service.bytesToFloat(Arrays.copyOfRange(message, 81, 85));//4
		   
		   Instant epoch = Instant.parse("2000-01-01T00:00:00.00Z");
		   Instant later = epoch.plusSeconds( Times ) ; 
		    Timestamp = later.getEpochSecond();
		     Datas = Date.from(later);
		
		   armamento = Service.byteToInt(Arrays.copyOfRange(message, 85, 86));//1
		
		byte[] dummy = Arrays.copyOfRange(message, 86, 100);//9
		log.info(dummy);
		byte [] CRC = Arrays.copyOfRange(message, 100,102);// message.length-2, message.length) ;
		var = Arrays.copyOfRange(message, 0, 100);//2
		
		
		CMAD_CRC = Service.CRC(var);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		if(Arrays.equals(recCRC, CRC)){
			log.info("CRC OK");
		}else{
			log.info("CRC KO");
		}
		
		log.info(CMAD_CRC);
		
	//	 Timestamp ts=new Timestamp(Timestamp);  
     //    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
     //    System.out.println(formatter.format(ts)); 
		
		log.info("DATA"+ Timestamp);
 		log.info(CMAD_DESCRIPTION);
 		log.info(toString());
 		int bandiera = 102;
 		while(bandiera<message.length) {
 			//log.info(message.length);
 			String CMAD_HEADER2 = String.valueOf( (char)(message[bandiera]));//1
 			//MAD-RED 92
 			if(CMAD_HEADER2.equals("R")) {
 				
 				var = Arrays.copyOfRange(message, bandiera, bandiera+92);//92
 				MessageMADRED messmadred = new MessageMADRED(var);
 				JMadRed madred = messmadred.getMadRed();
 				listred.add(madred);
 				log.info(madred);
 				
 				bandiera+=92;
 			}
 			
 			if(CMAD_HEADER2.equals("L")) {
 				var = Arrays.copyOfRange(message, bandiera, bandiera+67);//67
 				MessageMADILL messmadill = new MessageMADILL(var);
 				JMADILL madill = messmadill.getMadILL();
 				listill.add(madill);
 				log.info(message.length);
 				bandiera+=67;
 			}
 			
 			
 			//MAD-ILL 67
 		}
 		
 		/*if(message.length>102) {
 			log.info(message.length);
 			String CMAD_HEADER2 = String.valueOf( (char)(message[102]));//1
 			
 			//MAD-RED 92
 			if(CMAD_HEADER2.equals("R")) {
 				
 				var = Arrays.copyOfRange(message, 102, 194);//2
 				MessageMADRED messmadred = new MessageMADRED(var);
 				JMadRed madred = messmadred.getMadRed();
 				listred.add(madred);
 				log.info(madred);
 				
 				
 			}
 			if(message.length>194) {
 			 CMAD_HEADER2 = String.valueOf( (char)(message[194]));//1
 			if(CMAD_HEADER2.equals("L")) {
 				var = Arrays.copyOfRange(message, 194, 261);//2
 				MessageMADILL messmadill = new MessageMADILL(var);
 				JMADILL madill = messmadill.getMadILL();
 				listill.add(madill);
 				log.info(message.length);
 			}
 			}
 			
 			//MAD-ILL 67
 		}*/
 		
		
	}
	
	public JCMAD getJCMAD() {
		JCMAD j = new JCMAD(CMAD_HEADER,
				MAC_ADR,
				CMAD_TYPE,
				CMAD_REVISION,
				new Short(CMAD_POSITION).toString(),
				CMAD_DESCRIPTION,
				new Long(CMAD_LONGITUDE).toString(),
				new Long(CMAD_LATITUDE).toString(),
				new Short(CMAD_DIGITAL_INFO).toString(),Datas ,armamento,
				Base64.getEncoder().encodeToString(mess),
				CMAD_CRC);
		
		j.setListred(listred);
		j.setListill(listill);
		j.setCMAD_ANALOG_INFO(cCMAD_ANALOG_INFO);
		
		
		
		return j;
	}
	
	/* Struttura	Bytes	Default	Descrizione
CMAD_HEADER	1	BYTE	Header messaggio deve essere impostato a C
CMAD_MAC	6	UBYTE	Mac address CMAD
CMAD_TYPE
	1	UBYTE	0x00	CMAD
			0x01	MAD-RED
			0x02	MAD-ILL
			0x03	
			0x04 	
			0x05	
			0x06	
			0x07	
CMAD_REVISION	1	UBYTE	Revisione periferica
CMAD_POSITION	2	UWORD	Posizione nella lista
Vale 0x0000
	LSB	MSB		
CMAD_DESCRIPTION	20	BYTE	Descrizione periferica
CMAD_LONGITUDE	4	ULONG	Longitudine periferica
	LSB	MSB		
CMAD_LATITUDE	4	ULONG	Latitudine periferica
	LSB…MSB		
CMAD_DIGITAL_INFO	2
(Gestione a bit)	0	Dati validi (CMAD online)
		1	Accensione manuale RED
		2	Accensione manuale ILL
		3	Validità temperatura esterna
		4	Validità luxometro
		5	Validità temperatura suolo
		6	Accensione RED sa stazione meteo o CMAD 
		7	Accensione ILL per crepuscolare
		8	SCORTA
		9	SCORTA
		10	SCORTA
		11	SCORTA
		12	SCORTA
		13	SCORTA
		14	SCORTA
		15	SCORTA
CMAD_ANALOG_INFO
(LSB, MSB)	2	INT	Temperatura esterna
	2	INT	Luxometro
	2	INT	Temperatura suolo
	2	INT	Tensione L1
	2	INT	Tensione L2
	2	INT	Tensione L3
	2	INT	Corrente L1
	2	INT	Corrente L2
	2	INT	Corrente L3
	2	INT	Potenza attiva L1
	2	INT	Potenza attiva L2
	2	INT	Potenza attiva L3
	2	INT	Potenza reattiva L1
	2	INT	Potenza reattiva L2
	2	INT	Potenza reattiva L3
	2	INT	Fattore di potenza L1
	2	INT	Fattore di potenza L2
	2	INT	Fattore di potenza L3
	2	INT	Energia attiva
	2	INT	Energia reattiva
CMAD_Dummy	19	BYTE	Bytes liberi
CMAD_CRC	2	UWORD	Calcolo CRC16 esclusi i campi HEADER e CRC
*/
	
	


	
	  
   /* public static int unsigned(byte[] b) {
    	int k = 0;
        for(int i=b.length-1;i>=0;i--){
        	 k += b[i] & 0xff;
        	
        }
       
        return k;
      }*/
    
   
    

   /*public static int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
   }*/
   /* public static final long unsignedIntToLong(byte[] b) {
        long l = 0;
        for(int i=0;i<b.length;i++){
        	l |= b[i] & 0xFF;
        	l <<= 8;
        }
       
        return l;
      }
    */
    public static byte[] intToBytes(int x) {
        byte[] bytes = new byte[2];

        for (int i = 0; x != 0; i++, x >>>= 8) {
            bytes[i] = (byte) (x & 0xFF);
        }

        return bytes;
    }
	@Override
	public String toString() {
		return (CMAD_HEADER != null ? "CMAD_HEADER: " + CMAD_HEADER + "; " : "")
				+ (MAC_ADR != null ? "MAC_ADR: " + MAC_ADR + "; " : "") + "CMAD_TYPE: " + CMAD_TYPE
				+ "; CMAD_REVISION: " + CMAD_REVISION + "; CMAD_POSITION: " + CMAD_POSITION + "; "
				+ (CMAD_DESCRIPTION != null ? "CMAD_DESCRIPTION: " + CMAD_DESCRIPTION + "; " : "") + "CMAD_LONGITUDE: "
				+ CMAD_LONGITUDE + "; CMAD_LATITUDE: " + CMAD_LATITUDE + "; CMAD_DIGITAL_INFO: " + CMAD_DIGITAL_INFO
				+ "; "
				+ (CMAD_ANALOG_INFO != null ? "CMAD_ANALOG_INFO: " + Arrays.toString(CMAD_ANALOG_INFO) + "; " : "")
				+ (CMAD_Dummy != null ? "CMAD_Dummy: " + Arrays.toString(CMAD_Dummy) + "; " : "") + "CMAD_CRC: "
				+ CMAD_CRC + "; Timestamp: " + Timestamp + "; armamento: " + armamento + "; "
				+ (cCMAD_ANALOG_INFO != null ? "cCMAD_ANALOG_INFO: " + cCMAD_ANALOG_INFO + "; " : "")
				+ (mess != null ? "mess: " + Arrays.toString(mess) + "; " : "")
				+ (listred != null ? "listred: " + listred + "; " : "")
				+ (listill != null ? "listill: " + listill : "");
	}
	
	public byte[] toByte() {
		
		
		
		return null;
	}
    
    
    
    
}
