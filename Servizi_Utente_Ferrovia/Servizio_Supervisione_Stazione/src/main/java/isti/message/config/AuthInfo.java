package isti.message.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AuthInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthInfo {
	
	
	@XmlElement(name = "AuthInfo", required = true)
	String AuthInfo;
	
	AuthInfo(){
		
	}

	public String getAuthInfo() {
		return AuthInfo;
	}

	public void setAuthInfo(String authInfo) {
		AuthInfo = authInfo;
	}

	@Override
	public String toString() {
		return "AuthInfo: " + AuthInfo;
	}
	
	

}
