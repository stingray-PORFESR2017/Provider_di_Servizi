package it.dinfo.stlab.rest.endpoints;

import it.dinfo.stlab.controllers.SmartStationController;
import it.dinfo.stlab.dao.InfomobilityServiceDao;
import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dao.UserAccountDao;
import it.dinfo.stlab.dto.SmartStationDTO;
import it.dinfo.stlab.dto.SmartStationDTOLight;
import it.dinfo.stlab.eai.ambientaldata.controllers.AmbientalDataController;
import it.dinfo.stlab.eai.ambientaldata.dto.AmbientalDataDTO;
import it.dinfo.stlab.eai.apiproviders.dto.Location;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.user.UserAccount;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Path("/smart-stations")
public class SmartStationEndpoint {

    @Inject
    private SmartStationDao smartStationDao;

    @Inject
    private SmartStationController smartStationController;

    @Inject
    private UserAccountDao userAccountDao;

    @Inject
    private InfomobilityServiceDao ispDao;

    @Inject
    private AmbientalDataController ambientalDataController;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<SmartStationDTO> dtos = smartStationController.getAll();
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})  //dichiaro il formato dei dati ricevuti all’interno della Request
    @Produces({MediaType.APPLICATION_JSON})  //dichiaro il formato dei dati della Response
    @RolesAllowed({"SUPER_ADMIN","ADMIN"})
    public Response create(SmartStationDTO smartStationDTOReceived){
        String uuid = smartStationController.create(smartStationDTOReceived); //salvo nel db
        return this.getId(uuid); //faccio la query per verificare di averlo inserito
    }

    @GET
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"SUPER_ADMIN","ADMIN"})
    public Response getId(@PathParam("uuid") String uuid) {
        SmartStationDTO dto = smartStationController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Path("/{uuid}/location")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocation(@PathParam("uuid") String uuid) {
        Location dto = smartStationController.getLocationById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PUT
    @Path("/{uuid}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"SUPER_ADMIN","ADMIN"})
    public Response update(@PathParam("uuid") String uuid, SmartStationDTO smartStationDTOReceived) {
        smartStationController.update(uuid, smartStationDTOReceived);
        SmartStationDTO dto = smartStationController.getById(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Transactional
    @Path("/{uuid}/picture")
    @Produces("image/png")
    public Response getPicture(
            @PathParam("uuid") String uuid) {
        byte[] picture = smartStationController.getPicture(uuid);
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
            smartStationController.updatePicture(uuid, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"SUPER_ADMIN","ADMIN"})
    public Response delete(@PathParam("uuid") String uuid) {
        SmartStationDTO dto = smartStationController.getById(uuid); //salvo il dto primo della cancellazione in modo da poterlo inviare in risposta
        smartStationController.delete(uuid);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PATCH
    @Path("/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"SUPER_ADMIN","ADMIN"})
    public Response enableDisable( @QueryParam("enable") Boolean enable, @PathParam("uuid") String uuid) {
        SmartStationDTO dto = smartStationController.enableDisable(uuid, enable);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Path("/associable-infomobility-services/{ispUuid}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("ADMIN")
    public Response getAllSsAssociableForOneIspForOneAdmin(@PathParam("ispUuid") String ispId) {
        UserAccount user = userAccountDao.findByEmail(securityContext.getUserPrincipal().getName());
        InfomobilityServiceProvider isp = ispDao.findById(ispId);
        List<SmartStationDTOLight> dtos = smartStationController.getAllSsAssociableForOneIspForOneAdmin(user, isp);
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @GET
    @Path("/getAllAuthorized")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("ADMIN")
    public Response getAllAuthorizedSSForAdmin() {
        UserAccount user = userAccountDao.findByEmail(securityContext.getUserPrincipal().getName());
        List<SmartStationDTO> dtos = smartStationController.getAllAuthorizedIspForAdmin(user);
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @GET
    @Path("/municipality/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllSsByMunicipality(@PathParam("uuid") String municipalityId) {
        List<SmartStationDTO> dtos = smartStationController.getAllEnabledSsByMunicipality(municipalityId);
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @GET
    @Path("/ambientalData/{uuid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAmbientalDataForSS(@PathParam("uuid") String ssId) throws IOException {
        SmartStation ss = smartStationDao.findById(ssId);
        AmbientalDataDTO dto = ambientalDataController.getAmbientalDataInfo(ss.getCmadMacAddress());
        return Response.status(Response.Status.OK).entity(dto).build();
    }

}
