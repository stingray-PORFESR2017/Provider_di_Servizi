package isti.message.impl.cmad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlType(name = "CommandType", propOrder = {
    "CommandRed",
    "CommandIll"    
})*/
public class CommandType {
	
	@XmlAttribute(name = "commandred")
    protected FormatoType commandred;
	
	@XmlAttribute(name = "commandill")
    protected FormatoType commandill;
	
	@XmlElement(name = "Dimmer")
	Integer Dimmer;

	public FormatoType getCommandred() {
		return commandred;
	}

	public void setCommandred(FormatoType commandred) {
		this.commandred = commandred;
	}

	public FormatoType getCommandill() {
		return commandill;
	}

	public void setCommandill(FormatoType commandill) {
		this.commandill = commandill;
	}

	@Override
	public String toString() {
		return "commandred: " + commandred + "\\n, commandill: " + commandill+ "\\n, Dimmer: " + Dimmer;
	}

	public Integer getDimmer() {
		return Dimmer;
	}

	public void setDimmer(Integer dimmer) {
		Dimmer = dimmer;
	}

	
	

}
