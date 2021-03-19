package it.dinfo.stlab.eai.rfi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DepartureDto {
	@JacksonXmlProperty(localName = "Cancelled")
	@JsonProperty("cancelled")
	private Boolean cancelled;

	@JacksonXmlProperty(localName = "Delay")
	@JsonProperty("delay")
	private String delay;

	@JacksonXmlProperty(localName = "Platform")
	@JsonProperty("platform")
	private String platform;

	@JacksonXmlProperty(localName = "Time")
	@JsonProperty("time")
	private String time;

	@JacksonXmlProperty(localName = "TrainNumber")
	@JsonProperty("trainNumber")
	private String trainNumber;

	@JacksonXmlProperty(localName = "TrainHeader")
	@JsonProperty("trainHeader")
	private TrainHeaderDto trainHeaders;

	@JacksonXmlProperty(localName = "Messages")
	@JsonProperty("messages")
	private MessagesDto messages;

	public Boolean getCancelled() {
		return cancelled;
	}
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public TrainHeaderDto getTrainHeaders() {
		return trainHeaders;
	}
	public void setTrainHeaders(TrainHeaderDto trainHeaders) {
		this.trainHeaders = trainHeaders;
	}
	public MessagesDto getMessages() {
		return messages;
	}
	public void setMessages(MessagesDto messages) {
		this.messages = messages;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
}
