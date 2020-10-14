package it.dinfo.stlab.rest.auth;

import it.dinfo.stlab.dto.CredentialsDTO;
import it.dinfo.stlab.dto.TokenDTO;
import it.dinfo.stlab.model.user.UserAccount;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/auth")
public class AuthenticationEndpoint {

    @Inject
    AuthenticationController authenticationController;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(CredentialsDTO credentialsDTO) {

        String email = credentialsDTO.getEmail();
        String password = credentialsDTO.getPassword();

        try {
            // Authenticate the user using the credentials provided
            UserAccount userAccount = authenticationController.authenticate(email, password);

            // Issue a token for the user
            String token = "Bearer " + authenticationController.issueToken(email, userAccount.getUserRole().toString(), userAccount.getName());

            // Return the token on the response
            //return Response.ok().header(AUTHORIZATION,"Bearer " + token).entity(userAccountMapper.convert(userAccountDao.findByEmail(email))).build();
            //return Response.ok().entity(new TokenAndUser("Bearer "+token,userAccountMapper.convert(userAccountDao.findByEmail(email)))).build();
            return Response.ok().entity(new TokenDTO(token)).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

}
