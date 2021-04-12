package it.dinfo.stlab.eai.ambientaldata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "DatiCMAD")
public class AmbientalDataDTO {

    //@JacksonXmlElementWrapper(localName = "CMAD_ANALOG_INFO") //Define wrapper to use for collection types. localName is the name of the XML wrapper element
    @JacksonXmlProperty(localName = "CMAD_ANALOG_INFO") //Define XML property, can be attribute or element. localName is the name of the XML element
    private CmadAnalogInfo cmadAnalogInfo;

    public CmadAnalogInfo getCmadAnalogInfo() {
        return cmadAnalogInfo;
    }

    public void setCmadAnalogInfo(CmadAnalogInfo cmadAnalogInfo) {
        this.cmadAnalogInfo = cmadAnalogInfo;
    }

    public static class CmadAnalogInfo{
        @JacksonXmlProperty(localName = "TempEst") //Define XML property, can be attribute or element. localName is the name of the XML element
        @JsonProperty("tempEst")
        private String tempEst;

        @JacksonXmlProperty(localName = "TempSuolo")
        @JsonProperty("tempSuolo")
        private String tempSuolo;

        public String getTempEst() {
            return tempEst;
        }

        public void setTempEst(String tempEst) {
            this.tempEst = tempEst;
        }

        public String getTempSuolo() {
            return tempSuolo;
        }

        public void setTempSuolo(String tempSuolo) {
            this.tempSuolo = tempSuolo;
        }
    }
}

