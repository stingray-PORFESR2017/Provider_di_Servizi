package it.dinfo.stlab.eai.apiproviders.dto;

import it.dinfo.stlab.model.providers.MobilityType;

import java.util.List;

public class VehicleDto {

	private Location currentLocation;

	private String number;
	private String description;
	private String bookingLink;
	private float hourlyCost;
	private String powerType;

	private String nominalDepartureTime;
	private String delay;
	private List<Location> upcomingStops;

	private Object otherInfos;

	public MobilityType mobilityType;

	public MobilityType getMobilityType() {
		return mobilityType;
	}

	public void setMobilityType(MobilityType mobilityType) {
		this.mobilityType = mobilityType;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getBookingLink() {
		return bookingLink;
	}

	public void setBookingLink(String bookingLink) {
		this.bookingLink = bookingLink;
	}

	public float getHourlyCost() {
		return hourlyCost;
	}

	public void setHourlyCost(float hourlyCost) {
		this.hourlyCost = hourlyCost;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public List<Location> getUpcomingStops() {
		return upcomingStops;
	}

	public void setUpcomingStops(List<Location> upcomingStops) {
		this.upcomingStops = upcomingStops;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNominalDepartureTime() {
		return nominalDepartureTime;
	}

	public void setNominalDepartureTime(String nominalDepartureTime) {
		this.nominalDepartureTime = nominalDepartureTime;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Object getOtherInfos() {
		return otherInfos;
	}

	public void setOtherInfos(Object otherInfos) {
		this.otherInfos = otherInfos;
	}
}
