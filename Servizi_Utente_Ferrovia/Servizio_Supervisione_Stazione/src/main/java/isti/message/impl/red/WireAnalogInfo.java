package isti.message.impl.red;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WireAnalogInfo", propOrder = {
		"Temperatura1",
		"Temperatura2",
		"ValoreCorrenteCavo1",
		"ValoreCorrenteCavo2",
		"ValoreCorrenteCavo3",
		"ValoreCorrenteCavo4",

		"ValoreCorrenteCavo5",
		"ValoreCorrenteCavo6",
		"ValoreCorrenteCavo7",
		"ValoreCorrenteCavo8",

		"ValoreCorrenteCavo9",
		"ValoreCorrenteCavo10",
		"ValoreCorrenteCavo11",
		"ValoreCorrenteCavo12",
				
    
})
@Embeddable
public class WireAnalogInfo {

	@XmlElement(required = true)
	int Temperatura1=0;
	@XmlElement(required = true)
	int Temperatura2=0; 
	@XmlElement(required = true)
	int ValoreCorrenteCavo1 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo2 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo3 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo4 =0;
	
	@XmlElement(required = true)
	int ValoreCorrenteCavo5 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo6 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo7 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo8 =0;
	
	@XmlElement(required = true)
	int ValoreCorrenteCavo9 =0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo10=0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo11=0;
	@XmlElement(required = true)
	int ValoreCorrenteCavo12=0;
	
	WireAnalogInfo(){
		
	}
	
	

	/**
	 * @param temperatura1
	 * @param temperatura2
	 * @param valoreCorrenteCavo1
	 * @param valoreCorrenteCavo2
	 * @param valoreCorrenteCavo3
	 * @param valoreCorrenteCavo4
	 * @param valoreCorrenteCavo5
	 * @param valoreCorrenteCavo6
	 * @param valoreCorrenteCavo7
	 * @param valoreCorrenteCavo8
	 * @param valoreCorrenteCavo9
	 * @param valoreCorrenteCavo10
	 * @param valoreCorrenteCavo11
	 * @param valoreCorrenteCavo12
	 */
	public WireAnalogInfo(int temperatura1, int temperatura2, int valoreCorrenteCavo1, int valoreCorrenteCavo2,
			int valoreCorrenteCavo3, int valoreCorrenteCavo4, int valoreCorrenteCavo5, int valoreCorrenteCavo6,
			int valoreCorrenteCavo7, int valoreCorrenteCavo8, int valoreCorrenteCavo9, int valoreCorrenteCavo10,
			int valoreCorrenteCavo11, int valoreCorrenteCavo12) {
		super();
		Temperatura1 = temperatura1;
		Temperatura2 = temperatura2;
		ValoreCorrenteCavo1 = valoreCorrenteCavo1;
		ValoreCorrenteCavo2 = valoreCorrenteCavo2;
		ValoreCorrenteCavo3 = valoreCorrenteCavo3;
		ValoreCorrenteCavo4 = valoreCorrenteCavo4;
		ValoreCorrenteCavo5 = valoreCorrenteCavo5;
		ValoreCorrenteCavo6 = valoreCorrenteCavo6;
		ValoreCorrenteCavo7 = valoreCorrenteCavo7;
		ValoreCorrenteCavo8 = valoreCorrenteCavo8;
		ValoreCorrenteCavo9 = valoreCorrenteCavo9;
		ValoreCorrenteCavo10 = valoreCorrenteCavo10;
		ValoreCorrenteCavo11 = valoreCorrenteCavo11;
		ValoreCorrenteCavo12 = valoreCorrenteCavo12;
	}



	public int getTemperatura1() {
		return Temperatura1;
	}

	public void setTemperatura1(int temperatura1) {
		Temperatura1 = temperatura1;
	}

	public int getTemperatura2() {
		return Temperatura2;
	}

	public void setTemperatura2(int temperatura2) {
		Temperatura2 = temperatura2;
	}

	public int getValoreCorrenteCavo1() {
		return ValoreCorrenteCavo1;
	}

	public void setValoreCorrenteCavo1(int valoreCorrenteCavo1) {
		ValoreCorrenteCavo1 = valoreCorrenteCavo1;
	}

	public int getValoreCorrenteCavo2() {
		return ValoreCorrenteCavo2;
	}

