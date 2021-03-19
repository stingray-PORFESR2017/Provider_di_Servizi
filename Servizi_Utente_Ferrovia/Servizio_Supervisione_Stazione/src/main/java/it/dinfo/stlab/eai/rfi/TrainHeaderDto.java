package it.dinfo.stlab.eai.rfi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TrainHeaderDto {
	@JacksonXmlProperty(localName = "Id")
	@JsonProperty("id")
	private String id;

	@JacksonXmlProperty(localName = "BrandCustomer")
	@JsonProperty("brandCustomer")
	private String brandCustomer;

	@JacksonXmlProperty(localName = "BrandCategory")
	@JsonProperty("brandCategory")
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
