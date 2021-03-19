package it.dinfo.stlab.eai.viaggiatreno;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class JourneyStopDto {

    @JsonProperty("orientamento")
    private String orientamento;

    @JsonProperty("kcNumTreno")
    private Object kcNumTreno;

    @JsonProperty("stazione")
    private String stazione;

    @JsonProperty("id")
    private String id;

    @JsonProperty("listaCorrispondenze")
    private Object listaCorrispondenze;

    @JsonProperty("programmata")
    private Long programmata;

    @JsonProperty("programmataZero")
    private Object programmataZero;

    @JsonProperty("effettiva")
    private Object effettiva;

    @JsonProperty("ritardo")
    private Long ritardo;

    @JsonProperty("partenzaTeoricaZero")
    private Object partenzaTeoricaZero;

    @JsonProperty("arrivoTeoricoZero")
    private Object arrivoTeoricoZero;

    @JsonProperty("partenza_teorica")
    private Object partenzaTeorica;

    @JsonProperty("arrivo_teorico")
    private Long arrivoTeorico;

    @JsonProperty("isNextChanged")
    private Boolean isNextChanged;

    @JsonProperty("partenzaReale")
    private Object partenzaReale;

    @JsonProperty("arrivoReale")
    private Object arrivoReale;

    @JsonProperty("ritardoPartenza")
    private Long ritardoPartenza;

    @JsonProperty("ritardoArrivo")
    private Long ritardoArrivo;

    @JsonProperty("progressivo")
    private Long progressivo;

    @JsonProperty("binarioEffettivoArrivoCodice")
    private Object binarioEffettivoArrivoCodice;

    @JsonProperty("binarioEffettivoArrivoTipo")
    private Object binarioEffettivoArrivoTipo;

    @JsonProperty("binarioEffettivoArrivoDescrizione")
    private Object binarioEffettivoArrivoDescrizione;

    @JsonProperty("binarioProgrammatoArrivoCodice")
    private Object binarioProgrammatoArrivoCodice;

    @JsonProperty("binarioProgrammatoArrivoDescrizione")
    private String binarioProgrammatoArrivoDescrizione;

    @JsonProperty("binarioEffettivoPartenzaCodice")
    private Object binarioEffettivoPartenzaCodice;

    @JsonProperty("binarioEffettivoPartenzaTipo")
    private Object binarioEffettivoPartenzaTipo;

    @JsonProperty("binarioEffettivoPartenzaDescrizione")
    private Object binarioEffettivoPartenzaDescrizione;

    @JsonProperty("binarioProgrammatoPartenzaCodice")
    private Object binarioProgrammatoPartenzaCodice;

    @JsonProperty("binarioProgrammatoPartenzaDescrizione")
    private Object binarioProgrammatoPartenzaDescrizione;

    @JsonProperty("tipoFermata")
    private String tipoFermata;

    @JsonProperty("visualizzaPrevista")
    private Boolean visualizzaPrevista;

    @JsonProperty("nextChanged")
    private Boolean nextChanged;

    @JsonProperty("nextTrattaType")
    private Long nextTrattaType;

    @JsonProperty("actualFermataType")
    private Integer actualFermataType;

    @JsonProperty("materiale_label")
    private Object materialeLabel;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String,Object>();

    @JsonProperty("orientamento")
    public String getOrientamento() {
        return orientamento;
    }

    @JsonProperty("orientamento")
    public void setOrientamento(String orientamento) {
        this.orientamento = orientamento;
    }

    @JsonProperty("kcNumTreno")
    public Object getKcNumTreno() {
        return kcNumTreno;
    }

    @JsonProperty("kcNumTreno")
    public void setKcNumTreno(Object kcNumTreno) {
        this.kcNumTreno = kcNumTreno;
    }

    @JsonProperty("stazione")
    public String getStazione() {
        return stazione;
    }

    @JsonProperty("stazione")
    public void setStazione(String stazione) {
        this.stazione = stazione;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("listaCorrispondenze")
    public Object getListaCorrispondenze() {
        return listaCorrispondenze;
    }

    @JsonProperty("listaCorrispondenze")
    public void setListaCorrispondenze(Object listaCorrispondenze) {
        this.listaCorrispondenze = listaCorrispondenze;
    }

    @JsonProperty("programmata")
    public Long getProgrammata() {
        return programmata;
    }

    @JsonProperty("programmata")
    public void setProgrammata(Long programmata) {
        this.programmata = programmata;
    }

    @JsonProperty("programmataZero")
    public Object getProgrammataZero() {
        return programmataZero;
    }

    @JsonProperty("programmataZero")
    public void setProgrammataZero(Object programmataZero) {
        this.programmataZero = programmataZero;
    }

    @JsonProperty("effettiva")
    public Object getEffettiva() {
        return effettiva;
    }

    @JsonProperty("effettiva")
    public void setEffettiva(Object effettiva) {
        this.effettiva = effettiva;
    }

    @JsonProperty("ritardo")
    public Long getRitardo() {
        return ritardo;
    }

    @JsonProperty("ritardo")
    public void setRitardo(Long ritardo) {
        this.ritardo = ritardo;
    }

    @JsonProperty("partenzaTeoricaZero")
    public Object getPartenzaTeoricaZero() {
        return partenzaTeoricaZero;
    }

    @JsonProperty("partenzaTeoricaZero")
    public void setPartenzaTeoricaZero(Object partenzaTeoricaZero) {
        this.partenzaTeoricaZero = partenzaTeoricaZero;
    }

    @JsonProperty("arrivoTeoricoZero")
    public Object getArrivoTeoricoZero() {
        return arrivoTeoricoZero;
    }

    @JsonProperty("arrivoTeoricoZero")
    public void setArrivoTeoricoZero(Object arrivoTeoricoZero) {
        this.arrivoTeoricoZero = arrivoTeoricoZero;
    }

    @JsonProperty("partenza_teorica")
    public Object getPartenzaTeorica() {
        return partenzaTeorica;
    }

    @JsonProperty("partenza_teorica")
    public void setPartenzaTeorica(Object partenzaTeorica) {
        this.partenzaTeorica = partenzaTeorica;
    }

    @JsonProperty("arrivo_teorico")
    public Long getArrivoTeorico() {
        return arrivoTeorico;
    }

    @JsonProperty("arrivo_teorico")
    public void setArrivoTeorico(Long arrivoTeorico) {
        this.arrivoTeorico = arrivoTeorico;
    }

    @JsonProperty("isNextChanged")
    public Boolean getIsNextChanged() {
        return isNextChanged;
    }

    @JsonProperty("isNextChanged")
    public void setIsNextChanged(Boolean isNextChanged) {
        this.isNextChanged = isNextChanged;
    }

    @JsonProperty("partenzaReale")
    public Object getPartenzaReale() {
        return partenzaReale;
    }

    @JsonProperty("partenzaReale")
    public void setPartenzaReale(Object partenzaReale) {
        this.partenzaReale = partenzaReale;
    }

    @JsonProperty("arrivoReale")
    public Object getArrivoReale() {
        return arrivoReale;
    }

    @JsonProperty("arrivoReale")
    public void setArrivoReale(Object arrivoReale) {
        this.arrivoReale = arrivoReale;
    }

    @JsonProperty("ritardoPartenza")
    public Long getRitardoPartenza() {
        return ritardoPartenza;
    }

    @JsonProperty("ritardoPartenza")
    public void setRitardoPartenza(Long ritardoPartenza) {
        this.ritardoPartenza = ritardoPartenza;
    }

    @JsonProperty("ritardoArrivo")
    public Long getRitardoArrivo() {
        return ritardoArrivo;
    }

    @JsonProperty("ritardoArrivo")
    public void setRitardoArrivo(Long ritardoArrivo) {
        this.ritardoArrivo = ritardoArrivo;
    }

    @JsonProperty("progressivo")
    public Long getProgressivo() {
        return progressivo;
    }

    @JsonProperty("progressivo")
    public void setProgressivo(Long progressivo) {
        this.progressivo = progressivo;
    }

    @JsonProperty("binarioEffettivoArrivoCodice")
    public Object getBinarioEffettivoArrivoCodice() {
        return binarioEffettivoArrivoCodice;
    }

    @JsonProperty("binarioEffettivoArrivoCodice")
    public void setBinarioEffettivoArrivoCodice(Object binarioEffettivoArrivoCodice) {
        this.binarioEffettivoArrivoCodice = binarioEffettivoArrivoCodice;
    }

    @JsonProperty("binarioEffettivoArrivoTipo")
    public Object getBinarioEffettivoArrivoTipo() {
        return binarioEffettivoArrivoTipo;
    }

    @JsonProperty("binarioEffettivoArrivoTipo")
    public void setBinarioEffettivoArrivoTipo(Object binarioEffettivoArrivoTipo) {
        this.binarioEffettivoArrivoTipo = binarioEffettivoArrivoTipo;
    }

    @JsonProperty("binarioEffettivoArrivoDescrizione")
    public Object getBinarioEffettivoArrivoDescrizione() {
        return binarioEffettivoArrivoDescrizione;
    }

    @JsonProperty("binarioEffettivoArrivoDescrizione")
    public void setBinarioEffettivoArrivoDescrizione(Object binarioEffettivoArrivoDescrizione) {
        this.binarioEffettivoArrivoDescrizione = binarioEffettivoArrivoDescrizione;
    }

    @JsonProperty("binarioProgrammatoArrivoCodice")
    public Object getBinarioProgrammatoArrivoCodice() {
        return binarioProgrammatoArrivoCodice;
    }

    @JsonProperty("binarioProgrammatoArrivoCodice")
    public void setBinarioProgrammatoArrivoCodice(Object binarioProgrammatoArrivoCodice) {
        this.binarioProgrammatoArrivoCodice = binarioProgrammatoArrivoCodice;
    }

    @JsonProperty("binarioProgrammatoArrivoDescrizione")
    public String getBinarioProgrammatoArrivoDescrizione() {
        return binarioProgrammatoArrivoDescrizione;
    }

    @JsonProperty("binarioProgrammatoArrivoDescrizione")
    public void setBinarioProgrammatoArrivoDescrizione(String binarioProgrammatoArrivoDescrizione) {
        this.binarioProgrammatoArrivoDescrizione = binarioProgrammatoArrivoDescrizione;
    }

    @JsonProperty("binarioEffettivoPartenzaCodice")
    public Object getBinarioEffettivoPartenzaCodice() {
        return binarioEffettivoPartenzaCodice;
    }

    @JsonProperty("binarioEffettivoPartenzaCodice")
    public void setBinarioEffettivoPartenzaCodice(Object binarioEffettivoPartenzaCodice) {
        this.binarioEffettivoPartenzaCodice = binarioEffettivoPartenzaCodice;
    }

    @JsonProperty("binarioEffettivoPartenzaTipo")
    public Object getBinarioEffettivoPartenzaTipo() {
        return binarioEffettivoPartenzaTipo;
    }

    @JsonProperty("binarioEffettivoPartenzaTipo")
    public void setBinarioEffettivoPartenzaTipo(Object binarioEffettivoPartenzaTipo) {
        this.binarioEffettivoPartenzaTipo = binarioEffettivoPartenzaTipo;
    }

    @JsonProperty("binarioEffettivoPartenzaDescrizione")
    public Object getBinarioEffettivoPartenzaDescrizione() {
        return binarioEffettivoPartenzaDescrizione;
    }

    @JsonProperty("binarioEffettivoPartenzaDescrizione")
    public void setBinarioEffettivoPartenzaDescrizione(Object binarioEffettivoPartenzaDescrizione) {
        this.binarioEffettivoPartenzaDescrizione = binarioEffettivoPartenzaDescrizione;
    }

    @JsonProperty("binarioProgrammatoPartenzaCodice")
    public Object getBinarioProgrammatoPartenzaCodice() {
        return binarioProgrammatoPartenzaCodice;
    }

    @JsonProperty("binarioProgrammatoPartenzaCodice")
    public void setBinarioProgrammatoPartenzaCodice(Object binarioProgrammatoPartenzaCodice) {
        this.binarioProgrammatoPartenzaCodice = binarioProgrammatoPartenzaCodice;
    }

    @JsonProperty("binarioProgrammatoPartenzaDescrizione")
    public Object getBinarioProgrammatoPartenzaDescrizione() {
        return binarioProgrammatoPartenzaDescrizione;
    }

    @JsonProperty("binarioProgrammatoPartenzaDescrizione")
    public void setBinarioProgrammatoPartenzaDescrizione(Object binarioProgrammatoPartenzaDescrizione) {
        this.binarioProgrammatoPartenzaDescrizione = binarioProgrammatoPartenzaDescrizione;
    }

    @JsonProperty("tipoFermata")
    public String getTipoFermata() {
        return tipoFermata;
    }

    @JsonProperty("tipoFermata")
    public void setTipoFermata(String tipoFermata) {
        this.tipoFermata = tipoFermata;
    }

    @JsonProperty("visualizzaPrevista")
    public Boolean getVisualizzaPrevista() {
        return visualizzaPrevista;
    }

    @JsonProperty("visualizzaPrevista")
    public void setVisualizzaPrevista(Boolean visualizzaPrevista) {
        this.visualizzaPrevista = visualizzaPrevista;
    }

    @JsonProperty("nextChanged")
    public Boolean getNextChanged() {
        return nextChanged;
    }

    @JsonProperty("nextChanged")
    public void setNextChanged(Boolean nextChanged) {
        this.nextChanged = nextChanged;
    }

    @JsonProperty("nextTrattaType")
    public Long getNextTrattaType() {
        return nextTrattaType;
    }

    @JsonProperty("nextTrattaType")
    public void setNextTrattaType(Long nextTrattaType) {
        this.nextTrattaType = nextTrattaType;
    }

    @JsonProperty("actualFermataType")
    public Integer getActualFermataType() {
        return actualFermataType;
    }

    @JsonProperty("actualFermataType")
    public void setActualFermataType(Integer actualFermataType) {
        this.actualFermataType = actualFermataType;
    }

    @JsonProperty("materiale_label")
    public Object getMaterialeLabel() {
        return materialeLabel;
    }

    @JsonProperty("materiale_label")
    public void setMaterialeLabel(Object materialeLabel) {
        this.materialeLabel = materialeLabel;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
