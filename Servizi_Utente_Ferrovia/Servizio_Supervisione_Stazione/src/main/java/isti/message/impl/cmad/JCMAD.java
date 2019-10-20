package isti.message.impl.cmad;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import isti.message.impl.ill.JMADILL;
import isti.message.impl.red.JMadRed;

import javax.persistence.*;

@XmlRootElement(name = "DatiCMAD", namespace = "http://stingray.isti.cnr.it/docs/xsd/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JCAMD", propOrder = {
		"Id",
		"CMAD_HEADER",
		"CMAD_TYPE",
		"CMAD_REVISION",
		"CMAD_POSITION",
		"CMAD_DESCRIPTION",
		"CMAD_LONGITUDE",
		"CMAD_LATITUDE",
		"CMAD_DIGITAL_INFO",
		"CMAD_ANALOG_INFO",
		"CMAD_RAW",
		"CMAD_CRC",
		"listred",
		"listill"

})
@Entity(name="Jcmad" )
@Table(name = "CMAD") 
@NamedQueries({
	@NamedQuery(name="JCMAD.findAll",
			query="SELECT c FROM Jcmad c"),
	@NamedQuery(name="JCMAD.findAllMac",
	query="SELECT c FROM Jcmad c WHERE c.Id.MAC_ADR= ?1 ORDER BY c.Id.DATE "),
	@NamedQuery(name="JCMAD.findMacBetweenTime",
	query="SELECT c FROM Jcmad c WHERE c.Id.MAC_ADR= ?1  and c.Id.DATE BETWEEN ?2 AND ?3 ORDER BY c.Id.DATE "),



}) 
/*@NamedNativeQueries({ORDER BY c.CMAD_DATE  and c.CMAD_DATE BETWEEN ?2 AND ?3
    @NamedNativeQuery(
            name    =   "updateCMAD",
            query   =   "UPDATE c SET c = ?, c.CMAD_ANALOG_INFO= ?, c.CMAD_CRC= ?, c.CMAD_DESCRIPTION= ?, c.CMAD_DIGITAL_INFO= ?, c.CMAD_HEADER= ?, c.CMAD_LATITUDE= ?, c.CMAD_LONGITUDE= ?, c.CMAD_POSITION= ?, c.CMAD_RAW= ?, c.CMAD_REVISION= ?, c.CMAD_TYPE  WHERE c.MAC_ADR = ? FROM Jcmad c"
            ,resultSetMapping = "updateResult"
    )
})*/
public class JCMAD  implements java.io.Serializable{

	@XmlElement(/*name = "CMAD",*/ required = true)
	@EmbeddedId
	JCMADID Id;

	@XmlElement(name = "CMAD_HEADER", required = true)
	String CMAD_HEADER;


	@XmlElement(name = "CMAD_TYPE", required = true)
	int CMAD_TYPE = 0;
	@XmlElement(name = "CMAD_REVISION", required = true)
	int CMAD_REVISION;
	@XmlElement(name = "CMAD_POSITION", required = true)
	String CMAD_POSITION;
	@XmlElement(name = "CMAD_DESCRIPTION", required = true)
	String CMAD_DESCRIPTION;
	@XmlElement(name = "CMAD_LONGITUDE", required = true)
	String  CMAD_LONGITUDE;
	@XmlElement(name = "CMAD_LATITUDE", required = true)
	String CMAD_LATITUDE;
	@XmlElement(name = "CMAD_DIGITAL_INFO", required = true)
	String CMAD_DIGITAL_INFO;


	@XmlElement(name = "CMAD_ANALOG_INFO", required = true)
	@Embedded
	CMADAnalogInfo CMAD_ANALOG_INFO;

	@XmlElement(name = "CMAD_RAW_BASE64", required = true)
	String CMAD_RAW;
	@XmlElement(name = "CMAD_CRC", required = true)
	String CMAD_CRC;

