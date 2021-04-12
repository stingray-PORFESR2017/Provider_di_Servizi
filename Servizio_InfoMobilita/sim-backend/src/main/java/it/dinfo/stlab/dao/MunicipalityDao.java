package it.dinfo.stlab.dao;

import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.user.UserAccount;

import javax.enterprise.context.RequestScoped;
import java.util.Date;
import java.util.List;


@RequestScoped
public class MunicipalityDao extends GenericDao<Municipality> {

    public MunicipalityDao(){
        super(Municipality.class);
    }

    public Municipality findByName(String name){
        return (Municipality) this.entityManager.createQuery("SELECT m FROM Municipality m WHERE m.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    public List<Municipality> findNotEmpty() {
        return this.entityManager.createQuery(
                          "SELECT DISTINCT m " +
                             "FROM Municipality m " +
                             "left join SmartStation s on m.id = s.municipality " +
                             "left join s.infomobilityServiceProviders as ssisp " +
                             "WHERE s.enabled = true " +
                             "AND ssisp.enabled = true "
                , Municipality.class).getResultList();
    }

//    public List<Municipality> findNotEmpty() {
//        return this.entityManager.createNativeQuery(
//                "select distinct m.* " +
//                        "from InfomobilityServiceProvider i, SmartStation_InfomobilityServiceProvider si, " +
//                        "SmartStation s, Municipality m " +
//                        "where " +
//                        "i.enabled = true " +
//                        "and i.id = si.infomobility_service_provider_id " +
//                        "and si.smart_station_id = s.id " +
//                        "and s.enabled = true " +
//                        "and s.municipality_id = m.id"
//                , Municipality.class).getResultList();
//    }

    // ritorno tutte le municipality alle quali un admin è autorizzato per una isp. Ad esempio un admin puo puo essere autorizzato all'isp Car2Go sia su Milano e su Firenze
    public List<Municipality> findAllMunicipalityForAdminAuth(UserAccount user, InfomobilityServiceProvider isp){
        return this.entityManager.createQuery("SELECT DISTINCT m FROM Municipality m, AdminAuthorization a " +
                "WHERE a.userAccount = :user AND a.infomobilityServiceProvider = :isp AND a.municipality.id = m.id AND (a.expireDate >= :now OR a.expireDate IS NULL)")
                .setParameter("user",user).setParameter("isp",isp).setParameter("now",new Date()).getResultList();
    }

    public List<Municipality> findAllForAdmin(UserAccount user){
        return this.entityManager.createQuery("SELECT DISTINCT m FROM Municipality m, AdminAuthorization aa WHERE " +
                "m.id = aa.municipality.id AND aa.userAccount = :user AND (aa.expireDate >= :now OR aa.expireDate IS NULL)")
                .setParameter("user",user).setParameter("now",new Date()).getResultList();
    }

}
