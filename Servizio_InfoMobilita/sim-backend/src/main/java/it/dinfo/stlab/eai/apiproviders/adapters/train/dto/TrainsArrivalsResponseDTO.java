package it.dinfo.stlab.eai.apiproviders.adapters.train.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //si ignorano gli altri campi
@JacksonXmlRootElement(localName = "ArrivalsResponse") //Define root element name in XML. localName is the name of the XML root element
public class TrainsArrivalsResponseDTO {

    @JacksonXmlElementWrapper(localName = "Arrivals") //Define wrapper to use for collection types. localName is the name of the XML wrapper element
    @JacksonXmlProperty(localName = "Arrival") //Define XML property, can be attribute or element. localName is the name of the XML element
    @JsonProperty("Arrivals") //Nome di uscita in json. Altrimenti il nome sarebbe quello della variabile (arrivals)
    private List<Arrival> arrivals;

    public List<Arrival> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Arrival> arrivals) {
        this.arrivals = arrivals;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Arrival {

        @JacksonXmlProperty(localName = "Cancelled")
        @JsonProperty("Cancelled")
        private Boolean cancelled;

        @JacksonXmlProperty(localName = "Delay")
        @JsonProperty("Delay")
        private String delay;

        @JacksonXmlProperty(localName = "Platform")
        @JsonProperty("Platform")
        private String platform;

        @JacksonXmlProperty(localName = "Time")
        @JsonProperty("Time")
        private String time;

        @JacksonXmlProperty(localName = "TrainHeader")
        @JsonProperty("TrainHeader")
        private TrainHeader trainHeaders;

        public Boolean getCancelled() {
            return cancelled;
        }

        public void setCancelled(Boolean cancelled) {
            this.cancelled = cancelled;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public TrainHeader getTrainHeaders() {
            return trainHeaders;
        }

        public void setTrainHeaders(TrainHeader trainHeaders) {
            this.trainHeaders = trainHeaders;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class TrainHeader {

        @JacksonXmlProperty(localName = "Id")
        @JsonProperty("Id")
        private String id;

        @JacksonXmlProperty(localName = "BrandCustomer")
        @JsonProperty("BrandCustomer")
        private String brandCustomer;

        @JacksonXmlProperty(localName = "BrandCategory")
        @JsonProperty("BrandCategory")
        private String brandCategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrandCustomer() {
            return brandCustomer;
        }

        public void setBrandCustomer(String brandCustomer) {
            this.brandCustomer = brandCustomer;
        }

        public String getBrandCategory() {
            return brandCategory;
        }

        public void setBrandCategory(String brandCategory) {
            this.brandCategory = brandCategory;
        }
    }
}
