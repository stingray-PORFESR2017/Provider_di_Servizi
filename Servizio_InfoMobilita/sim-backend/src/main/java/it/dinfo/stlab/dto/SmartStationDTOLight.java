package it.dinfo.stlab.dto;

import it.dinfo.stlab.model.SmartStation;

public class SmartStationDTOLight {
    private String id;
    private String name;

    public SmartStationDTOLight(){}

    public SmartStationDTOLight(SmartStation ss){
        this.id = ss.getId();
        this.name = ss.getName();
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
}
