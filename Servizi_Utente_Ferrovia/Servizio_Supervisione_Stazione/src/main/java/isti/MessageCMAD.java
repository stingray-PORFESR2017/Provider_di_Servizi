package isti;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;




public class MessageCMAD {
	String CMAD_HEADER;
	String MAC_ADR;
	int CMAD_TYPE = 0;
	int CMAD_REVISION;
	short CMAD_POSITION;
	String CMAD_DESCRIPTION;
	byte[]  CMAD_LONGITUDE;
	byte[] CMAD_LATITUDE;
	short CMAD_DIGITAL_INFO;
	byte[] CMAD_ANALOG_INFO;
	byte[] CMAD_Dummy;
	short CMAD_CRC;
	MessageCMAD(byte[] messag) {

		byte[] message = unsigned(messag);
		
		CMAD_HEADER = String.valueOf( (char)(message[0]));//1
		byte[] CMAD_MAC = Arrays.copyOfRange(message, 1, 7);//6
		MAC_ADR = getMacString(CMAD_MAC);
		CMAD_REVISION  = message[8]& 0xff;;//1
		byte[] var = Arrays.copyOfRange(message, 9, 11);//2
		CMAD_POSITION =  bytesToShort(var); 
		try {
			CMAD_DESCRIPTION =  new String(Arrays.copyOfRange(message, 11, 31), "UTF-8");//20
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CMAD_LONGITUDE =    Arrays.copyOfRange(message, 32, 36);//4
		long f = unsignedIntToLong(Arrays.copyOfRange(message, 31, 35));
		CMAD_LATITUDE =    Arrays.copyOfRange(message, 37, 41);//4
		var = Arrays.copyOfRange(message, 42, 44);//2
		CMAD_DIGITAL_INFO = bytesToShort(var); 
		var = Arrays.copyOfRange(message, 45, 47);//2
		int df = bytesToShort(var);
		byte [] CRC = Arrays.copyOfRange(message,  message.length-2, message.length) ;
		var = Arrays.copyOfRange(message, 1, message.length-2);//2
		
		int calculatecrc =  Service.CRCE(var);
		System.out.println(calculatecrc);
		String d = Service.CRC(var);
		System.out.println(d);
 		System.out.println(CMAD_DESCRIPTION);
		
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
	     return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
	}
	public byte[] shortToBytes(short value) {
	    return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
	}
	
	private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);    

  
	  
    public static byte[] unsigned(byte[] b) {
    	byte[] l = new byte[b.length];
        for(int i=0;i<b.length;i++){
        	int k = b[i] & 0xff;
        	l[i] = (byte) k;
        }
       
        return l;
      }
    
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
    

   public static int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
   }
    public static final long unsignedIntToLong(byte[] b) {
        long l = 0;
        for(int i=0;i<b.length;i++){
        	l |= b[i] & 0xFF;
        	l <<= 8;
        }
       
        return l;
      }
}
