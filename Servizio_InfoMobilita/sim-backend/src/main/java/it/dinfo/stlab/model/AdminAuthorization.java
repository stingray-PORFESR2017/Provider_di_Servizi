package it.dinfo.stlab.model;

import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.user.UserAccount;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AdminAuthorization")
public class AdminAuthorization {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @Temporal(TemporalType.DATE)
    @Column(name = "expire_date")
    private Date expireDate;

    @ManyToOne
    @JoinColumn(name = "infomobility_service_provider_id")
    private InfomobilityServiceProvider infomobilityServiceProvider;

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expiryDate) {
        this.expireDate = expiryDate;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public InfomobilityServiceProvider getInfomobilityServiceProvider() {
        return infomobilityServiceProvider;
    }

    public void setInfomobilityServiceProvider(InfomobilityServiceProvider infomobilityServiceProvider) {
        this.infomobilityServiceProvider = infomobilityServiceProvider;
    }

}
