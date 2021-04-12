package it.dinfo.stlab.dto;

import java.util.ArrayList;
import java.util.List;

public class InfomobilityServiceDTO {

    private String id;
    private String name;
    private boolean enabled;
    private List<String> mobilityTypes;
    private String serviceProviderType;
    private String serviceProviderTypeContent;
    private List<SmartStationDTOLight> smartStations;

    public InfomobilityServiceDTO() {
        this.mobilityTypes = new ArrayList<>();
        this.smartStations = new ArrayList<>();
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getMobilityTypes() {
        return mobilityTypes;
    }

    public void setMobilityTypes(List<String> mobilityTypes) {
        this.mobilityTypes = mobilityTypes;
    }

    public String getServiceProviderType() {
        return serviceProviderType;
    }

    public void setServiceProviderType(String serviceProviderType) {
        this.serviceProviderType = serviceProviderType;
    }

    public String getServiceProviderTypeContent() {
        return serviceProviderTypeContent;
    }

    public void setServiceProviderTypeContent(String serviceProviderTypeContent) {
        this.serviceProviderTypeContent = serviceProviderTypeContent;
    }

    public List<SmartStationDTOLight> getSmartStations() {
        return smartStations;
    }

    public void setSmartStations(List<SmartStationDTOLight> smartStations) {
        this.smartStations = smartStations;
    }
}
