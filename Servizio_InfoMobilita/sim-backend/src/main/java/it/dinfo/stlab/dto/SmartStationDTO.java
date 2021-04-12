package it.dinfo.stlab.dto;

import java.util.ArrayList;
import java.util.List;

public class SmartStationDTO {

    private String id;
    private String name;
    private Double lat;
    private Double lon;
    private boolean enabled;
    private MunicipalityDTO municipality;
    private List<InfomobilityServiceDTOLight> infomobilityServices;
    private String ExternalPlaceId;
    private String cmadMacAddress;

    public SmartStationDTO(){
        this.infomobilityServices = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MunicipalityDTO getMunicipality() {
        return municipality;
    }

    public void setMunicipality(MunicipalityDTO municipality) {
        this.municipality = municipality;
    }

    public List<InfomobilityServiceDTOLight> getInfomobilityServices() {
        return infomobilityServices;
    }

    public void setInfomobilityServices(List<InfomobilityServiceDTOLight> infomobilityServices) {
        this.infomobilityServices = infomobilityServices;
    }

    public String getExternalPlaceId() {
        return ExternalPlaceId;
    }

    public void setExternalPlaceId(String externalPlaceId) {
        this.ExternalPlaceId = externalPlaceId;
    }

    public String getCmadMacAddress() {
        return cmadMacAddress;
    }

    public void setCmadMacAddress(String cmadMacAddress) {
        this.cmadMacAddress = cmadMacAddress;
    }
}
