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

	@XmlElement(name = "MAC_ADR", required = true)
	String MAC_ADR;
	@XmlElement(name = "Command", required = true)
	CommandType command;
	@XmlElement(name = "imei", required = true)
	String imei;
	
	public JCMADCommand() {
		
	}
	
	public String getMAC_ADR() {
		return MAC_ADR;
	}
	public void setMAC_ADR(String mAC_ADR) {
		MAC_ADR = mAC_ADR;
	}

	public CommandType getCommand() {
		return command;
	}

	public void setCommand(CommandType command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return "MAC_ADR: " + MAC_ADR + "\\n, command: " + command + "\\n, imei: " + imei;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	
	
}
