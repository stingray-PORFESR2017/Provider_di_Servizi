package it.dinfo.stlab.model.providers;

import it.dinfo.stlab.model.AdminAuthorization;
import it.dinfo.stlab.model.SmartStation;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "InfomobilityServiceProvider")
public class InfomobilityServiceProvider {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="picture")
    private byte[] picture;

    @Column(name = "enabled")
    private boolean enabled;

    @ElementCollection(fetch=FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="InfomobilityServiceProvider_MobilityType")
    private List<MobilityType> mobilityTypes;

    @Embedded
    private ServiceProviderInfo serviceProviderInfo;

    @ManyToMany
    @JoinTable
            (
                    name="SmartStation_InfomobilityServiceProvider",
                    joinColumns= @JoinColumn(name="infomobility_service_provider_id"),
                    inverseJoinColumns= @JoinColumn(name="smart_station_id")
            )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SmartStation> smartStations;

    //utilizzo la bidirezionalità esclusimante per l'utilizzo del cascade, ie, per fare in modo che quando elimino una InfomobilityServiceProvider, si eliminino anche le righe corrispondenti della tabella AdminAuthorization
    @OneToMany(mappedBy = "infomobilityServiceProvider", cascade = {CascadeType.REMOVE})
    private List<AdminAuthorization> adminAuthorizations;

    @Column(name = "controllerClassName")
    private String controllerClassName;

    public InfomobilityServiceProvider() {
        this.mobilityTypes = new ArrayList<>();
        this.smartStations = new ArrayList<>();
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

    public ServiceProviderInfo getServiceProviderInfo() {
        return serviceProviderInfo;
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

    public List<MobilityType> getMobilityTypes() {
        return mobilityTypes;
    }

    public void setMobilityTypes(List<MobilityType> mobilityTypes) {
        this.mobilityTypes = mobilityTypes;
    }

    public void setServiceProviderInfo(ServiceProviderInfo serviceProviderInfo) {
        this.serviceProviderInfo = serviceProviderInfo;
    }

    public List<SmartStation> getSmartStations() {
        return smartStations;
    }

    public void setSmartStations(List<SmartStation> smartStations) {
        this.smartStations = smartStations;
    }

    public String getControllerClassName() {
        return controllerClassName;
    }

    public void setControllerClassName(String controllerClassName) {
        this.controllerClassName = controllerClassName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}

