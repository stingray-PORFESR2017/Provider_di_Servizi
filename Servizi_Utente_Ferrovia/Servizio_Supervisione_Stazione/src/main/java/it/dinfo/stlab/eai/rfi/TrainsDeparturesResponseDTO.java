package it.dinfo.stlab.eai.rfi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "DeparturesResponse") //Define root element name in XML. localName is the name of the XML root element
public class TrainsDeparturesResponseDTO {
    @JacksonXmlElementWrapper(localName = "Departures") //Define wrapper to use for collection types. localName is the name of the XML wrapper element
    @JacksonXmlProperty(localName = "Departure") //Define XML property, can be attribute or element. localName is the name of the XML element
    @JsonProperty("departures") //Nome di uscita in json. Altrimenti il nome sarebbe quello della variabile (departures)
    private List<DepartureDto> departures;

    public List<DepartureDto> getDepartures() {
        return departures;
    }
    public void setDepartures(List<DepartureDto> departures) {
        this.departures = departures;
    }
}