	public void setValoreCorrenteCavo2(int valoreCorrenteCavo2) {
		ValoreCorrenteCavo2 = valoreCorrenteCavo2;
	}

	public int getValoreCorrenteCavo3() {
		return ValoreCorrenteCavo3;
	}

	public void setValoreCorrenteCavo3(int valoreCorrenteCavo3) {
		ValoreCorrenteCavo3 = valoreCorrenteCavo3;
	}

	public int getValoreCorrenteCavo4() {
		return ValoreCorrenteCavo4;
	}

	public void setValoreCorrenteCavo4(int valoreCorrenteCavo4) {
		ValoreCorrenteCavo4 = valoreCorrenteCavo4;
	}

	public int getValoreCorrenteCavo5() {
		return ValoreCorrenteCavo5;
	}

	public void setValoreCorrenteCavo5(int valoreCorrenteCavo5) {
		ValoreCorrenteCavo5 = valoreCorrenteCavo5;
	}

	public int getValoreCorrenteCavo6() {
		return ValoreCorrenteCavo6;
	}

	public void setValoreCorrenteCavo6(int valoreCorrenteCavo6) {
		ValoreCorrenteCavo6 = valoreCorrenteCavo6;
	}

	public int getValoreCorrenteCavo7() {
		return ValoreCorrenteCavo7;
	}

	public void setValoreCorrenteCavo7(int valoreCorrenteCavo7) {
		ValoreCorrenteCavo7 = valoreCorrenteCavo7;
	}

	public int getValoreCorrenteCavo8() {
		return ValoreCorrenteCavo8;
	}

	public void setValoreCorrenteCavo8(int valoreCorrenteCavo8) {
		ValoreCorrenteCavo8 = valoreCorrenteCavo8;
	}

	public int getValoreCorrenteCavo9() {
		return ValoreCorrenteCavo9;
	}

	public void setValoreCorrenteCavo9(int valoreCorrenteCavo9) {
		ValoreCorrenteCavo9 = valoreCorrenteCavo9;
	}

	public int getValoreCorrenteCavo10() {
		return ValoreCorrenteCavo10;
	}

	public void setValoreCorrenteCavo10(int valoreCorrenteCavo10) {
		ValoreCorrenteCavo10 = valoreCorrenteCavo10;
	}

	public int getValoreCorrenteCavo11() {
		return ValoreCorrenteCavo11;
	}

	public void setValoreCorrenteCavo11(int valoreCorrenteCavo11) {
		ValoreCorrenteCavo11 = valoreCorrenteCavo11;
	}

	public int getValoreCorrenteCavo12() {
		return ValoreCorrenteCavo12;
	}

	public void setValoreCorrenteCavo12(int valoreCorrenteCavo12) {
		ValoreCorrenteCavo12 = valoreCorrenteCavo12;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WireAnalogInfo other = (WireAnalogInfo) obj;
		if (Temperatura1 != other.Temperatura1)
			return false;
		if (Temperatura2 != other.Temperatura2)
			return false;
		if (ValoreCorrenteCavo1 != other.ValoreCorrenteCavo1)
			return false;
		if (ValoreCorrenteCavo10 != other.ValoreCorrenteCavo10)
			return false;
		if (ValoreCorrenteCavo11 != other.ValoreCorrenteCavo11)
			return false;
		if (ValoreCorrenteCavo12 != other.ValoreCorrenteCavo12)
			return false;
		if (ValoreCorrenteCavo2 != other.ValoreCorrenteCavo2)
			return false;
		if (ValoreCorrenteCavo3 != other.ValoreCorrenteCavo3)
			return false;
		if (ValoreCorrenteCavo4 != other.ValoreCorrenteCavo4)
			return false;
		if (ValoreCorrenteCavo5 != other.ValoreCorrenteCavo5)
			return false;
		if (ValoreCorrenteCavo6 != other.ValoreCorrenteCavo6)
			return false;
		if (ValoreCorrenteCavo7 != other.ValoreCorrenteCavo7)
			return false;
		if (ValoreCorrenteCavo8 != other.ValoreCorrenteCavo8)
			return false;
		if (ValoreCorrenteCavo9 != other.ValoreCorrenteCavo9)
			return false;
		return true;
	}

	
	
	

}
