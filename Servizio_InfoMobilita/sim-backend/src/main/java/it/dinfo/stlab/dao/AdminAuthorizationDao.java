package it.dinfo.stlab.dao;

import it.dinfo.stlab.model.AdminAuthorization;
import it.dinfo.stlab.model.user.UserAccount;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class AdminAuthorizationDao extends GenericDao<AdminAuthorization> {

    public AdminAuthorizationDao(){
        super(AdminAuthorization.class);
    }

    public List<AdminAuthorization> findAllAuthorizationForOneUser(UserAccount u){
        return this.entityManager.createQuery("SELECT DISTINCT aa FROM AdminAuthorization aa WHERE aa.userAccount = :user").setParameter("user",u).getResultList();
    }

}
