package isti;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;




public class MessageCMAD {
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
	CMADAnalogInfo cCMAD_ANALOG_INFO;
	byte[] mess;
	
	MessageCMAD(byte[] message) {

		mess = message;
		
		CMAD_HEADER = String.valueOf( (char)(message[0]));//1
		byte[] CMAD_MAC = Arrays.copyOfRange(message, 1, 7);//6
		MAC_ADR = getMacString(CMAD_MAC);
		CMAD_TYPE  = message[7]& 0xff;;//1
		CMAD_REVISION  = message[8]& 0xff;;//1
		
		byte[] var = Arrays.copyOfRange(message, 9, 11);//2
		CMAD_POSITION =  bytesToShort(var); 
		try {
			CMAD_DESCRIPTION =  new String(Arrays.copyOfRange(message, 11, 31), "UTF-8");//20
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] LONGITUDE =    Arrays.copyOfRange(message, 31, 35);//4
		CMAD_LONGITUDE = bytesToLong(LONGITUDE)/10000;
		byte[]  LATITUDE =    Arrays.copyOfRange(message, 35, 39);//4
		CMAD_LATITUDE = bytesToLong(LATITUDE)/10000;
		
		var = Arrays.copyOfRange(message, 39, 41);//2
		CMAD_DIGITAL_INFO = bytesToShort(var); 
		
		CMAD_ANALOG_INFO =  Arrays.copyOfRange(message, 41, 81);//20
		
		
		float TempEst = (float) TwobytesToint(Arrays.copyOfRange(message, 41, 43))/10;//2
		int Lux =  TwobytesToint(Arrays.copyOfRange(message, 43, 45));//2
		
		//byte[] var2 = Arrays.copyOfRange(message, 45, 47);
		float TempSuolo = (float) TwobytesToint(Arrays.copyOfRange(message, 45, 47))/10;//2
		float TensioneL1 = (float) TwobytesToint(Arrays.copyOfRange(message, 47, 49))/10;//2
		float TensioneL2 = (float) TwobytesToint(Arrays.copyOfRange(message, 49, 51))/10;//2
		float TensioneL3 = (float) TwobytesToint(Arrays.copyOfRange(message, 51, 53))/10;//2
		
		L Tensione = new L(TensioneL1,TensioneL2,TensioneL3);
		
		float CorrenteL1 = (float) TwobytesToint(Arrays.copyOfRange(message, 53, 55))/100;//2
		float CorrenteL2 = (float) TwobytesToint(Arrays.copyOfRange(message, 55, 57))/100;//2
		float CorrenteL3 = (float) TwobytesToint(Arrays.copyOfRange(message, 57, 59))/100;//2
		
		L Corrente = new L(CorrenteL1,CorrenteL2,CorrenteL3);
		
		float PotenzaL1 = (float) TwobytesToint(Arrays.copyOfRange(message, 59, 61))/10;//2
		float PotenzaL2 = (float) TwobytesToint(Arrays.copyOfRange(message, 61, 63))/10;//2
		float PotenzaL3 = (float) TwobytesToint(Arrays.copyOfRange(message, 63, 65))/10;//2
		
		L Potenza = new L(PotenzaL1,PotenzaL2,PotenzaL3);
		
		float PotenzaRL1 = (float) TwobytesToint(Arrays.copyOfRange(message, 65, 67))/10;//2
		float PotenzaRL2 = (float) TwobytesToint(Arrays.copyOfRange(message, 67, 69))/10;//2
		float PotenzaRL3 = (float) TwobytesToint(Arrays.copyOfRange(message, 69, 71))/10;//2
		
		L PotenzaR = new L(PotenzaRL1,PotenzaRL2,PotenzaRL3);
		
		float FattPotenzaL1 = (float) TwobytesToint(Arrays.copyOfRange(message, 71, 73))/100;//2
		float FattPotenzaL2 = (float) TwobytesToint(Arrays.copyOfRange(message, 73, 75))/100;//2
		float FattPotenzaL3 = (float) TwobytesToint(Arrays.copyOfRange(message, 75, 77))/100;//2
		
		L FattPotenza = new L(FattPotenzaL1,FattPotenzaL2,FattPotenzaL3);
		
		float EnergiaA = (float) TwobytesToint(Arrays.copyOfRange(message, 77, 79))/10;//2
		float EnergiaR = (float) TwobytesToint(Arrays.copyOfRange(message, 79, 81))/10;//2

		
		 cCMAD_ANALOG_INFO = new CMADAnalogInfo(TempEst,
			    Lux,
			    TempSuolo, Tensione,Corrente,Potenza,PotenzaR,FattPotenza,EnergiaA,EnergiaR);
		
		

		//int df = bytesToShort(Arrays.copyOfRange(message, 41, 43));//2
		
		byte[] dymmy = Arrays.copyOfRange(message, 81, 100);//19
	
		byte [] CRC = Arrays.copyOfRange(message,  message.length-2, message.length) ;
		var = Arrays.copyOfRange(message, 0, message.length-2);//2
		
		
		CMAD_CRC = Service.CRC(var);
		byte[] recCRC = intToBytes(CMAD_CRC);
		if(Arrays.equals(recCRC, CRC)){
			System.out.println("CRC OK");
		}else{
			System.err.println("CRC KO");
		}
		
		System.out.println(CMAD_CRC);
 		System.out.println(CMAD_DESCRIPTION);
 		System.out.println(toString());
		
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
				new Short(CMAD_DIGITAL_INFO).toString(),
				Base64.getEncoder().encodeToString(mess),
				CMAD_CRC);
		
		
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
	public short bytesToShort(byte[] bytes) {
	     return (short) (ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort() & 0xFF);
	}
	
