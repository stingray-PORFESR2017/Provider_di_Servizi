//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package isti.message.impl.cmad;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "FormatoType")
@XmlEnum
public enum FormatoType {


    
    @XmlEnumValue("ON")
    ON("ON"),
    @XmlEnumValue("OFF")
    OFF("OFF");
    private final String value;

    FormatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FormatoType fromValue(String v) {
        for (FormatoType c: FormatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
    
    public boolean getStatus() {
    	if(value!=null) {
    		if(value=="ON") {
    			return true;
    		}
    	}
    	return false;
    }

}
