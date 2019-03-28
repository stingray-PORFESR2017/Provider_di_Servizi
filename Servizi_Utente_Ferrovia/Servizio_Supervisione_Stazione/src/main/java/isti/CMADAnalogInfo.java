package isti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CMADAnalogInfo", propOrder = {
    "TempEst",
    "Lux",
    "TempSuolo",
    "Tensione",
    "Corrente",
    "PotenzaAttiva",
    "PotenzaReattiva",
    "FattorePotenza",
    "EnergiaAttiva",
    "EnergiaReattiva"
    
})
@Embeddable
public class CMADAnalogInfo implements Serializable{
	
    @XmlElement(name = "TempEst", required = true)
	float TempEst;
    @XmlElement(name = "Lux", required = true)
	int Lux;
    @XmlElement(name = "TempSuolo", required = true)
	float TempSuolo;
    
    @XmlElement(name = "Tensione", required = true)
    @AttributeOverrides({
        @AttributeOverride(name="L1",
                           column=@Column(name="Tensione1")),
        @AttributeOverride(name="L2",
                           column=@Column(name="Tensione2")),
        @AttributeOverride(name="L3",
        column=@Column(name="Tensione3"))
    })
    @Embedded L Tensione;

    
    @XmlElement(name = "Corrente", required = true)
    @AttributeOverrides({
        @AttributeOverride(name="L1",
                           column=@Column(name="Corrente1")),
        @AttributeOverride(name="L2",
                           column=@Column(name="Corrente2")),
        @AttributeOverride(name="L3",
        column=@Column(name="Corrente3"))
    })
    @Embedded L Corrente;
    
    
    @XmlElement(name = "PotenzaAttiva", required = true)
    @AttributeOverrides({
        @AttributeOverride(name="L1",
                           column=@Column(name="PotenzaAttiva1")),
        @AttributeOverride(name="L2",
                           column=@Column(name="PotenzaAttiva2")),
        @AttributeOverride(name="L3",
        column=@Column(name="PotenzaAttiva3"))
    })
    @Embedded L PotenzaAttiva;
    
    
    @XmlElement(name = "PotenzaReattiva", required = true)
    @AttributeOverrides({
        @AttributeOverride(name="L1",
                           column=@Column(name="PotenzaReattiva1")),
        @AttributeOverride(name="L2",
                           column=@Column(name="PotenzaReattiva2")),
        @AttributeOverride(name="L3",
        column=@Column(name="PotenzaReattiva3"))
    })
    @Embedded L PotenzaReattiva;
    
    @XmlElement(name = "FattorePotenza", required = true)
    @AttributeOverrides({
        @AttributeOverride(name="L1",
                           column=@Column(name="FattorePotenza1")),
        @AttributeOverride(name="L2",
                           column=@Column(name="FattorePotenza2")),
        @AttributeOverride(name="L3",
        column=@Column(name="FattorePotenza3"))
    })
    @Embedded L FattorePotenza;

    @XmlElement(name = "EnergiaAttiva", required = true)
	float EnergiaAttiva;
    @XmlElement(name = "EnergiaReattiva", required = true)
	float EnergiaReattiva;
    
    
    public CMADAnalogInfo(){
    	
    }
    
    
    

	/**
	 * @param tempEst
	 * @param lux
	 * @param tempSuolo
	 * @param tensione
	 * @param corrente
	 * @param potenzaAttiva
	 * @param potenzaReattiva
	 * @param fattorePotenza
	 * @param energiaAttiva
	 * @param energiaReattiva
	 */
	public CMADAnalogInfo(float tempEst, int lux, float tempSuolo, L tensione, L corrente,
			L potenzaAttiva, L potenzaReattiva, L fattorePotenza, float energiaAttiva,
			float energiaReattiva) {

		TempEst = tempEst;
		Lux = lux;
		TempSuolo = tempSuolo;
		Tensione = tensione;
		Corrente = corrente;
		PotenzaAttiva = potenzaAttiva;
		PotenzaReattiva = potenzaReattiva;
		FattorePotenza = fattorePotenza;
		EnergiaAttiva = energiaAttiva;
		EnergiaReattiva = energiaReattiva;
	}
	
