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
    
    

}
