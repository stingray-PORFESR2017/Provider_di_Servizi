package isti.message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
		String mac = message.getMAC_ADR();
		FormatoType ill = message.getCommand().getCommandill();
		FormatoType red = message.getCommand().getCommandred();
		if(ill!=null) {
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
		}
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
		char a = (char) 'C';
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
		return mess;
	}
	
	public byte[] CommandRedOn(String mac) {
		char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] macb = Service.toByteArray(mac);
		
//		0x00009001	0x0000570028007E00	Comando accensione RED
		//	0x00230500009AC428	Comando spegnimento RED
		byte[] command_red = Service.toByteArray("00009001");
		
		byte[] command_A_red = Service.toByteArray("0000570028007E00");
		

		
		byte[] Dummy = new byte[16];
		byte[] mess = Bytes.concat(b, macb, command_red,command_A_red, Dummy);
		
		//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		log.trace("Send On Command:"+ mac +" "+ mess);
		return mess;
	}
	public byte[] CommandRedOff(String mac) {
		
		
		char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] macp = Service.toByteArray(mac);
		
//		0x00009001	0x0000570028007E00	Comando accensione RED
		//	0x00230500009AC428	Comando spegnimento RED
		byte[] command_red = Service.toByteArray("00009001");
		
		byte[] command_S_red = Service.toByteArray("00230500009AC428");
	

		
		byte[] Dummy = new byte[16];
		byte[] mess = Bytes.concat(b, macp, command_red,command_S_red, Dummy);
		
		//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		
		log.trace("Send On Command:"+ mac +" "+ mess);
		return mess;
		
	}
	
	public byte[] CommandIllOn(String mac) {
		char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] macp = Service.toByteArray(mac);
		
//		0x00009020	0xF10057002800BB32	Comando accensione ILL
//		0xC267D400993859A5	Comando spegnimento ILL

		byte[] command_ill = Service.toByteArray("00009020");
		
		byte[] command_A_ill = Service.toByteArray("F10057002800BB32");
		
		

		
		byte[] Dummy = new byte[16];
		byte[] mess = Bytes.concat(b, macp, command_ill,command_A_ill, Dummy);
		
		//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		
		log.trace("Send On Command:"+ mac +" "+ mess);
		return mess;
		
	}
	public byte[] CommandIllOff(String mac) {
		
		char a = (char) 'C';
		byte[] b = new byte[] {(byte)a};
		
		byte[] macp = Service.toByteArray(mac);
		
		
//		0x00009020	0xF10057002800BB32	Comando accensione ILL
//		0xC267D400993859A5	Comando spegnimento ILL

		byte[] command_ill = Service.toByteArray("00009020");
		
		byte[] command_S_ill = Service.toByteArray("C267D400993859A5");
	
		byte[] Dummy = new byte[16];
		byte[] mess = Bytes.concat(b, macp, command_ill,command_S_ill, Dummy);
		
		//byte[] var = Arrays.copyOfRange(mess, 7, 36);//36
		int CMAD_CRC = Service.CRC(mess);
		byte[] recCRC = Service.intToBytes(CMAD_CRC);
		
		
		
		mess = Bytes.concat(mess,recCRC);
		
		log.trace("Send On Command:"+ mac +" "+ mess);
		return mess;
	}

	public String getMAC_ADR(byte[] messagebyte) {
		String CMAD_HEADER = String.valueOf( (char)(messagebyte[0]));//1
		byte[] CMAD_MAC = Arrays.copyOfRange(messagebyte, 1, 7);//6
		String MAC_ADR = Service.getMacString(CMAD_MAC);
		return MAC_ADR;
	}
	
    
    
    
}
