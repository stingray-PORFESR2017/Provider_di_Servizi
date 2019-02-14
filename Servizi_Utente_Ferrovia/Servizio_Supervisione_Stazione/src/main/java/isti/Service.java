package isti;

public class Service {


	public static void CRC(String message){
		int crc = 0xFFFF;          // initial value
		int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 

		// byte[] testBytes = "123456789".getBytes("ASCII");

		byte[] bytes = message.getBytes();

		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b   >> (7-i) & 1) == 1);
				boolean c15 = ((crc >> 15    & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit) crc ^= polynomial;
			}
		}

		crc &= 0xffff;
		System.out.println("CRC16-CCITT = " + Integer.toHexString(crc));
	}
	
	public static int CRC(byte[] bytes){
		int crc = 0x00;          // initial value
		int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 

		// byte[] testBytes = "123456789".getBytes("ASCII");

		

		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b   >> (7-i) & 1) == 1);
				boolean c15 = ((crc >> 15    & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit) crc = (crc) ^ polynomial;
			}
		}

		crc &= 0xffff;
		System.out.println("CRC16-CCITT = " + crc+ " "+Integer.toHexString(crc));
		return crc;
	}
	
	
	public static byte[] intToBytes(int x) {
	    byte[] bytes = new byte[2];

	    for (int i = 0; x != 0; i++, x >>>= 8) {
	        bytes[i] = (byte) (x & 0xFF);
	    }

	    return bytes;
	}
	
	
	
	public static int CRCE(byte[] list){
		int iCRC= 0x0000; 
		for(int t=0;t<list.length;t++){
			iCRC = CRCECM(iCRC,list[t]);
		}
		
		return iCRC;
	}
	
	public static int CRCECM( int iCRC, int iNew_val){
		         // initial value
		int MTT = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 

		  iNew_val<<=8;
		  for(int sLoopCRC=0;sLoopCRC<8;sLoopCRC++)
		    {
			  if (((iCRC ^ iNew_val) & 0x8000)!=0)
		      {
		      iCRC = (iCRC<<1)^MTT;
		      }
		    else
		      {
		      iCRC<<=1;
		      }
		      
		    iNew_val<<=1;
		    }
		    
		  return iCRC;
		}

		

	/*	for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b   >> (7-i) & 1) == 1);
				boolean c15 = ((crc >> 15    & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit) crc ^= polynomial;
			}
		}

		crc &= 0xffff;
		System.out.println("CRC16-CCITT = " + crc);
		return Integer.toHexString(crc);
	}*/
}


