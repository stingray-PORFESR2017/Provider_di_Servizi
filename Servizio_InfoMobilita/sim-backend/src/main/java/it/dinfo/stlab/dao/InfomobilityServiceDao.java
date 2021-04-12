package it.dinfo.stlab.dao;

import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.user.UserAccount;


import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@RequestScoped
public class InfomobilityServiceDao extends GenericDao<InfomobilityServiceProvider> {

    public InfomobilityServiceDao() {
        super(InfomobilityServiceProvider.class);
    }

//    public List<InfomobilityServiceProvider> findAllEnabled() {
//        return this.entityManager.createQuery("SELECT DISTINCT isp FROM InfomobilityServiceProvider isp" +
//                                                      " WHERE isp.enabled = true").getResultList();
//    }

    public List<InfomobilityServiceProvider> findAllAuthorizedIspForAdmin(UserAccount user) {
        return this.entityManager.createQuery(
                "SELECT DISTINCT isp " +
                   "FROM InfomobilityServiceProvider isp " +
                   "INNER JOIN AdminAuthorization aa " +
                   "ON aa.infomobilityServiceProvider = isp " +
                   "WHERE aa.userAccount = :user " +
                   "AND (aa.expireDate >= :now OR aa.expireDate IS NULL) " +
                   "GROUP BY isp.id ",
                InfomobilityServiceProvider.class)
                .setParameter("user",user).setParameter("now",new Date()).getResultList();
    }

    public List<InfomobilityServiceProvider> findAllIspForMunicipalityAuthorizationForAdmin(UserAccount user, Municipality municipality) {
        return this.entityManager.createQuery(
                     "SELECT DISTINCT isp " +
                        "FROM InfomobilityServiceProvider isp, AdminAuthorization aa " +
                        "WHERE aa.municipality = :municipality " +
                        "AND aa.userAccount = :user " +
                        "AND (aa.expireDate >= :now OR aa.expireDate IS NULL)" +
                        "AND aa.infomobilityServiceProvider.id = isp.id")
                .setParameter("municipality",municipality).setParameter("user",user)
                .setParameter("now",new Date()).getResultList();
    }

}