	public List<L> set(L l){
		List<L> d = new ArrayList<L>();
		d.add(l);
		return d;
	}
	
	public float getTempEst() {
		return TempEst;
	}
	public void setTempEst(float tempEst) {
		TempEst = tempEst;
	}
	public int getLux() {
		return Lux;
	}
	public void setLux(int lux) {
		Lux = lux;
	}
	public float getTempSuolo() {
		return TempSuolo;
	}
	public void setTempSuolo(float tempSuolo) {
		TempSuolo = tempSuolo;
	}
	public L getTensione() {
		return Tensione;
	}
	public void setTensione(L tensione) {
		Tensione = tensione;
	}
	public L getCorrente() {
		return Corrente;
	}
	public void setCorrente(L corrente) {
		Corrente = corrente;
	}
	public L getPotenzaAttiva() {
		return PotenzaAttiva;
	}
	public void setPotenzaAttiva(L potenzaAttiva) {
		PotenzaAttiva = potenzaAttiva;
	}
	public L getPotenzaReattiva() {
		return PotenzaReattiva;
	}
	public void setPotenzaReattiva(L potenzaReattiva) {
		PotenzaReattiva = potenzaReattiva;
	}
	public L getFattorePotenza() {
		return FattorePotenza;
	}
	public void setFattorePotenza(L fattorePotenza) {
		FattorePotenza = fattorePotenza;
	}
	public float getEnergiaAttiva() {
		return EnergiaAttiva;
	}
	public void setEnergiaAttiva(float energiaAttiva) {
		EnergiaAttiva = energiaAttiva;
	}
	public float getEnergiaReattiva() {
		return EnergiaReattiva;
	}
	public void setEnergiaReattiva(float energiaReattiva) {
		EnergiaReattiva = energiaReattiva;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Corrente == null) ? 0 : Corrente.hashCode());
		result = prime * result + Float.floatToIntBits(EnergiaAttiva);
		result = prime * result + Float.floatToIntBits(EnergiaReattiva);
		result = prime * result + ((FattorePotenza == null) ? 0 : FattorePotenza.hashCode());
		result = prime * result + Lux;
		result = prime * result + ((PotenzaAttiva == null) ? 0 : PotenzaAttiva.hashCode());
		result = prime * result + ((PotenzaReattiva == null) ? 0 : PotenzaReattiva.hashCode());
		result = prime * result + Float.floatToIntBits(TempEst);
		result = prime * result + Float.floatToIntBits(TempSuolo);
		result = prime * result + ((Tensione == null) ? 0 : Tensione.hashCode());
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
		CMADAnalogInfo other = (CMADAnalogInfo) obj;
		if (Corrente == null) {
			if (other.Corrente != null)
				return false;
		} else if (!Corrente.equals(other.Corrente))
			return false;
		if (Float.floatToIntBits(EnergiaAttiva) != Float.floatToIntBits(other.EnergiaAttiva))
			return false;
		if (Float.floatToIntBits(EnergiaReattiva) != Float.floatToIntBits(other.EnergiaReattiva))
			return false;
		if (FattorePotenza == null) {
			if (other.FattorePotenza != null)
				return false;
		} else if (!FattorePotenza.equals(other.FattorePotenza))
			return false;
		if (Lux != other.Lux)
			return false;
		if (PotenzaAttiva == null) {
			if (other.PotenzaAttiva != null)
				return false;
		} else if (!PotenzaAttiva.equals(other.PotenzaAttiva))
			return false;
		if (PotenzaReattiva == null) {
			if (other.PotenzaReattiva != null)
				return false;
		} else if (!PotenzaReattiva.equals(other.PotenzaReattiva))
			return false;
		if (Float.floatToIntBits(TempEst) != Float.floatToIntBits(other.TempEst))
			return false;
		if (Float.floatToIntBits(TempSuolo) != Float.floatToIntBits(other.TempSuolo))
			return false;
		if (Tensione == null) {
			if (other.Tensione != null)
				return false;
		} else if (!Tensione.equals(other.Tensione))
			return false;
		return true;
	}
    
    

}
