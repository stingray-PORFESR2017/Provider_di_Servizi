package isti.message.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AuthInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthInfo {
	
	
	@XmlElement(name = "Id", required = true)
	String Id;
	@XmlAttribute(name = "User")
	String user;
	@XmlElement(name = "FirebaseToken", required = true)
	String FirebaseToken;
	
	AuthInfo(){
		
	}

	public String getId() {
		return Id;
	}

	public void setId(String authInfo) {
		this.Id = authInfo;
	}

	@Override
	public String toString() {
		return "Id: " + Id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFirebaseToken() {
		return FirebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		FirebaseToken = firebaseToken;
	}
	
	

}
