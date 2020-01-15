package isti.message.config;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ConfigCommand")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name="config" )
@Table(name = "configcommand") 
@NamedQueries({
	@NamedQuery(name="ConfigCommand.findAll",
			query="SELECT c FROM config c"),
	@NamedQuery(name="ConfigCommand.findAllimei",
	query="SELECT c FROM config c WHERE c.id= ?1 "),
	
}) 
public class ConfigCommand implements Serializable{
	
	
	@XmlElement(name = "id", required = true)
	@Id
	String id;
	@XmlElement(name = "AuthLevel", required = true)
	String AuthLevel;
	
	public ConfigCommand(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setImei(String id) {
		this.id = id;
	}
	public String getAuthLevel() {
		return AuthLevel;
	}
	public void setAuthLevel(String authLevel) {
		AuthLevel = authLevel;
	}

	@Override
	public String toString() {
		return "id: " + id + ", AuthLevel: " + AuthLevel;
	}
	
	public boolean isGod() {
		if(this.AuthLevel=="GOD")
			return true;
		return false;
	}
	

}
