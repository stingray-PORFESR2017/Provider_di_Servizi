package it.dinfo.stlab.dao;

import it.dinfo.stlab.model.user.UserAccount;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserAccountDao extends GenericDao<UserAccount> {

    public UserAccountDao() {
        super(UserAccount.class);
    }

    public UserAccount findByEmail(String email){
        return (UserAccount) this.entityManager.createQuery("SELECT u FROM UserAccount u WHERE u.email = :email")
                .setParameter("email",email).getSingleResult();
    }

    public UserAccount findByEmailAndPassword(String email, String password){
        return (UserAccount) this.entityManager.createQuery("SELECT u FROM UserAccount u WHERE u.email = :email AND u.password = :password")
                .setParameter("email",email).setParameter("password",password).getSingleResult();
    }

}