	@XmlElementWrapper(name="ListMadRed")
	@XmlElement(name="DatiMadRed")
	@OneToMany(cascade = CascadeType.ALL,
	orphanRemoval = true, targetEntity=isti.message.impl.red.JMadRed.class)
	@JoinColumns({@JoinColumn(name = "MAC_CAMD", referencedColumnName = "MAC_ADR"),
		@JoinColumn(name = "DATE_CMAD", referencedColumnName = "DATE")	
	})
	List<JMadRed> listred;

	@XmlElementWrapper(name="ListMadIll")
	@XmlElement(name="DatiMadIll")
	@OneToMany(cascade = CascadeType.ALL,
	orphanRemoval = true, targetEntity=isti.message.impl.ill.JMADILL.class)
	@JoinColumns({@JoinColumn(name = "MAC_CAMD", referencedColumnName = "MAC_ADR"),
		@JoinColumn(name = "DATE_CMAD", referencedColumnName = "DATE")	
	})
	List<JMADILL> listill;

	public JCMAD() {
	}




	/**
	 * @param cMAD_HEADER
	 * @param mAC_ADR
	 * @param cMAD_TYPE
	 * @param cMAD_REVISION
	 * @param cMAD_POSITION
	 * @param cMAD_DESCRIPTION
	 * @param cMAD_LONGITUDE
	 * @param cMAD_LATITUDE
	 * @param cMAD_DIGITAL_INFO
	 * @param cMAD_ANALOG_INFO
	 * @param cMAD_Dummy
	 * @param cMAD_CRC
	 */
	public JCMAD(String cMAD_HEADER, String mAC_ADR, int cMAD_TYPE, int cMAD_REVISION, String cMAD_POSITION,
			String cMAD_DESCRIPTION, String cMAD_LONGITUDE, String cMAD_LATITUDE, String cMAD_DIGITAL_INFO,
			String cCMAD_RAW, int cMAD_CRC) {
		CMAD_HEADER = cMAD_HEADER;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date today = new Date();

			Date todayWithZeroTime = formatter.parse(formatter.format(today));

			Id = new JCMADID(mAC_ADR,  todayWithZeroTime);

			CMAD_TYPE = cMAD_TYPE;
			CMAD_REVISION = cMAD_REVISION;
			CMAD_POSITION = cMAD_POSITION;
			CMAD_DESCRIPTION = cMAD_DESCRIPTION;
			CMAD_LONGITUDE = cMAD_LONGITUDE;
			CMAD_LATITUDE = cMAD_LATITUDE;
			CMAD_DIGITAL_INFO = cMAD_DIGITAL_INFO;

			CMAD_RAW = cCMAD_RAW;
			CMAD_CRC = String.valueOf(cMAD_CRC);

		} catch (ParseException e) {
			org.apache.log4j.Logger.getLogger(JCMAD.class).error(e);
		}
	}

	public void Init(String cMAD_HEADER, String mAC_ADR, int cMAD_TYPE, int cMAD_REVISION, String cMAD_POSITION,
			String cMAD_DESCRIPTION, String cMAD_LONGITUDE, String cMAD_LATITUDE, String cMAD_DIGITAL_INFO,
			String cCMAD_RAW, int cMAD_CRC) {
		CMAD_HEADER = cMAD_HEADER;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date today = new Date();

			Date todayWithZeroTime = formatter.parse(formatter.format(today));

			Id = new JCMADID(mAC_ADR,  todayWithZeroTime);

			CMAD_TYPE = cMAD_TYPE;
			CMAD_REVISION = cMAD_REVISION;
			CMAD_POSITION = cMAD_POSITION;
			CMAD_DESCRIPTION = cMAD_DESCRIPTION;
			CMAD_LONGITUDE = cMAD_LONGITUDE;
			CMAD_LATITUDE = cMAD_LATITUDE;
			CMAD_DIGITAL_INFO = cMAD_DIGITAL_INFO;

			CMAD_RAW = cCMAD_RAW;
			CMAD_CRC = String.valueOf(cMAD_CRC);

		} catch (ParseException e) {
			org.apache.log4j.Logger.getLogger(JCMAD.class).error(e);
		}
	}


	public String getCMAD_HEADER() {
		return CMAD_HEADER;
	}
	public void setCMAD_HEADER(String cMAD_HEADER) {
		CMAD_HEADER = cMAD_HEADER;
	}
	/*public String getMAC_ADR() {
		return MAC_ADR;
	}
	public void setMAC_ADR(String mAC_ADR) {
		MAC_ADR = mAC_ADR;
	}*/
	public int getCMAD_TYPE() {
		return CMAD_TYPE;
	}
	public void setCMAD_TYPE(int cMAD_TYPE) {
		CMAD_TYPE = cMAD_TYPE;
	}
	public int getCMAD_REVISION() {
		return CMAD_REVISION;
	}
	public void setCMAD_REVISION(int cMAD_REVISION) {
		CMAD_REVISION = cMAD_REVISION;
	}
	public String getCMAD_POSITION() {
		return CMAD_POSITION;
	}
	public void setCMAD_POSITION(String cMAD_POSITION) {
		CMAD_POSITION = cMAD_POSITION;
	}
	public String getCMAD_DESCRIPTION() {
		return CMAD_DESCRIPTION;
	}
	public void setCMAD_DESCRIPTION(String cMAD_DESCRIPTION) {
		CMAD_DESCRIPTION = cMAD_DESCRIPTION;
	}
	public String getCMAD_LONGITUDE() {
		return CMAD_LONGITUDE;
	}
	public void setCMAD_LONGITUDE(String cMAD_LONGITUDE) {
		CMAD_LONGITUDE = cMAD_LONGITUDE;
	}
	public String getCMAD_LATITUDE() {
		return CMAD_LATITUDE;
	}
	public void setCMAD_LATITUDE(String cMAD_LATITUDE) {
		CMAD_LATITUDE = cMAD_LATITUDE;
	}
	public String getCMAD_DIGITAL_INFO() {
		return CMAD_DIGITAL_INFO;
	}
	public void setCMAD_DIGITAL_INFO(String cMAD_DIGITAL_INFO) {
		CMAD_DIGITAL_INFO = cMAD_DIGITAL_INFO;
	}
	public CMADAnalogInfo getCMAD_ANALOG_INFO() {
		return CMAD_ANALOG_INFO;
	}
	public void setCMAD_ANALOG_INFO(CMADAnalogInfo cMAD_ANALOG_INFO) {
		CMAD_ANALOG_INFO = cMAD_ANALOG_INFO;
	}
	public String getCMAD_RAW() {
		return CMAD_RAW;
	}
	public void setCMAD_RAW(String cMAD_Dummy) {
		CMAD_RAW = cMAD_Dummy;
	}
	public String getCMAD_CRC() {
		return CMAD_CRC;
	}
	public void setCMAD_CRC(String cMAD_CRC) {
		CMAD_CRC = cMAD_CRC;
	}




	public JCMADID getId() {
		return Id;
	}




	public void setId(JCMADID id) {
		Id = id;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CMAD_ANALOG_INFO == null) ? 0 : CMAD_ANALOG_INFO.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((listill == null) ? 0 : listill.hashCode());
		result = prime * result + ((listred == null) ? 0 : listred.hashCode());
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
		JCMAD other = (JCMAD) obj;
		if (CMAD_ANALOG_INFO == null) {
			if (other.CMAD_ANALOG_INFO != null)
				return false;
		} else if (!CMAD_ANALOG_INFO.equals(other.CMAD_ANALOG_INFO))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (listill == null) {
			if (other.listill != null)
				return false;
		} else if (!listill.containsAll(other.listill))
			return false;
		if (listred == null) {
			if (other.listred != null)
				return false;
		} else if (!listred.containsAll(other.listred))
			return false;
		return true;
	}

	



	public List<JMadRed> getListred() {
		return listred;
	}




	public void setListred(List<JMadRed> listred) {
		this.listred = listred;
	}




	public List<JMADILL> getListill() {
		return listill;
	}




	public void setListill(List<JMADILL> listill) {
		this.listill = listill;
	}




	public byte[] toByte() {
		return null;
	}





}
