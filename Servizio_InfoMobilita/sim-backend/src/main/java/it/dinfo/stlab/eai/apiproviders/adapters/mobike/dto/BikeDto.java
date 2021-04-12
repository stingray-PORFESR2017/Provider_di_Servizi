package it.dinfo.stlab.eai.apiproviders.adapters.mobike.dto;

import it.dinfo.stlab.eai.apiproviders.adapters.ProviderVehicle;

public class BikeDto implements ProviderVehicle {

	private String distId;
	private Float distX;
	private Float distY;
	private String distance;
	private String bikeIds;
	private Integer biketype;
	private Integer operateType;

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public Float getDistX() {
		return distX;
	}

	public void setDistX(Float distX) {
		this.distX = distX;
	}

	public Float getDistY() {
		return distY;
	}

	public void setDistY(Float distY) {
		this.distY = distY;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getBikeIds() {
		return bikeIds;
	}

	public void setBikeIds(String bikeIds) {
		this.bikeIds = bikeIds;
	}

	public Integer getBiketype() {
		return biketype;
	}

	public void setBiketype(Integer biketype) {
		this.biketype = biketype;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
}
