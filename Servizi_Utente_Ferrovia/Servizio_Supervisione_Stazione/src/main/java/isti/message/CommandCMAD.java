package isti.message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.google.common.primitives.Bytes;

import isti.message.impl.cmad.CMADAnalogInfo;
import isti.message.impl.cmad.FormatoType;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.message.impl.cmad.L;
import isti.message.impl.ill.JMADILL;
import isti.message.impl.red.JMadRed;
import isti.message.util.Service;




public class CommandCMAD {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CommandCMAD.class);
	

	public CommandCMAD() {

		String MAC_ADR = "000000000034";
		byte[] mess = new byte[38];
		char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] mac = Service.toByteArray(MAC_ADR);
		
//		0x00009001	0x0000570028007E00	Comando accensione RED
		//	0x00230500009AC428	Comando spegnimento RED
		byte[] command_red = Service.toByteArray("00009001");
		
		byte[] command_A_red = Service.toByteArray("0000570028007E00");
		byte[] command_S_red = Service.toByteArray("00230500009AC428");
	
		
//	0x00009020	0xF10057002800BB32	Comando accensione ILL
//		0xC267D400993859A5	Comando spegnimento ILL

		byte[] command_ill = Service.toByteArray("00009020");
		
		byte[] command_A_ill = Service.toByteArray("F10057002800BB32");
		byte[] command_S_ill = Service.toByteArray("C267D400993859A5");
		
		
		byte[] Dummy = new byte[16];
		mess = Bytes.concat(b, mac, command_red,command_A_red, Dummy);
		
		//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		
		System.out.println();
	}
	
	public byte[] getMessage(JCMADCommand message) {
		String mac = message.getMAC_ADR_CMAD();
		String macill = message.getMAC_ADR_ILL();
		String macred = message.getMAC_ADR_RED();
		FormatoType ill = message.getCommand().getCommandill();
		FormatoType red = message.getCommand().getCommandred();
		if(ill!=null){
			if(ill.getStatus()) {
				if(macill!=null){
					Integer d = message.getCommand().getDimmer();
					if(d!=null) {
						if(d>0) {
							return this.CommandDimmer(mac,macill,d);
						}
					}
					return this.CommandIllOn(mac,macill);
				}else
					return this.CommandIllOn(mac,mac);
			}else {
				if(macill!=null){
					return this.CommandIllOff(mac,macill);
				}else
					return this.CommandIllOff(mac,mac);
			}

		}
		if(red!=null) {
			if(red.getStatus()) {
				if(macred!=null){
					return this.CommandRedOn(mac,macred);
				}else
					return this.CommandRedOn(mac,mac);
			}else {
				if(macred!=null){
					return this.CommandRedOff(mac,macred);
				}else
					return this.CommandRedOff(mac,mac);
			}
		}
		
		/*if(ill!=null) {
			if(ill.getStatus()) {
				return this.CommandIllOn(mac);
			}else {
				return this.CommandIllOff(mac);
			}
		}
		if(red!=null) {
			if(red.getStatus()) {
				return this.CommandRedOn(mac);
			}else {
				return this.CommandRedOff(mac);
			}
		}*/
		return CommandNull(mac);
	}
	
	public byte[] getMessageNull(JCMADCommand message) {
		String mac = message.getMAC_ADR();
		
		return  CommandNull(mac);
	}
	
	public byte[] getMessageNull(String mac) {
		
		return  CommandNull(mac);
	}
	
	public byte[] CommandNull(String mac) {
		
		
		String command_code = "00000000";
		String command_value = "0000000000000000";
		int typ = 0;
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac,  mac,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		/*char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] macb = Service.toByteArray(mac);
		

		byte[] command_red = Service.toByteArray("00000000");
		
		byte[] command_A_red = Service.toByteArray("0000000000000000");
		

		
		byte[] Dummy = new byte[16];
		byte[] mess = Bytes.concat(b, macb, command_red,command_A_red, Dummy);
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		log.trace("Send On Command:"+ mac +" "+ mess);
		return mess;*/
	}
	
	public long getTimes() {
		 
		 Instant epoch = Instant.parse("2000-01-01T00:00:00.00Z");
		   Instant timetosend = Instant.now().minusSeconds(epoch.getEpochSecond());
		   
		   ZoneOffset offset = ZoneOffset.of("+01:00");
		   
		   OffsetDateTime odt = timetosend.atOffset( offset ) ;
		   
		
		return  odt.toInstant().getEpochSecond(); //timetosend.getEpochSecond();
	}
	
	
	public byte[] CommandDimmer(String mac1, String mac2, int dimmer) {
		
		String command_code = "00009300";
		
		String command_value = String.format("%015d", dimmer);;
		if(!mac1.equals(mac2)){
			command_value = "000057002800007E";
		}
		int typ = 0;
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac1,  mac2,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		
	}
	
	public byte[] CommandRedOn(String mac1, String mac2) {
		
		String command_code = "00009001";
		String command_value = "0000570028007E00";
		int typ = 0;
		if(!mac1.equals(mac2)){
			command_value = "000057002800007E";
			typ = 1;
		}
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac1,  mac2,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		
	}
	public byte[] CommandRedOff(String mac, String mac2) {
		
		String command_code = "00009001";
		String command_value = "00230500009AC428";
		int typ = 0;
		if(!mac.equals(mac2)){
			command_value = "00230500009A28C4";
			typ = 1;
		}
		
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac,  mac2,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		
		
	}
	
	public byte[] CommandIllOn(String mac, String mac2) {
		
		String command_code = "00009020";
		String command_value = "F10057002800BB32";
		int typ = 0;
		if(!mac.equals(mac2)){
			command_value = "F1005700280032BB";
			typ = 2;
		}
		
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac,  mac2,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		
		
	}
	public byte[] CommandIllOff(String mac, String mac2) {
		
		String command_code = "00009020";
		String command_value = "C267D400993859A5";
		int typ = 0;
		if(!mac.equals(mac2)){
			command_value = "C267D4009938A559";
			typ = 2;
		}
		
		int group = 0 ;
		long millis = getTimes();
		int vali = 5;
		
		return CommandGeneric( mac,  mac2,  typ,  command_code,  command_value,
				 group,  millis,  vali);
		
		
	}
	
	
	public byte[] CommandGeneric(String mac1, String mac2, int typ, String command_code, String command_value,
			int group, long tmilli, int vali) {
		try{
			char a = (char) 'C';
			byte[] b = new byte[] {(byte)a};

			byte[] maca = Service.toByteArray(mac1);

			byte[] type = new byte[] {(byte)typ};

			//		0x00009001	0x0000570028007E00	Comando accensione RED
			//	0x00230500009AC428	Comando spegnimento RED
			byte[] command_red = Service.toByteArray(command_code);

			byte[] command_A_red = Service.toByteArray(command_value);

			byte[] macb = Service.toByteArray(mac2);

			byte[] gruppo = Service.intToBytes(group);




			byte[] time = Service.longToByte(tmilli);

			byte[] val = Service.intToBytes(vali);

			byte[] Dummy = new byte[16];
			byte[] mess = Bytes.concat(b, maca,type, command_red,command_A_red,macb,gruppo,time,val, Dummy);

			//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
			int CMAD_CRC = Service.CRC(mess);
			byte[] recCRC = Service.intToBytes(CMAD_CRC);



			mess = Bytes.concat(mess,recCRC);
			log.trace("Send On Command:"+ mac1 +" "+ mac2  +" "+ mess);
			return mess;
		}catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	public String getMAC_ADR(byte[] messagebyte) {
		String CMAD_HEADER = String.valueOf( (char)(messagebyte[0]));//1
		byte[] CMAD_MAC = Arrays.copyOfRange(messagebyte, 1, 7);//6
		String MAC_ADR = Service.getMacString(CMAD_MAC);
		return MAC_ADR;
	}
	
    
    
    
}