	public short bytesToInt(byte[] bytes) {
	     return (short) (ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFF);
	}
	
	
	public long bytesToLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes)
        .order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFFFFFFFFL;
	
	}
	
	public int TwobytesToint(byte[] bytes) {
		byte[] k = {bytes[0],bytes[1],0,0};
	 int l =(ByteBuffer.wrap(k).order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFF);
	 return l;
	}
	
	public float TwobytesToLong(byte[] bytes) {
		byte[] k = {bytes[0],bytes[1],0,0};
	 float l = ByteBuffer.wrap(bytes)
             .order(ByteOrder.LITTLE_ENDIAN).getFloat();
	 return l;
	}

	
	  
   /* public static int unsigned(byte[] b) {
    	int k = 0;
        for(int i=b.length-1;i>=0;i--){
        	 k += b[i] & 0xff;
        	
        }
       
        return k;
      }*/
    
    public static String getMacString(byte[] macAddress) {
        StringBuilder retval = new StringBuilder(17);
        
        boolean isFirst = true;
        for (byte b : macAddress) {
          if (!isFirst) {
            retval.append(":");
          } else {
            isFirst = false;
          }
          retval.append(String.format("%02x", b & 0xff));
        }
        return retval.toString();
      }
    

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
		return "CMAD_HEADER: " + CMAD_HEADER + "; \n MAC_ADR: " + MAC_ADR + "; \n CMAD_TYPE: " + CMAD_TYPE
				+ "; \n CMAD_REVISION: " + CMAD_REVISION + "; \n CMAD_POSITION: " + CMAD_POSITION
				+ "; \n CMAD_DESCRIPTION: " + CMAD_DESCRIPTION + "; \n CMAD_LONGITUDE: " + CMAD_LONGITUDE
				+ "; \n CMAD_LATITUDE: " + CMAD_LATITUDE + "; \n CMAD_DIGITAL_INFO: " + CMAD_DIGITAL_INFO
				+ "; \n CMAD_ANALOG_INFO: " + Arrays.toString(CMAD_ANALOG_INFO) + "; \n CMAD_Dummy: "
				+ Arrays.toString(CMAD_Dummy) + "; \n CMAD_CRC: " + CMAD_CRC;
	}
	
    
    
    
    
}
