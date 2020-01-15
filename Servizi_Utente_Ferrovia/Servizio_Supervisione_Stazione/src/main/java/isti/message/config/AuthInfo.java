package isti.message.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AuthInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthInfo {
	
	
	@XmlElement(name = "Id", required = true)
	String Id;
	
	AuthInfo(){
		
	}

	public String getId() {
		return Id;
	}

	public void setId(String authInfo) {
		Id = authInfo;
	}

	@Override
	public String toString() {
		return "Id: " + Id;
	}
	
	

}
