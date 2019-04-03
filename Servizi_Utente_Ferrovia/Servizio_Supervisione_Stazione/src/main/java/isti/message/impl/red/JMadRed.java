package isti.message.impl.red;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import isti.message.impl.cmad.JCMADID;

@XmlRootElement(name = "DatiMadRed", namespace = "http://stingray.isti.cnr.it/docs/xsd/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MadRed", propOrder = {
		"Id","HEADER","TYPE","REVISION","POSITION","DESCRIPTION","LONGITUDE","LATITUDE","DIGITAL_INFO","WIRE_DIGITAL_INFO","WIRE_ANALOG_INFO","RAW","CRC"
})
@Entity(name="JMadRed" )
@Table(name = "MadRed") 
public class JMadRed  implements java.io.Serializable {

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
	String WIRE_DIGITAL_INFO;


	@XmlElement(required = true)
	WireAnalogInfo WIRE_ANALOG_INFO;


	@XmlElement(name = "RAW_BASE64", required = true)
	String RAW;
	@XmlElement(required = true)
	String CRC;


	public JMadRed(){

	}


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
	 * @param wIRE_DIGITAL_INFO
	 * @param wIRE_ANALOG_INFO
	 * @param cRC
	 */
	public JMadRed( String cmAC, String hEADER, int tYPE, int rEVISION, String pOSITION, String dESCRIPTION,
			String lONGITUDE, String lATITUDE, String dIGITAL_INFO, String wIRE_DIGITAL_INFO,
			String mess, String cRC) {
		Id = new JCMADID(cmAC,  new Date());
		HEADER = hEADER;
		TYPE = tYPE;
		REVISION = rEVISION;
		POSITION = pOSITION;
		DESCRIPTION = dESCRIPTION;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		DIGITAL_INFO = dIGITAL_INFO;
		WIRE_DIGITAL_INFO = wIRE_DIGITAL_INFO;

		CRC = cRC;
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
	public String getWIRE_DIGITAL_INFO() {
		return WIRE_DIGITAL_INFO;
	}
	public void setWIRE_DIGITAL_INFO(String wIRE_DIGITAL_INFO) {
		WIRE_DIGITAL_INFO = wIRE_DIGITAL_INFO;
	}
	public WireAnalogInfo getWIRE_ANALOG_INFO() {
		return WIRE_ANALOG_INFO;
	}
	public void setWIRE_ANALOG_INFO(WireAnalogInfo wIRE_ANALOG_INFO) {
		WIRE_ANALOG_INFO = wIRE_ANALOG_INFO;
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
