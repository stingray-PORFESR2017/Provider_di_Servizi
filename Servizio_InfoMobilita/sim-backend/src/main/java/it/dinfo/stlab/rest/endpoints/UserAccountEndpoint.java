package it.dinfo.stlab.rest.endpoints;

import it.dinfo.stlab.controllers.UserAccountController;
import it.dinfo.stlab.dto.UserAccountDTO;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@RolesAllowed("SUPER_ADMIN")
public class UserAccountEndpoint {

    @Inject
    private UserAccountController userAccountController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<UserAccountDTO> dtos = userAccountController.getAll();
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(UserAccountDTO userAccountDTOReceived){
        String uuid = userAccountController.create(userAccountDTOReceived);
        return this.getId(uuid);
    }

    @GET
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getId(@PathParam("uuid") String uuid) {
        UserAccountDTO dto = userAccountController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PUT
    @Path("/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("uuid") String uuid, UserAccountDTO userAccountDTOReceived) {
        userAccountController.update(uuid, userAccountDTOReceived);
        UserAccountDTO dto = userAccountController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("uuid") String uuid) {
        UserAccountDTO dto = userAccountController.getById(uuid);
        userAccountController.delete(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

}
