package it.dinfo.stlab.eai.viaggiatreno;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"numeroTreno", "categoria", "categoriaDescrizione", "origine", "codOrigine", "destinazione",
		"codDestinazione", "origineEstera", "destinazioneEstera", "oraPartenzaEstera", "oraArrivoEstera", "tratta",
		"regione", "origineZero", "destinazioneZero", "orarioPartenzaZero", "orarioArrivoZero", "circolante",
		"codiceCliente", "binarioEffettivoArrivoCodice", "binarioEffettivoArrivoDescrizione",
		"binarioEffettivoArrivoTipo", "binarioProgrammatoArrivoCodice", "binarioProgrammatoArrivoDescrizione",
		"binarioEffettivoPartenzaCodice", "binarioEffettivoPartenzaDescrizione", "binarioEffettivoPartenzaTipo",
		"binarioProgrammatoPartenzaCodice", "binarioProgrammatoPartenzaDescrizione", "subTitle", "esisteCorsaZero",
		"orientamento", "inStazione", "haCambiNumero", "nonPartito", "provvedimento", "riprogrammazione",
		"orarioPartenza", "orarioArrivo", "stazionePartenza", "stazioneArrivo", "statoTreno", "corrispondenze",
		"servizi", "ritardo", "tipoProdotto", "compOrarioPartenzaZeroEffettivo", "compOrarioArrivoZeroEffettivo",
		"compOrarioPartenzaZero", "compOrarioArrivoZero", "compOrarioArrivo", "compOrarioPartenza", "compNumeroTreno"
		, "compOrientamento", "compTipologiaTreno", "compClassRitardoTxt", "compClassRitardoLine", "compImgRitardo2",
		"compImgRitardo", "compRitardo", "compRitardoAndamento", "compInStazionePartenza", "compInStazioneArrivo",
		"compOrarioEffettivoArrivo", "compDurata", "compImgCambiNumerazione", "materiale_label"
})
public class DeparturingTrain {

	@JsonProperty("numeroTreno")
	private int numeroTreno;

	@JsonProperty("categoria")
	private String categoria;

	@JsonProperty("categoriaDescrizione")
	private String categoriaDescrizione;

	@JsonProperty("origine")
	private String origine;

	@JsonProperty("codOrigine")
	private String codOrigine;

	@JsonProperty("destinazione")
	private String destinazione;

	@JsonProperty("codDestinazione")
	private String codDestinazione;

	@JsonProperty("origineEstera")
	private String origineEstera;

	@JsonProperty("destinazioneEstera")
	private String destinazioneEstera;

	@JsonProperty("oraPartenzaEstera")
	private String oraPartenzaEstera;

	@JsonProperty("oraArrivoEstera")
	private String oraArrivoEstera;

	@JsonProperty("tratta")
	private Long tratta;

	@JsonProperty("regione")
	private Long regione;

	@JsonProperty("origineZero")
	private String origineZero;

	@JsonProperty("destinazioneZero")
	private String destinazioneZero;

	@JsonProperty("orarioPartenzaZero")
	private String orarioPartenzaZero;

	@JsonProperty("orarioArrivoZero")
	private String orarioArrivoZero;

	@JsonProperty("circolante")
	private boolean circolante;

	@JsonProperty("codiceCliente")
	private Long codiceCliente;

	@JsonProperty("binarioEffettivoArrivoCodice")
	private String binarioEffettivoArrivoCodice;

	@JsonProperty("binarioEffettivoArrivoDescrizione")
	private String binarioEffettivoArrivoDescrizione;

	@JsonProperty("binarioEffettivoArrivoTipo")
	private String binarioEffettivoArrivoTipo;

	@JsonProperty("binarioProgrammatoArrivoCodice")
	private String binarioProgrammatoArrivoCodice;

	@JsonProperty("binarioProgrammatoArrivoDescrizione")
	private String binarioProgrammatoArrivoDescrizione;

