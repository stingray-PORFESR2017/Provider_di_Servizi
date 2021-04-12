package it.dinfo.stlab.rest.endpoints;

import it.dinfo.stlab.controllers.AdminAuthorizationController;
import it.dinfo.stlab.dto.AdminAuthorizationDTO;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/adminAuthorization")
@RolesAllowed("SUPER_ADMIN")
public class AdminAuthorizationEndpoint {

    @Inject
    private AdminAuthorizationController adminAuthController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<AdminAuthorizationDTO> dtos = adminAuthController.getAll();
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(AdminAuthorizationDTO adminAuthDTOReceived){
        String uuid = adminAuthController.create(adminAuthDTOReceived);
        return this.getId(uuid);
    }

    @GET
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getId(@PathParam("uuid") String uuid) {
        AdminAuthorizationDTO dto = adminAuthController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PUT
    @Path("/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("uuid") String uuid, AdminAuthorizationDTO adminAuthDTOReceived) {
        adminAuthController.update(uuid, adminAuthDTOReceived);
        AdminAuthorizationDTO dto = adminAuthController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("uuid") String uuid) {
        AdminAuthorizationDTO dto = adminAuthController.getById(uuid);
        adminAuthController.delete(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Path("/user/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllAuthForOneUser(@PathParam("uuid") String uuid) {
        List<AdminAuthorizationDTO> dtos = adminAuthController.getAllAuthForOneUser(uuid);
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

}
