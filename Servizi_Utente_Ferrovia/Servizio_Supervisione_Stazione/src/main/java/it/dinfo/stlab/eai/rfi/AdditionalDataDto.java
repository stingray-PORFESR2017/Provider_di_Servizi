package it.dinfo.stlab.eai.rfi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AdditionalDataDto {
	@JacksonXmlProperty(localName = "Content")
	@JsonProperty("content")
	private String content;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