	@JsonProperty("binarioEffettivoPartenzaCodice")
	private String binarioEffettivoPartenzaCodice;

	@JsonProperty("binarioEffettivoPartenzaDescrizione")
	private String binarioEffettivoPartenzaDescrizione;

	@JsonProperty("binarioEffettivoPartenzaTipo")
	private String binarioEffettivoPartenzaTipo;

	@JsonProperty("binarioProgrammatoPartenzaCodice")
	private String binarioProgrammatoPartenzaCodice;

	@JsonProperty("binarioProgrammatoPartenzaDescrizione")
	private String binarioProgrammatoPartenzaDescrizione;

	@JsonProperty("subTitle")
	private String subTitle;

	@JsonProperty("esisteCorsaZero")
	private String esisteCorsaZero;

	@JsonProperty("orientamento")
	private String orientamento;

	@JsonProperty("inStazione")
	private boolean inStazione;

	@JsonProperty("haCambiNumero")
	private boolean haCambiNumero;

	@JsonProperty("nonPartito")
	private boolean nonPartito;

	@JsonProperty("provvedimento")
	private Integer provvedimento;

	@JsonProperty("riprogrammazione")
	private String riprogrammazione;

	@JsonProperty("orarioPartenza")
	private Long orarioPartenza;

	@JsonProperty("orarioArrivo")
	private Long orarioArrivo;

	@JsonProperty("stazionePartenza")
	private Object stazionePartenza;

	@JsonProperty("stazioneArrivo")
	private Object stazioneArrivo;

	@JsonProperty("statoTreno")
	private Object statoTreno;

	@JsonProperty("corrispondenze")
	private Object corrispondenze;

	@JsonProperty("servizi")
	private Object servizi;

	@JsonProperty("ritardo")
	private Long ritardo;

	@JsonProperty("tipoProdotto")
	private String tipoProdotto;

	@JsonProperty("compOrarioPartenzaZeroEffettivo")
	private String compOrarioPartenzaZeroEffettivo;

	@JsonProperty("compOrarioArrivoZeroEffettivo")
	private String compOrarioArrivoZeroEffettivo;

	@JsonProperty("compOrarioPartenzaZero")
	private String compOrarioPartenzaZero;

	@JsonProperty("compOrarioArrivoZero")
	private String compOrarioArrivoZero;

	@JsonProperty("compOrarioArrivo")
	private String compOrarioArrivo;

	@JsonProperty("compOrarioPartenza")
	private String compOrarioPartenza;

	@JsonProperty("compNumeroTreno")
	private String compNumeroTreno;

	@JsonProperty("compOrientamento")
	private List<String> compOrientamento = null;

	@JsonProperty("compTipologiaTreno")
	private String compTipologiaTreno;

	@JsonProperty("compClassRitardoTxt")
	private String compClassRitardoTxt;

	@JsonProperty("compClassRitardoLine")
	private String compClassRitardoLine;

	@JsonProperty("compImgRitardo2")
	private String compImgRitardo2;

	@JsonProperty("compImgRitardo")
	private String compImgRitardo;

	@JsonProperty("compRitardo")
	private List<String> compRitardo = null;

	@JsonProperty("compRitardoAndamento")
	private List<String> compRitardoAndamento = null;

	@JsonProperty("compInStazionePartenza")
	private List<String> compInStazionePartenza = null;

	@JsonProperty("compInStazioneArrivo")
	private List<String> compInStazioneArrivo = null;

	@JsonProperty("compOrarioEffettivoArrivo")
	private String compOrarioEffettivoArrivo;

	@JsonProperty("compDurata")
	private String compDurata;

	@JsonProperty("compImgCambiNumerazione")
	private String compImgCambiNumerazione;

	@JsonProperty("materiale_label")
	private Object materialeLabel;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("numeroTreno")
	public int getNumeroTreno() {
		return numeroTreno;
	}

	@JsonProperty("numeroTreno")
	public void setNumeroTreno(int numeroTreno) {
		this.numeroTreno = numeroTreno;
	}

