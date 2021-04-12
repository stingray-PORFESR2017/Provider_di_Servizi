package it.dinfo.stlab.dto;

import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;

public class InfomobilityServiceDTOLight {

    private String id;
    private String name;
    private String serviceProviderType;

    public InfomobilityServiceDTOLight(){}

    public InfomobilityServiceDTOLight(InfomobilityServiceProvider isp){
        this.id = isp.getId();
        this.name = isp.getName();
        this.serviceProviderType = isp.getServiceProviderInfo().getServiceProviderType().toString();
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

    public String getServiceProviderType() {
        return serviceProviderType;
    }

    public void setServiceProviderType(String serviceProviderType) {
        this.serviceProviderType = serviceProviderType;
    }
}
