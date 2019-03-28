package isti;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "L", propOrder = {
    "L1",
    "L2",
    "L3"
})
@Embeddable
public class L implements Serializable{
	
	
	@XmlElement(name = "L1", required = true)
	float L1;
	@XmlElement(name = "L2", required = true)
	float L2;
	@XmlElement(name = "L3", required = true)
	float L3;
	
	
	public L() {
		
	}
	
	
	/**
	 * @param l1
	 * @param l2
	 * @param l3
	 */
	public L(float l1, float l2, float l3) {
		L1 = l1;
		L2 = l2;
		L3 = l3;
	}
	public float getL1() {
		return L1;
	}
	public void setL1(float l1) {
		L1 = l1;
	}
	public float getL2() {
		return L2;
	}
	public void setL2(float l2) {
		L2 = l2;
	}
	public float getL3() {
		return L3;
	}
	public void setL3(float l3) {
		L3 = l3;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(L1);
		result = prime * result + Float.floatToIntBits(L2);
		result = prime * result + Float.floatToIntBits(L3);
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
		L other = (L) obj;
		if (Float.floatToIntBits(L1) != Float.floatToIntBits(other.L1))
			return false;
		if (Float.floatToIntBits(L2) != Float.floatToIntBits(other.L2))
			return false;
		if (Float.floatToIntBits(L3) != Float.floatToIntBits(other.L3))
			return false;
		return true;
	}
	
	
	

}
