package isti.message.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

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
	
	
	 public static String getMacString(byte[] macAddress) {
	        StringBuilder retval = new StringBuilder(17);
	        
	        boolean isFirst = true;
	        for (byte b : macAddress) {
	          if (!isFirst) {
	            //retval.append(":");
	          } else {
	            isFirst = false;
	          }
	          retval.append(String.format("%02x", b & 0xff));
	        }
	        return retval.toString();
	      }
	 public static short bytesToShort(byte[] bytes) {
	     return (short) (ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort() & 0xFF);
	}
	
	public static short bytesToInt(byte[] bytes) {
	     return (short) (ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFF);
	}
	
	public static short byteToInt(byte[] byt) {
		byte[] bytes = {byt[0],0,0,0};
	     return (short) (ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFF);
	}
	
	
	public static long bytesToLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes)
        .order(ByteOrder.LITTLE_ENDIAN).getInt() & 0xFFFFFFFFL;
	
	}
	
	public static float bytesToFloat(byte[] bytes) {
		return ByteBuffer.wrap(bytes)
        .order(ByteOrder.LITTLE_ENDIAN).getFloat();
	
	}
	
	public static int TwobytesToint(byte[] bytes) {
		byte[] k = {bytes[0],bytes[1],0,0};
	 int l =(ByteBuffer.wrap(k).order(ByteOrder.LITTLE_ENDIAN).getInt() /*& 0xFF*/);
	 return l;
	}
	
	public static float TwobytesToLong(byte[] bytes) {
		byte[] k = {bytes[0],bytes[1],0,0};
	 float l = ByteBuffer.wrap(k)
             .order(ByteOrder.LITTLE_ENDIAN).getFloat();
	 return l;
	}
	
	public static byte[] getBytefromBase64(String in) {
		
		
		return Base64.getDecoder().decode(in);
		
	}
	
	public static byte[] toByteArray(String s) {
	    return DatatypeConverter.parseHexBinary(s);
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


