package it.dinfo.stlab.rest.endpoints;

import it.dinfo.stlab.controllers.MunicipalityController;
import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.dto.MunicipalityDTO;
import it.dinfo.stlab.model.user.UserAccount;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.InputStream;
import java.util.List;

@Path("/municipality")
public class MunicipalityEndpoint {

	@Context
	SecurityContext securityContext;
	@Inject
	private MunicipalityController municipalityController;
	@Inject
	private UserAccountDao userAccountDao;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll() {
		List<MunicipalityDTO> dtos = municipalityController.getAll();
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/notempty")
	public Response getMunicipalityWithSmartStations() {
		List<MunicipalityDTO> dtos = municipalityController.getAllNotEmpty();
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("SUPER_ADMIN")
	public Response create(MunicipalityDTO municipalityDTOReceived) {
		String uuid = municipalityController.create(municipalityDTOReceived);
		return this.getId(uuid);
	}

	@GET
	@Path("/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"SUPER_ADMIN", "ADMIN"})
	public Response getId(@PathParam("uuid") String uuid) {
		MunicipalityDTO dto = municipalityController.getById(uuid);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@PUT
	@Path("/{uuid}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("SUPER_ADMIN")
	public Response update(@PathParam("uuid") String uuid, MunicipalityDTO municipalityDTOReceived) {
		municipalityController.update(uuid, municipalityDTOReceived);
		MunicipalityDTO dto = municipalityController.getById(uuid);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@GET
	@Transactional
	@Path("/{uuid}/picture")
	@Produces("image/png")
	public Response getPicture(
			@PathParam("uuid") String uuid) {
		byte[] picture = municipalityController.getPicture(uuid);
		return Response.ok(picture,"image/png").build();
	}

	@POST
	@Path("/{uuid}/picture")
	@Consumes({"image/jpeg,image/png"})
	@RolesAllowed("SUPER_ADMIN")
	public Response updatePicture(
			@PathParam("uuid") String uuid,
			InputStream inputStream) {
		try {
			municipalityController.updatePicture(uuid, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("SUPER_ADMIN")
	public Response delete(@PathParam("uuid") String uuid) {
		MunicipalityDTO dto = municipalityController.getById(uuid);
		if (municipalityController.delete(uuid))
			return Response.status(Response.Status.OK).entity(dto).build();
		else
			return Response.status(Response.Status.CONFLICT).entity(dto).build();
	}

	@GET
	@Path("/getAllAuthorized")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("ADMIN")
	public Response getAllAuthMunicipalityForAdmin() {
		UserAccount user = userAccountDao.findByEmail(securityContext.getUserPrincipal().getName());
		List<MunicipalityDTO> dtos = municipalityController.getAllForAdmin(user);
		return Response.status(Response.Status.OK).entity(dtos).build();
	}
}
