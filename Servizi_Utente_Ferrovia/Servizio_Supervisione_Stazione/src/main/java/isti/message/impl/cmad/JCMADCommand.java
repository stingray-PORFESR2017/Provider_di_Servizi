package isti.message.impl.cmad;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JCMADCommand")
@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlType(name = "JCMADCommand", propOrder = {
    "MAC_ADR",
    "CMAD_DATE"    //<JCMADCommand><MAC_ADR>12</MAC_ADR><Command "CommandRed"="ON" "CommandIll"="OFF"></Command> </JCMADCommand>
})*/
public class JCMADCommand implements Serializable{

	@XmlElement(name = "MAC_ADR_cmad", required = true)
	String MAC_ADR_CMAD;
	@XmlElement(name = "MAC_ADR_red")
	String MAC_ADR_RED;
	@XmlElement(name = "MAC_ADR_ill")
	String MAC_ADR_ILL;
	@XmlElement(name = "Command", required = true)
	CommandType command;
	@XmlElement(name = "Id", required = true)
	String id;
	
	public JCMADCommand() {
		
	}
	
	

	public String getMAC_ADR_CMAD() {
		return MAC_ADR_CMAD;
	}



	public void setMAC_ADR_CMAD(String mAC_ADR_CMAD) {
		MAC_ADR_CMAD = mAC_ADR_CMAD;
	}



	public String getMAC_ADR_RED() {
		return MAC_ADR_RED;
	}



	public void setMAC_ADR_RED(String mAC_ADR_RED) {
		MAC_ADR_RED = mAC_ADR_RED;
	}



	public String getMAC_ADR_ILL() {
		return MAC_ADR_ILL;
	}



	public void setMAC_ADR_ILL(String mAC_ADR_ILL) {
		MAC_ADR_ILL = mAC_ADR_ILL;
	}



	public CommandType getCommand() {
		return command;
	}

	public void setCommand(CommandType command) {
		this.command = command;
	}

	

	@Override
	public String toString() {
		return "MAC_ADR_CMAD: " + MAC_ADR_CMAD + "\\n, MAC_ADR_RED: " + MAC_ADR_RED + "\\n, MAC_ADR_ILL: " + MAC_ADR_ILL
				+ "\\n, command: " + command + "\\n, id: " + id;
	}

	public String getId() {
		return id;
	}

	public void setId(String imei) {
		this.id = imei;
	}

	public String getMAC_ADR() {
		if(MAC_ADR_CMAD!=null) {
			return MAC_ADR_CMAD;
		}
		if(MAC_ADR_RED!=null) {
			return MAC_ADR_RED;
		}
		if(MAC_ADR_ILL!=null) {
			return MAC_ADR_ILL;
		}
		return "";
	}
	
	
	

	
}
