package it.dinfo.stlab.rest.auth.filter;

import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.model.user.UserAccount;
import it.dinfo.stlab.model.user.UserRole;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements javax.ws.rs.core.SecurityContext {

    private UserAccountDao userAccountDao;
    private String principalEmail;
    private ContainerRequestContext requestContext;

    public MySecurityContext(){}

    public MySecurityContext(String principalEmail, ContainerRequestContext requestContext, UserAccountDao userAccountDao){
        this.principalEmail = principalEmail;
        this.requestContext = requestContext;
        this.userAccountDao = userAccountDao;
    }

    @Override
    public boolean isUserInRole(String role) {
        UserAccount principal = userAccountDao.findByEmail(principalEmail);
        if(principal == null)
            return false;

        if(!principal.hasRole(UserRole.valueOf(role)))
            return false;

        return true;
    }

    @Override
    public boolean isSecure() {
        return requestContext.getSecurityContext().isSecure();
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return principalEmail;
            }
        };
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
