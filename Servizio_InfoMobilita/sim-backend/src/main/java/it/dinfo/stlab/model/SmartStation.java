package it.dinfo.stlab.model;

import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SmartStation")
public class SmartStation {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="picture")
    private byte[] picture;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    //Relazione NtoN bidirezionale (non si mette il mappedBy in nessuna classe e si mettono le joinColumns e inverseJoinColumns nelle due classi invertite
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable
        (
            name="SmartStation_InfomobilityServiceProvider",
            joinColumns= @JoinColumn(name="smart_station_id"),
            inverseJoinColumns= @JoinColumn(name="infomobility_service_provider_id")
        )
    private List<InfomobilityServiceProvider> infomobilityServiceProviders;

    //per servizio esterno dei treni
    @Column(name = "external_place_id")
    private String externalPlaceId;

    //per servizio esterno dei dati ambientali
    @Column(name = "cmad_mac_address")
    private String cmadMacAddress;

    // Constructor

    public SmartStation(){
        this.infomobilityServiceProviders = new ArrayList<>();
    }

    // Getters & Setters

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public List<InfomobilityServiceProvider> getInfomobilityServiceProviders() {
        return infomobilityServiceProviders;
    }

    public void setInfomobilityServiceProviders(List<InfomobilityServiceProvider> infomobilityServiceProviders) {
        this.infomobilityServiceProviders = infomobilityServiceProviders;
    }

    public String getExternalPlaceId() {
        return externalPlaceId;
    }

    public void setExternalPlaceId(String externalPlaceId) {
        this.externalPlaceId = externalPlaceId;
    }

    public String getCmadMacAddress() {
        return cmadMacAddress;
    }

    public void setCmadMacAddress(String cmadMacAddress) {
        this.cmadMacAddress = cmadMacAddress;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
