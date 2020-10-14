package it.dinfo.stlab.dto;

import java.util.Date;

public class AdminAuthorizationDTO {

    private String id;
    private Date expire_date;
    private UserAccountDTO userAccount;
    private InfomobilityServiceDTOLight isp;
    private MunicipalityDTO municipality;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

    public InfomobilityServiceDTOLight getIsp() {
        return isp;
    }

    public void setIsp(InfomobilityServiceDTOLight isp) {
        this.isp = isp;
    }

    public MunicipalityDTO getMunicipality() {
        return municipality;
    }

    public void setMunicipality(MunicipalityDTO municipality) {
        this.municipality = municipality;
    }

}
