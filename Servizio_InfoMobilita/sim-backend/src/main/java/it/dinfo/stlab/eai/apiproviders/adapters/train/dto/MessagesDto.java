package it.dinfo.stlab.eai.apiproviders.adapters.train.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessagesDto {
	@JacksonXmlProperty(localName = "AdditionalData")
	@JsonProperty("additionalData")
	private AdditionalDataDto additionalData;

	public AdditionalDataDto getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(AdditionalDataDto additionalData) {
		this.additionalData = additionalData;
	}
}
