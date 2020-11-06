package isti.message.impl.ill;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import isti.message.impl.cmad.JCMADID;
import isti.message.impl.red.WireAnalogInfo;


@XmlRootElement(name = "DatiMadIll", namespace = "http://stingray.isti.cnr.it/docs/xsd/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MadIll", propOrder = {
		"Id","HEADER","TYPE","REVISION","POSITION","DESCRIPTION","LONGITUDE","LATITUDE","DIGITAL_INFO","ANALOG_INFO","RAW","CRC"
})
@Entity(name="JMadIll" )
@Table(name = "MadIll")
public class JMADILL  implements Serializable{
	
	
	@XmlElement(/*name = "CMAD",*/ required = true)
	@EmbeddedId
	JCMADID Id;

	@XmlElement(required = true)
	String HEADER;

	@XmlElement(required = true)
	int TYPE = 0;
	@XmlElement(required = true)
	int REVISION;
	@XmlElement(required = true)
	String POSITION;
	@XmlElement(required = true)
	String DESCRIPTION;
	@XmlElement(required = true)
	String  LONGITUDE;
	@XmlElement(required = true)
	String LATITUDE;
	@XmlElement(required = true)
	String DIGITAL_INFO;
	


	@XmlElement(required = true)
	@Embedded AnalogInfo ANALOG_INFO;


	@XmlElement(name = "RAW_BASE64", required = true)
	String RAW;
	@XmlElement(required = true)
	String CRC;
	/**
	 * @param id
	 * @param hEADER
	 * @param tYPE
	 * @param rEVISION
	 * @param pOSITION
	 * @param dESCRIPTION
	 * @param lONGITUDE
	 * @param lATITUDE
	 * @param dIGITAL_INFO
	 * @param aNALOG_INFO
	 * @param timestamp 
	 * @param rAW
	 * @param cRC
	 */
	public JMADILL(String cmAC, String hEADER, int tYPE, int rEVISION, String pOSITION, String dESCRIPTION,
			String lONGITUDE, String lATITUDE, String dIGITAL_INFO, AnalogInfo aNALOG_INFO, long timestamp, String rAW, String cRC) {
		Id = new JCMADID(cmAC,  new Date(timestamp));
		HEADER = hEADER;
		TYPE = tYPE;
		REVISION = rEVISION;
		POSITION = pOSITION;
		DESCRIPTION = dESCRIPTION;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		DIGITAL_INFO = dIGITAL_INFO;
		ANALOG_INFO = aNALOG_INFO;
		RAW = rAW;
		CRC = cRC;
	}
	public JMADILL() {
	}
	@Override
	public int hashCode() {
		return Objects.hash(ANALOG_INFO, CRC, DESCRIPTION, DIGITAL_INFO, HEADER, Id, LATITUDE, LONGITUDE, POSITION, RAW,
				REVISION, TYPE);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JMADILL other = (JMADILL) obj;
		return Objects.equals(ANALOG_INFO, other.ANALOG_INFO) && Objects.equals(CRC, other.CRC)
				&& Objects.equals(DESCRIPTION, other.DESCRIPTION) && Objects.equals(DIGITAL_INFO, other.DIGITAL_INFO)
				&& Objects.equals(HEADER, other.HEADER) && Objects.equals(Id, other.Id)
				&& Objects.equals(LATITUDE, other.LATITUDE) && Objects.equals(LONGITUDE, other.LONGITUDE)
				&& Objects.equals(POSITION, other.POSITION) 
				&& REVISION == other.REVISION && TYPE == other.TYPE;
	}
	public JCMADID getId() {
		return Id;
	}
	public void setId(JCMADID id) {
		Id = id;
	}
	public String getHEADER() {
		return HEADER;
	}
	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}
	public int getREVISION() {
		return REVISION;
	}
	public void setREVISION(int rEVISION) {
		REVISION = rEVISION;
	}
	public String getPOSITION() {
		return POSITION;
	}
	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getDIGITAL_INFO() {
		return DIGITAL_INFO;
	}
	public void setDIGITAL_INFO(String dIGITAL_INFO) {
		DIGITAL_INFO = dIGITAL_INFO;
	}
	public AnalogInfo getANALOG_INFO() {
		return ANALOG_INFO;
	}
	public void setANALOG_INFO(AnalogInfo aNALOG_INFO) {
		ANALOG_INFO = aNALOG_INFO;
	}
	public String getRAW() {
		return RAW;
	}
	public void setRAW(String rAW) {
		RAW = rAW;
	}
	public String getCRC() {
		return CRC;
	}
	public void setCRC(String cRC) {
		CRC = cRC;
	}
	
	
	
	


}
