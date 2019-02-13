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
	
	public static String CRC(byte[] bytes){
		int crc = 0x0000;          // initial value
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
		System.out.println("CRC16-CCITT = " + crc);
		return Integer.toHexString(crc);
	}
	
	
	
	
	
	public static int CRCE(byte[] list){
		int iCRC= 0x0000; 
		for(int t=0;t<list.length;t++){
			iCRC = CRCECM(iCRC,list[t]& 0xff);
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


