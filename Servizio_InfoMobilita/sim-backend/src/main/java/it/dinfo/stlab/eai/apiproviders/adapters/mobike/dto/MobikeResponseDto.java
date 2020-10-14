package it.dinfo.stlab.eai.apiproviders.adapters.mobike.dto;

import java.util.List;

public class MobikeResponseDto {

	private Integer code;
	private String message;
	private List<BikeDto> bike;
	private Integer radius;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BikeDto> getBike() {
		return bike;
	}

	public void setBike(List<BikeDto> bike) {
		this.bike = bike;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