	@JsonProperty("categoria")
	public String getCategoria() {
		return categoria;
	}

	@JsonProperty("categoria")
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@JsonProperty("categoriaDescrizione")
	public String getCategoriaDescrizione() {
		return categoriaDescrizione;
	}

	@JsonProperty("categoriaDescrizione")
	public void setCategoriaDescrizione(String categoriaDescrizione) {
		this.categoriaDescrizione = categoriaDescrizione;
	}

	@JsonProperty("origine")
	public String getOrigine() {
		return origine;
	}

	@JsonProperty("origine")
	public void setOrigine(String origine) {
		this.origine = origine;
	}

	@JsonProperty("codOrigine")
	public String getCodOrigine() {
		return codOrigine;
	}

	@JsonProperty("codOrigine")
	public void setCodOrigine(String codOrigine) {
		this.codOrigine = codOrigine;
	}

	@JsonProperty("destinazione")
	public String getDestinazione() {
		return destinazione;
	}

	@JsonProperty("destinazione")
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	@JsonProperty("codDestinazione")
	public String getCodDestinazione() {
		return codDestinazione;
	}

	@JsonProperty("codDestinazione")
	public void setCodDestinazione(String codDestinazione) {
		this.codDestinazione = codDestinazione;
	}

	@JsonProperty("origineEstera")
	public String getOrigineEstera() {
		return origineEstera;
	}

	@JsonProperty("origineEstera")
	public void setOrigineEstera(String origineEstera) {
		this.origineEstera = origineEstera;
	}

	@JsonProperty("destinazioneEstera")
	public String getDestinazioneEstera() {
		return destinazioneEstera;
	}

	@JsonProperty("destinazioneEstera")
	public void setDestinazioneEstera(String destinazioneEstera) {
		this.destinazioneEstera = destinazioneEstera;
	}

	@JsonProperty("oraPartenzaEstera")
	public String getOraPartenzaEstera() {
		return oraPartenzaEstera;
	}

	@JsonProperty("oraPartenzaEstera")
	public void setOraPartenzaEstera(String oraPartenzaEstera) {
		this.oraPartenzaEstera = oraPartenzaEstera;
	}

	@JsonProperty("oraArrivoEstera")
	public String getOraArrivoEstera() {
		return oraArrivoEstera;
	}

	@JsonProperty("oraArrivoEstera")
	public void setOraArrivoEstera(String oraArrivoEstera) {
		this.oraArrivoEstera = oraArrivoEstera;
	}

	@JsonProperty("tratta")
	public Long getTratta() {
		return tratta;
	}

	@JsonProperty("tratta")
	public void setTratta(Long tratta) {
		this.tratta = tratta;
	}

	@JsonProperty("regione")
	public Long getRegione() {
		return regione;
	}

	@JsonProperty("regione")
	public void setRegione(Long regione) {
		this.regione = regione;
	}

	@JsonProperty("origineZero")
	public String getOrigineZero() {
		return origineZero;
	}

	@JsonProperty("origineZero")
	public void setOrigineZero(String origineZero) {
		this.origineZero = origineZero;
	}

	@JsonProperty("destinazioneZero")
	public String getDestinazioneZero() {
		return destinazioneZero;
	}

	@JsonProperty("destinazioneZero")
	public void setDestinazioneZero(String destinazioneZero) {
		this.destinazioneZero = destinazioneZero;
	}

	@JsonProperty("orarioPartenzaZero")
	public String getOrarioPartenzaZero() {
		return orarioPartenzaZero;
	}

	@JsonProperty("orarioPartenzaZero")
	public void setOrarioPartenzaZero(String orarioPartenzaZero) {
		this.orarioPartenzaZero = orarioPartenzaZero;
	}

	@JsonProperty("orarioArrivoZero")
	public String getOrarioArrivoZero() {
		return orarioArrivoZero;
	}

