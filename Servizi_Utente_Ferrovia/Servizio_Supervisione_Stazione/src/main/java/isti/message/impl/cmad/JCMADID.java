package isti.message.impl.cmad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JCMADID", propOrder = {
    "MAC_ADR",
    "CMAD_DATE"    
})
@Embeddable
public class JCMADID implements Serializable {


	@XmlElement(name = "MAC_ADR", required = true)
	String MAC_ADR;
	@XmlElement(name = "CMAD_DATE", required = true)
	@Temporal(TemporalType.TIMESTAMP)
	Date CMAD_DATE;

	
	/**
	 * 
	 */
	public JCMADID() {
		
	}
	
	
	/**
	 * @param mAC_ADR
	 * @param cMAD_DATE
	 */
	public JCMADID(String mAC_ADR, Date cMAD_DATE) {
		super();
		MAC_ADR = mAC_ADR;
		CMAD_DATE = cMAD_DATE;
	}


	public String getMAC_ADR() {
		return MAC_ADR;
	}
	public void setMAC_ADR(String mAC_ADR) {
		MAC_ADR = mAC_ADR;
	}
	public Date getCMAD_DATE() {
		return CMAD_DATE;
	}
	public void setCMAD_DATE(Date cMAD_DATE) {
		CMAD_DATE = cMAD_DATE;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JCMADID other = (JCMADID) obj;
		if (MAC_ADR == null) {
			if (other.MAC_ADR != null)
				return false;
		} else if (!MAC_ADR.equals(other.MAC_ADR))
			return false;
		if (CMAD_DATE.compareTo(other.CMAD_DATE) != 0)
			return false;
		return true;
	}
	
	
	
}
