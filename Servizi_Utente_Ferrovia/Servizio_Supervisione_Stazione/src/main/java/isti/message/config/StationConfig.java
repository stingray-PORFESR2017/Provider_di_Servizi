package isti.message.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "StationConfig")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name="StationConfig" )
@Table(name = "StationConfig") 
@NamedQueries({
	@NamedQuery(name="StationConfig.findAll",
			query="SELECT c FROM config c"),
	@NamedQuery(name="StationConfig.findAllimei",
	query="SELECT c FROM config c WHERE c.id= ?1 "),
	
}) 
public class StationConfig implements Serializable{
	
	
	@XmlElement(name = "id", required = true)
	@Id
	String id;
	@XmlElement(name = "Descrizione", required = true)
	String Descrizione;
	@XmlElementWrapper(name="ListCMAD")
    @XmlElement(name="CMAD")
	List<String> ListCMAD;
	
	
	public StationConfig(){
		id = UUID.randomUUID().toString() ;
	}
	
	
	
	public String getId() {
		if(id==null)
			id = UUID.randomUUID().toString() ;
		return id;
	}

	

	


	@Override
	public String toString() {
		return (id != null ? "id: " + id + ", \\n " : "")
				+ (Descrizione != null ? "Descrizione: " + Descrizione + ", \\n " : "")
				+ (ListCMAD != null ? "ListCMAD: " + ListCMAD : "");
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDescrizione() {
		return Descrizione;
	}



	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}



	public List<String> getListCMAD() {
		if(ListCMAD==null) {
			ListCMAD = new  ArrayList<String>();
		}
		return ListCMAD;
	}



	public void setListCMAD(List<String> listCMAD) {
		ListCMAD = listCMAD;
	}
	
	
}
