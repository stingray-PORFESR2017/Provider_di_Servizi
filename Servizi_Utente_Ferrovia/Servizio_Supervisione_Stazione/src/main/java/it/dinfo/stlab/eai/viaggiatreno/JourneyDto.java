package it.dinfo.stlab.eai.viaggiatreno;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneyDto {

        @JsonProperty("fermate")
        private List<JourneyStopDto> fermate;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String,Object>();

        @JsonProperty("fermate")
        public List<JourneyStopDto> getFermate() {
            return fermate;
        }

        @JsonProperty("fermate")
        public void setFermate(List<JourneyStopDto> fermate) {
            this.fermate = fermate;
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
