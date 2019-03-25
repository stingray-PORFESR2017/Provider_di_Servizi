package isti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
public class CMADAnalogInfo implements Serializable{
	
    @XmlElement(name = "TempEst", required = true)
	float TempEst;
    @XmlElement(name = "Lux", required = true)
	int Lux;
    @XmlElement(name = "TempSuolo", required = true)
	float TempSuolo;
    
    @XmlElement(name = "Tensione", required = true)
	List<L> Tensione;

    @XmlElement(name = "Corrente", required = true)
   	List<L> Corrente;
    
    @XmlElement(name = "PotenzaAttiva", required = true)
   	List<L> PotenzaAttiva;

    @XmlElement(name = "PotenzaReattiva", required = true)
   	List<L> PotenzaReattiva;
	
    @XmlElement(name = "FattorePotenza", required = true)
   	List<L> FattorePotenza;

    @XmlElement(name = "EnergiaAttiva", required = true)
	float EnergiaAttiva;
    @XmlElement(name = "EnergiaReattiva", required = true)
	float EnergiaReattiva;
    
    
    
    
    
    

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
		Tensione = set(tensione);
		Corrente = set(corrente);
		PotenzaAttiva = set(potenzaAttiva);
		PotenzaReattiva = set(potenzaReattiva);
		FattorePotenza = set(fattorePotenza);
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
	public List<L> getTensione() {
		return Tensione;
	}
	public void setTensione(List<L> tensione) {
		Tensione = tensione;
	}
	public List<L> getCorrente() {
		return Corrente;
	}
	public void setCorrente(List<L> corrente) {
		Corrente = corrente;
	}
	public List<L> getPotenzaAttiva() {
		return PotenzaAttiva;
	}
	public void setPotenzaAttiva(List<L> potenzaAttiva) {
		PotenzaAttiva = potenzaAttiva;
	}
	public List<L> getPotenzaReattiva() {
		return PotenzaReattiva;
	}
	public void setPotenzaReattiva(List<L> potenzaReattiva) {
		PotenzaReattiva = potenzaReattiva;
	}
	public List<L> getFattorePotenza() {
		return FattorePotenza;
	}
	public void setFattorePotenza(List<L> fattorePotenza) {
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
