package it.dinfo.stlab.dao;

import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.user.UserAccount;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
public class SmartStationDao extends GenericDao<SmartStation> {

	public SmartStationDao() {
		super(SmartStation.class);
	}

	public List<SmartStation> findAllEnabled() {
		return this.entityManager.createQuery("SELECT DISTINCT sm FROM SmartStation sm" +
				                                      " WHERE sm.enabled = true").getResultList();
	}

	public List<SmartStation> findAllAuthorizedSSForAdmin(UserAccount user) {
		return this.entityManager.createQuery(
				     "SELECT DISTINCT ss " +
						"from SmartStation ss " +
						"INNER join AdminAuthorization aa on aa.municipality = ss.municipality " +
						"WHERE aa.userAccount = :user " +
						"AND (aa.expireDate >= :now OR aa.expireDate IS NULL) " +
						"GROUP BY ss.id ",
					SmartStation.class)
		       .setParameter("user", user).setParameter("now", new Date()).getResultList();
	}

	public List<SmartStation> findAllEnabledSSForMunicipality(Municipality municipality) {
		return this.entityManager.createQuery("SELECT DISTINCT ss FROM SmartStation ss WHERE ss.municipality = " +
				                                      ":municipality AND ss.enabled = true", SmartStation.class)
		                         .setParameter("municipality", municipality).getResultList();
	}

	public List<SmartStation> findAllSSForMunicipality(Municipality municipality) {
		try {
			return this.entityManager.createQuery("SELECT DISTINCT ss FROM SmartStation ss WHERE ss.municipality = " +
					                                      ":municipality", SmartStation.class)
			                         .setParameter("municipality", municipality).getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
