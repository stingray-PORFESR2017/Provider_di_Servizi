package isti;

import java.io.Serializable;

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
public class L implements Serializable{
	
	
	@XmlElement(name = "L1", required = true)
	float L1;
	@XmlElement(name = "L2", required = true)
	float L2;
	@XmlElement(name = "L3", required = true)
	float L3;
	
	
	
	
	
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
	
	
	

}
