package isti.message.impl.cmad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


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
		return "commandred: " + commandred + "\\n, commandill: " + commandill;
	}

	
	

}
