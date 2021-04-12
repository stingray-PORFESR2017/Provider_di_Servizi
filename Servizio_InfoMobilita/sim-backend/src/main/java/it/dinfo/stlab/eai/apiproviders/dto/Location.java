package it.dinfo.stlab.eai.apiproviders.dto;

public class Location {

	private double lon;
	private double lat;
	private String label;

	public Location(String label, double latitude, double longitude) {
		this.label = label;
		this.lat = latitude;
		this.lon = longitude;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
