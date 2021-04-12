package it.dinfo.stlab.rest.endpoints;

import it.dinfo.stlab.controllers.InfomobilityServiceController;
import it.dinfo.stlab.dao.InfomobilityServiceDao;
import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.dto.InfomobilityServiceDTO;
import it.dinfo.stlab.dto.InfomobilityServiceDTOLight;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.user.UserAccount;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Path("/infomobility-services")
public class InfomobilityServiceEndpoint {  //la classe identifica l'endpoint(/infomobility-services)

	@Inject
	private InfomobilityServiceController infomobilityServiceController;
	@Inject
	private SmartStationDao smartStationDao;
	@Inject
	private InfomobilityServiceDao infomobilityServiceDao;
	@Context
	private SecurityContext securityContext;
	@Inject
	private UserAccountDao userAccountDao;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll() {
		List<InfomobilityServiceDTO> dtos = infomobilityServiceController.getAll();
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})  //dichiaro il formato dei dati ricevuti all’interno della Request
	@Produces({MediaType.APPLICATION_JSON})  //dichiaro il formato dei dati della Response
	@RolesAllowed("SUPER_ADMIN")
	public Response create(InfomobilityServiceDTO infomobilityServiceDTOReceived) {
		String uuid = infomobilityServiceController.create(infomobilityServiceDTOReceived); //salvo nel db
		return this.getId(uuid); //faccio la query per verificare di averlo inserito
	}

	@GET
	@Path("/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"SUPER_ADMIN", "ADMIN"})
	public Response getId(@PathParam("uuid") String uuid) {
		InfomobilityServiceDTO dto = infomobilityServiceController.getById(uuid);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@PUT
	@Path("/{uuid}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"SUPER_ADMIN", "ADMIN"})
	public Response update(@PathParam("uuid") String uuid, InfomobilityServiceDTO infomobilityDTOReceived) {
		infomobilityServiceController.update(uuid, infomobilityDTOReceived);
		InfomobilityServiceDTO dto = infomobilityServiceController.getById(uuid);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@GET
	@Transactional
	@Path("/{uuid}/picture")
	@Produces("image/png")
	public Response getPicture(
			@PathParam("uuid") String uuid) {
		byte[] picture = infomobilityServiceController.getPicture(uuid);
		return Response.ok(picture, "image/png").build();
	}

	@POST
	@Path("/{uuid}/picture")
	@Consumes({"image/jpeg,image/png"})
	@RolesAllowed("SUPER_ADMIN")
	public Response updatePicture(
			@PathParam("uuid") String uuid,
			InputStream inputStream) {
		try {
			infomobilityServiceController.updatePicture(uuid, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"SUPER_ADMIN", "ADMIN"})
	public Response delete(@PathParam("uuid") String uuid) {
		InfomobilityServiceDTO dto = infomobilityServiceController.getById(uuid);
		infomobilityServiceController.delete(uuid);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@PATCH
	@Path("/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed({"SUPER_ADMIN", "ADMIN"})
	public Response enableDisable(@QueryParam("enable") Boolean enable, @PathParam("uuid") String uuid) {
		InfomobilityServiceDTO dto = infomobilityServiceController.enableDisable(uuid, enable);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@GET
	@Path("/{uuid}/vehicles")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAvailableVehicles(@PathParam("uuid") String uuid, @QueryParam("ssuuid") String ssuuid) throws IOException, JAXBException {
		SmartStation ss = smartStationDao.findById(ssuuid);
		InfomobilityServiceProvider isp = infomobilityServiceDao.findById(uuid);
		List<VehicleDto> availableVehicles = null;
		try {
			availableVehicles = infomobilityServiceController.getAvailableVehicles(ss, isp);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore nel recupero dei veicoli disponibili.");
			return Response.status(Response.Status.BAD_REQUEST).entity(
					"Qualcosa è andato storto col recupero dei veicoli disponibili. Forse non è disponibile " +
                            "l'integrazione con questo provider.").build();
		}
		return Response.status(Response.Status.OK).entity(availableVehicles).build();
	}

	@GET
	@Path("/associable-smart-stations/{ssUuid}")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("ADMIN")
	public Response getAllIspAssociableForOneSsForOneAdmin(@PathParam("ssUuid") String ssId) {
		UserAccount user = userAccountDao.findByEmail(securityContext.getUserPrincipal().getName());
		SmartStation ss = smartStationDao.findById(ssId);
		List<InfomobilityServiceDTOLight> dtos =
				infomobilityServiceController.getAllIspAssociableForOneSsForOneAdmin(user, ss);
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

	@GET
	@Path("/getAllAuthorized")
	@Produces({MediaType.APPLICATION_JSON})
	@RolesAllowed("ADMIN")
	public Response getAllAuthorizedIspForAdmin() {
		UserAccount user = userAccountDao.findByEmail(securityContext.getUserPrincipal().getName());
		List<InfomobilityServiceDTO> dtos = infomobilityServiceController.getAllAuthorizedIspForAdmin(user);
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

	@GET
	@Path("/smart-station/{uuid}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllIspInOneSS(@PathParam("uuid") String ssId) {
		List<InfomobilityServiceDTO> dtos = infomobilityServiceController.getAllIspInOneSS(ssId);
		return Response.status(Response.Status.OK).entity(dtos).build();
	}

}