	@JsonProperty("orarioArrivoZero")
	public void setOrarioArrivoZero(String orarioArrivoZero) {
		this.orarioArrivoZero = orarioArrivoZero;
	}

	@JsonProperty("circolante")
	public boolean isCircolante() {
		return circolante;
	}

	@JsonProperty("circolante")
	public void setCircolante(boolean circolante) {
		this.circolante = circolante;
	}

	@JsonProperty("codiceCliente")
	public Long getCodiceCliente() {
		return codiceCliente;
	}

	@JsonProperty("codiceCliente")
	public void setCodiceCliente(Long codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	@JsonProperty("binarioEffettivoArrivoCodice")
	public String getBinarioEffettivoArrivoCodice() {
		return binarioEffettivoArrivoCodice;
	}

	@JsonProperty("binarioEffettivoArrivoCodice")
	public void setBinarioEffettivoArrivoCodice(String binarioEffettivoArrivoCodice) {
		this.binarioEffettivoArrivoCodice = binarioEffettivoArrivoCodice;
	}

	@JsonProperty("binarioEffettivoArrivoDescrizione")
	public String getBinarioEffettivoArrivoDescrizione() {
		return binarioEffettivoArrivoDescrizione;
	}

	@JsonProperty("binarioEffettivoArrivoDescrizione")
	public void setBinarioEffettivoArrivoDescrizione(String binarioEffettivoArrivoDescrizione) {
		this.binarioEffettivoArrivoDescrizione = binarioEffettivoArrivoDescrizione;
	}

	@JsonProperty("binarioEffettivoArrivoTipo")
	public String getBinarioEffettivoArrivoTipo() {
		return binarioEffettivoArrivoTipo;
	}

	@JsonProperty("binarioEffettivoArrivoTipo")
	public void setBinarioEffettivoArrivoTipo(String binarioEffettivoArrivoTipo) {
		this.binarioEffettivoArrivoTipo = binarioEffettivoArrivoTipo;
	}

	@JsonProperty("binarioProgrammatoArrivoCodice")
	public String getBinarioProgrammatoArrivoCodice() {
		return binarioProgrammatoArrivoCodice;
	}

	@JsonProperty("binarioProgrammatoArrivoCodice")
	public void setBinarioProgrammatoArrivoCodice(String binarioProgrammatoArrivoCodice) {
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
	public String getBinarioEffettivoPartenzaCodice() {
		return binarioEffettivoPartenzaCodice;
	}

	@JsonProperty("binarioEffettivoPartenzaCodice")
	public void setBinarioEffettivoPartenzaCodice(String binarioEffettivoPartenzaCodice) {
		this.binarioEffettivoPartenzaCodice = binarioEffettivoPartenzaCodice;
	}

	@JsonProperty("binarioEffettivoPartenzaDescrizione")
	public String getBinarioEffettivoPartenzaDescrizione() {
		return binarioEffettivoPartenzaDescrizione;
	}

	@JsonProperty("binarioEffettivoPartenzaDescrizione")
	public void setBinarioEffettivoPartenzaDescrizione(String binarioEffettivoPartenzaDescrizione) {
		this.binarioEffettivoPartenzaDescrizione = binarioEffettivoPartenzaDescrizione;
	}

	@JsonProperty("binarioEffettivoPartenzaTipo")
	public String getBinarioEffettivoPartenzaTipo() {
		return binarioEffettivoPartenzaTipo;
	}

	@JsonProperty("binarioEffettivoPartenzaTipo")
	public void setBinarioEffettivoPartenzaTipo(String binarioEffettivoPartenzaTipo) {
		this.binarioEffettivoPartenzaTipo = binarioEffettivoPartenzaTipo;
	}

	@JsonProperty("binarioProgrammatoPartenzaCodice")
	public String getBinarioProgrammatoPartenzaCodice() {
		return binarioProgrammatoPartenzaCodice;
	}

	@JsonProperty("binarioProgrammatoPartenzaCodice")
	public void setBinarioProgrammatoPartenzaCodice(String binarioProgrammatoPartenzaCodice) {
		this.binarioProgrammatoPartenzaCodice = binarioProgrammatoPartenzaCodice;
	}

	@JsonProperty("binarioProgrammatoPartenzaDescrizione")
	public String getBinarioProgrammatoPartenzaDescrizione() {
		return binarioProgrammatoPartenzaDescrizione;
	}

	@JsonProperty("binarioProgrammatoPartenzaDescrizione")
	public void setBinarioProgrammatoPartenzaDescrizione(String binarioProgrammatoPartenzaDescrizione) {
		this.binarioProgrammatoPartenzaDescrizione = binarioProgrammatoPartenzaDescrizione;
	}

	@JsonProperty("subTitle")
	public String getSubTitle() {
		return subTitle;
	}

	@JsonProperty("subTitle")
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@JsonProperty("esisteCorsaZero")
	public String getEsisteCorsaZero() {
		return esisteCorsaZero;
	}

	@JsonProperty("esisteCorsaZero")
	public void setEsisteCorsaZero(String esisteCorsaZero) {
		this.esisteCorsaZero = esisteCorsaZero;
	}

	@JsonProperty("orientamento")
	public String getOrientamento() {
		return orientamento;
	}

	@JsonProperty("orientamento")
	public void setOrientamento(String orientamento) {
		this.orientamento = orientamento;
	}

	@JsonProperty("inStazione")
	public boolean isInStazione() {
		return inStazione;
	}

	@JsonProperty("inStazione")
	public void setInStazione(boolean inStazione) {
		this.inStazione = inStazione;
	}

	@JsonProperty("haCambiNumero")
	public boolean isHaCambiNumero() {
		return haCambiNumero;
	}

	@JsonProperty("haCambiNumero")
	public void setHaCambiNumero(boolean haCambiNumero) {
		this.haCambiNumero = haCambiNumero;
	}

	@JsonProperty("nonPartito")
	public boolean isNonPartito() {
		return nonPartito;
	}

	@JsonProperty("nonPartito")
	public void setNonPartito(boolean nonPartito) {
		this.nonPartito = nonPartito;
	}

	@JsonProperty("provvedimento")
	public Integer getProvvedimento() {
		return provvedimento;
	}

	@JsonProperty("provvedimento")
	public void setProvvedimento(Integer provvedimento) {
		this.provvedimento = provvedimento;
	}

	@JsonProperty("riprogrammazione")
	public String getRiprogrammazione() {
		return riprogrammazione;
	}

	@JsonProperty("riprogrammazione")
	public void setRiprogrammazione(String riprogrammazione) {
		this.riprogrammazione = riprogrammazione;
	}

	@JsonProperty("orarioPartenza")
	public Long getOrarioPartenza() {
		return orarioPartenza;
	}

	@JsonProperty("orarioPartenza")
	public void setOrarioPartenza(Long orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
	}

	@JsonProperty("orarioArrivo")
	public Long getOrarioArrivo() {
		return orarioArrivo;
	}

	@JsonProperty("orarioArrivo")
	public void setOrarioArrivo(Long orarioArrivo) {
		this.orarioArrivo = orarioArrivo;
	}

	@JsonProperty("stazionePartenza")
	public Object getStazionePartenza() {
		return stazionePartenza;
	}

	@JsonProperty("stazionePartenza")
	public void setStazionePartenza(Object stazionePartenza) {
		this.stazionePartenza = stazionePartenza;
	}

	@JsonProperty("stazioneArrivo")
	public Object getStazioneArrivo() {
		return stazioneArrivo;
	}

	@JsonProperty("stazioneArrivo")
	public void setStazioneArrivo(Object stazioneArrivo) {
		this.stazioneArrivo = stazioneArrivo;
	}

	@JsonProperty("statoTreno")
	public Object getStatoTreno() {
		return statoTreno;
	}

	@JsonProperty("statoTreno")
	public void setStatoTreno(Object statoTreno) {
		this.statoTreno = statoTreno;
	}

	@JsonProperty("corrispondenze")
	public Object getCorrispondenze() {
		return corrispondenze;
	}

	@JsonProperty("corrispondenze")
	public void setCorrispondenze(Object corrispondenze) {
		this.corrispondenze = corrispondenze;
	}

	@JsonProperty("servizi")
	public Object getServizi() {
		return servizi;
	}

	@JsonProperty("servizi")
	public void setServizi(Object servizi) {
		this.servizi = servizi;
	}

	@JsonProperty("ritardo")
	public Long getRitardo() {
		return ritardo;
	}

	@JsonProperty("ritardo")
	public void setRitardo(Long ritardo) {
		this.ritardo = ritardo;
	}

	@JsonProperty("tipoProdotto")
	public String getTipoProdotto() {
		return tipoProdotto;
	}

	@JsonProperty("tipoProdotto")
	public void setTipoProdotto(String tipoProdotto) {
		this.tipoProdotto = tipoProdotto;
	}

	@JsonProperty("compOrarioPartenzaZeroEffettivo")
	public String getCompOrarioPartenzaZeroEffettivo() {
		return compOrarioPartenzaZeroEffettivo;
	}

	@JsonProperty("compOrarioPartenzaZeroEffettivo")
	public void setCompOrarioPartenzaZeroEffettivo(String compOrarioPartenzaZeroEffettivo) {
		this.compOrarioPartenzaZeroEffettivo = compOrarioPartenzaZeroEffettivo;
	}

	@JsonProperty("compOrarioArrivoZeroEffettivo")
	public String getCompOrarioArrivoZeroEffettivo() {
		return compOrarioArrivoZeroEffettivo;
	}

	@JsonProperty("compOrarioArrivoZeroEffettivo")
	public void setCompOrarioArrivoZeroEffettivo(String compOrarioArrivoZeroEffettivo) {
		this.compOrarioArrivoZeroEffettivo = compOrarioArrivoZeroEffettivo;
	}

	@JsonProperty("compOrarioPartenzaZero")
	public String getCompOrarioPartenzaZero() {
		return compOrarioPartenzaZero;
	}

	@JsonProperty("compOrarioPartenzaZero")
	public void setCompOrarioPartenzaZero(String compOrarioPartenzaZero) {
		this.compOrarioPartenzaZero = compOrarioPartenzaZero;
	}

	@JsonProperty("compOrarioArrivoZero")
	public String getCompOrarioArrivoZero() {
		return compOrarioArrivoZero;
	}

	@JsonProperty("compOrarioArrivoZero")
	public void setCompOrarioArrivoZero(String compOrarioArrivoZero) {
		this.compOrarioArrivoZero = compOrarioArrivoZero;
	}

	@JsonProperty("compOrarioArrivo")
	public String getCompOrarioArrivo() {
		return compOrarioArrivo;
	}

	@JsonProperty("compOrarioArrivo")
	public void setCompOrarioArrivo(String compOrarioArrivo) {
		this.compOrarioArrivo = compOrarioArrivo;
	}

	@JsonProperty("compOrarioPartenza")
	public String getCompOrarioPartenza() {
		return compOrarioPartenza;
	}

	@JsonProperty("compOrarioPartenza")
	public void setCompOrarioPartenza(String compOrarioPartenza) {
		this.compOrarioPartenza = compOrarioPartenza;
	}

	@JsonProperty("compNumeroTreno")
	public String getCompNumeroTreno() {
		return compNumeroTreno;
	}

	@JsonProperty("compNumeroTreno")
	public void setCompNumeroTreno(String compNumeroTreno) {
		this.compNumeroTreno = compNumeroTreno;
	}

	@JsonProperty("compOrientamento")
	public List<String> getCompOrientamento() {
		return compOrientamento;
	}

	@JsonProperty("compOrientamento")
	public void setCompOrientamento(List<String> compOrientamento) {
		this.compOrientamento = compOrientamento;
	}

	@JsonProperty("compTipologiaTreno")
	public String getCompTipologiaTreno() {
		return compTipologiaTreno;
	}

	@JsonProperty("compTipologiaTreno")
	public void setCompTipologiaTreno(String compTipologiaTreno) {
		this.compTipologiaTreno = compTipologiaTreno;
	}

	@JsonProperty("compClassRitardoTxt")
	public String getCompClassRitardoTxt() {
		return compClassRitardoTxt;
	}

	@JsonProperty("compClassRitardoTxt")
	public void setCompClassRitardoTxt(String compClassRitardoTxt) {
		this.compClassRitardoTxt = compClassRitardoTxt;
	}

	@JsonProperty("compClassRitardoLine")
	public String getCompClassRitardoLine() {
		return compClassRitardoLine;
	}

	@JsonProperty("compClassRitardoLine")
	public void setCompClassRitardoLine(String compClassRitardoLine) {
		this.compClassRitardoLine = compClassRitardoLine;
	}

	@JsonProperty("compImgRitardo2")
	public String getCompImgRitardo2() {
		return compImgRitardo2;
	}

	@JsonProperty("compImgRitardo2")
	public void setCompImgRitardo2(String compImgRitardo2) {
		this.compImgRitardo2 = compImgRitardo2;
	}

	@JsonProperty("compImgRitardo")
	public String getCompImgRitardo() {
		return compImgRitardo;
	}

	@JsonProperty("compImgRitardo")
	public void setCompImgRitardo(String compImgRitardo) {
		this.compImgRitardo = compImgRitardo;
	}

	@JsonProperty("compRitardo")
	public List<String> getCompRitardo() {
		return compRitardo;
	}

	@JsonProperty("compRitardo")
	public void setCompRitardo(List<String> compRitardo) {
		this.compRitardo = compRitardo;
	}

	@JsonProperty("compRitardoAndamento")
	public List<String> getCompRitardoAndamento() {
		return compRitardoAndamento;
	}

	@JsonProperty("compRitardoAndamento")
	public void setCompRitardoAndamento(List<String> compRitardoAndamento) {
		this.compRitardoAndamento = compRitardoAndamento;
	}

	@JsonProperty("compInStazionePartenza")
	public List<String> getCompInStazionePartenza() {
		return compInStazionePartenza;
	}

	@JsonProperty("compInStazionePartenza")
	public void setCompInStazionePartenza(List<String> compInStazionePartenza) {
		this.compInStazionePartenza = compInStazionePartenza;
	}

	@JsonProperty("compInStazioneArrivo")
	public List<String> getCompInStazioneArrivo() {
		return compInStazioneArrivo;
	}

	@JsonProperty("compInStazioneArrivo")
	public void setCompInStazioneArrivo(List<String> compInStazioneArrivo) {
		this.compInStazioneArrivo = compInStazioneArrivo;
	}

	@JsonProperty("compOrarioEffettivoArrivo")
	public String getCompOrarioEffettivoArrivo() {
		return compOrarioEffettivoArrivo;
	}

	@JsonProperty("compOrarioEffettivoArrivo")
	public void setCompOrarioEffettivoArrivo(String compOrarioEffettivoArrivo) {
		this.compOrarioEffettivoArrivo = compOrarioEffettivoArrivo;
	}

	@JsonProperty("compDurata")
	public String getCompDurata() {
		return compDurata;
	}

	@JsonProperty("compDurata")
	public void setCompDurata(String compDurata) {
		this.compDurata = compDurata;
	}

	@JsonProperty("compImgCambiNumerazione")
	public String getCompImgCambiNumerazione() {
		return compImgCambiNumerazione;
	}

	@JsonProperty("compImgCambiNumerazione")
	public void setCompImgCambiNumerazione(String compImgCambiNumerazione) {
		this.compImgCambiNumerazione = compImgCambiNumerazione;
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
