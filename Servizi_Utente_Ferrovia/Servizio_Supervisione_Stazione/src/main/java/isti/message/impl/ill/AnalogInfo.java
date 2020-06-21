package isti.message.impl.ill;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import isti.message.util.Service;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalogInfo", propOrder = {
		"ComandoLampada",
		"PotenzaLampada",
		"VitaLampada",
		"TensioneLampada",
		"CorrenteLampada",
		"SCORTA",
		"FattorePotenza"


})
@Embeddable
public class AnalogInfo implements Serializable {

	@XmlElement(required = true)
	int ComandoLampada;
	@XmlElement(required = true)
	int PotenzaLampada;
	@XmlElement(required = true)
	int VitaLampada;
	@XmlElement(required = true)
	int TensioneLampada;
	@XmlElement(required = true)
	int CorrenteLampada;
	@XmlElement(required = true)
	int SCORTA = 0;
	@XmlElement(required = true)
	int FattorePotenza;

	public AnalogInfo(){
		
	}

	/**
	 * @param comandoLampada
	 * @param potenzaLampada
	 * @param vitaLampada
	 * @param tensioneLampada
	 * @param correnteLampada
	 * @param sCORTA
	 */
	public AnalogInfo(int comandoLampada, int potenzaLampada, int vitaLampada,int fattorePotenza, int tensioneLampada, int correnteLampada) {
		super();
		ComandoLampada = comandoLampada;
		PotenzaLampada = potenzaLampada;
		VitaLampada = vitaLampada;
		TensioneLampada = tensioneLampada;
		CorrenteLampada = correnteLampada;
		FattorePotenza = fattorePotenza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ComandoLampada, CorrenteLampada, PotenzaLampada, FattorePotenza, TensioneLampada, VitaLampada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalogInfo other = (AnalogInfo) obj;
		return ComandoLampada == other.ComandoLampada && CorrenteLampada == other.CorrenteLampada
				&& PotenzaLampada == other.PotenzaLampada && FattorePotenza == other.FattorePotenza
				&& TensioneLampada == other.TensioneLampada && VitaLampada == other.VitaLampada;
	}

	public int getComandoLampada() {
		return ComandoLampada;
	}

	public void setComandoLampada(int comandoLampada) {
		ComandoLampada = comandoLampada;
	}

	public int getPotenzaLampada() {
		return PotenzaLampada;
	}

	public void setPotenzaLampada(int potenzaLampada) {
		PotenzaLampada = potenzaLampada;
	}

	public int getVitaLampada() {
		return VitaLampada;
	}

	public void setVitaLampada(int vitaLampada) {
		VitaLampada = vitaLampada;
	}

	public int getTensioneLampada() {
		return TensioneLampada;
	}

	public void setTensioneLampada(int tensioneLampada) {
		TensioneLampada = tensioneLampada;
	}

	public int getCorrenteLampada() {
		return CorrenteLampada;
	}

	public void setCorrenteLampada(int correnteLampada) {
		CorrenteLampada = correnteLampada;
	}

	public int getFattorePotenza() {
		return FattorePotenza;
	}

	public void setFattorePotenza(int fattorePotenza) {
		FattorePotenza = fattorePotenza;
	}
	
	

	public int getSCORTA() {
		return SCORTA;
	}

	public void setSCORTA(int sCORTA) {
		SCORTA = sCORTA;
	}

	@Override
	public String toString() {
		return "ComandoLampada: " + ComandoLampada + "\\n, PotenzaLampada: " + PotenzaLampada + "\\n, VitaLampada: "
				+ VitaLampada + "\\n, TensioneLampada: " + TensioneLampada + "\\n, CorrenteLampada: " + CorrenteLampada
				+ "\\n, FattorePotenza: " + FattorePotenza;
	}
	
	

}
