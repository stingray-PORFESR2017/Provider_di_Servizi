package isti.message.impl.red;

import java.util.Date;

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
	@Embedded WireAnalogInfo WIRE_ANALOG_INFO;


	@XmlElement(name = "RAW_BASE64", required = true)
	String RAW;
	@XmlElement(required = true)
	String CRC;
	
	/*@XmlTransient
	 @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "MAC_CAMD", referencedColumnName = "MAC_ADR"),
		@JoinColumn(name = "DATE_CMAD", referencedColumnName = "DATE")	
	})
	     JCMAD localJCMAD;
*/

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
			String rAW, String cRC) {
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
		RAW = rAW;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CRC == null) ? 0 : CRC.hashCode());
		result = prime * result + ((DESCRIPTION == null) ? 0 : DESCRIPTION.hashCode());
		result = prime * result + ((DIGITAL_INFO == null) ? 0 : DIGITAL_INFO.hashCode());
		result = prime * result + ((HEADER == null) ? 0 : HEADER.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((LATITUDE == null) ? 0 : LATITUDE.hashCode());
		result = prime * result + ((LONGITUDE == null) ? 0 : LONGITUDE.hashCode());
		result = prime * result + ((POSITION == null) ? 0 : POSITION.hashCode());
		result = prime * result + ((RAW == null) ? 0 : RAW.hashCode());
		result = prime * result + REVISION;
		result = prime * result + TYPE;
		result = prime * result + ((WIRE_ANALOG_INFO == null) ? 0 : WIRE_ANALOG_INFO.hashCode());
		result = prime * result + ((WIRE_DIGITAL_INFO == null) ? 0 : WIRE_DIGITAL_INFO.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JMadRed other = (JMadRed) obj;
		if (CRC == null) {
			if (other.CRC != null)
				return false;
		} else if (!CRC.equals(other.CRC))
			return false;
		if (DESCRIPTION == null) {
			if (other.DESCRIPTION != null)
				return false;
		} else if (!DESCRIPTION.equals(other.DESCRIPTION))
			return false;
		if (DIGITAL_INFO == null) {
			if (other.DIGITAL_INFO != null)
				return false;
		} else if (!DIGITAL_INFO.equals(other.DIGITAL_INFO))
			return false;
		if (HEADER == null) {
			if (other.HEADER != null)
				return false;
		} else if (!HEADER.equals(other.HEADER))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (LATITUDE == null) {
			if (other.LATITUDE != null)
				return false;
		} else if (!LATITUDE.equals(other.LATITUDE))
			return false;
		if (LONGITUDE == null) {
			if (other.LONGITUDE != null)
				return false;
		} else if (!LONGITUDE.equals(other.LONGITUDE))
			return false;
		if (POSITION == null) {
			if (other.POSITION != null)
				return false;
		} else if (!POSITION.equals(other.POSITION))
			return false;
		if (REVISION != other.REVISION)
			return false;
		if (TYPE != other.TYPE)
			return false;
		if (WIRE_ANALOG_INFO == null) {
			if (other.WIRE_ANALOG_INFO != null)
				return false;
		} else if (!WIRE_ANALOG_INFO.equals(other.WIRE_ANALOG_INFO))
			return false;
		if (WIRE_DIGITAL_INFO == null) {
			if (other.WIRE_DIGITAL_INFO != null)
				return false;
		} else if (!WIRE_DIGITAL_INFO.equals(other.WIRE_DIGITAL_INFO))
			return false;
		return true;
	}

/*
	public JCMAD getLocalJCMAD() {
		return localJCMAD;
	}


	public void setLocalJCMAD(JCMAD localJCMAD) {
		this.localJCMAD = localJCMAD;
	}

*/
	

}
