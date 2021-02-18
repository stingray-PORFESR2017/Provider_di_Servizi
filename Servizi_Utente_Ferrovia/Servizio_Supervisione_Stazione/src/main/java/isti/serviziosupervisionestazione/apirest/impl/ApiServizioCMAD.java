package isti.serviziosupervisionestazione.apirest.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.TitledBorder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.info.Info;
import isti.message.CommandCMAD;
import isti.message.config.ConfigCommand;
import isti.message.config.AuthInfo;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.mqtt.publisher.Publisher;
import isti.mqtt.publisher.thread.PubThread;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;


@Api(value = "CMAD")
@SwaggerDefinition(info = @io.swagger.annotations.Info(title = "SUF Services", version = "0.1", description = "API of SUF of Stingray Project",
contact = @Contact(
        name = "Giorgio O. Spagnolo",
        email = "spagnolo@isti.cnr.it"
    )),securityDefinition = @SecurityDefinition(basicAuthDefinitions = {
	    @BasicAuthDefinition(key = "basicAuth")
	    }), host = "https://stingray.isti.cnr.it:8443/docs/" )
@OpenAPIDefinition(info = @Info(title = "SUF Services", version = "0.1"))
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_JSON)
@Path("/CMAD")
public class ApiServizioCMAD {

	@Inject 
	TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiServizioCMAD.class);


	@Operation(summary = "Service Dati CMAD by MacAddress e data", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD by MacAddress e data", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/MAC_ADR_BT/{key:.*}")
	@GET
	public JCMAD daticmad(@PathParam("key") String key, @QueryParam("datei") String datei , @QueryParam("datef") String datef , @Context HttpServletRequest request, @Context HttpServletResponse response) throws ParseException {

		if(datei==null || datef == null) {
			return null;
		}

		Date dateini = new SimpleDateFormat("yyyy-MM-dd").parse(datei);
		Date datefinal = new SimpleDateFormat("yyyy-MM-dd").parse(datef);

		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findMacBetweenTime", JCMAD.class);
		r.setParameter(1, key);
		r.setParameter(2, dateini, TemporalType.DATE);
		r.setParameter(3, datefinal, TemporalType.DATE);
		List<JCMAD> result = r.getResultList();

		if(result!=null){
			if(!result.isEmpty())
				return result.get(0);
		}else{
			log.error("Element not found: "+key+";");
			return null;
		}
		return null;

	}

	@Operation(summary = "Service Dati CMAD last by MacAddress", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD last by MacAddress", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/MAC_ADR/{key:.*}")
	@GET
	public JCMAD daticmadmac(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {


		try {
		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
		r.setParameter(1, key);
		List<JCMAD> results = r.getResultList();
		if(results.size()>0) {
			JCMAD result = results.get(0);

			if(result!=null){
				return result;
			}
		}

		log.error("Element not found: "+key+";");
		return null;
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return null;

	}

	@Operation(summary = "Service Dati CMAD all by MacAddress", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD all by MacAddress", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/MAC_ADR_ALL/{key:.*}")
	@GET
	public List<JCMAD> daticmadmacall(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {

		try {

			TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
			r.setParameter(1, key);
			List<JCMAD> result = r.getResultList();

			if(result!=null){
				if(!result.isEmpty()){	
					return result;
				}else{
					log.error("Element not found: "+key+";");
					return null;
				}		
			}else{
				log.error("Element not found: "+key+";");
				return null;
			}

		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return null;

	}
	
	@Operation(summary = "Service Dati CMAD All", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD All Order by date", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/ALL_ORDER/{key:.*}")
	@GET
	public List<JCMAD> alldaticmadbydate(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {

		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllorder", JCMAD.class);
		List<JCMAD> temp = r.getResultList();

		return temp;
		//return new ArrayList<JCMAD>();

	}
	
	@Operation(summary = "Service Dati CMAD All", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD All Last", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/ALL_LAST/{key:.*}")
	@GET
	public List<JCMAD> alllastcmad(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<JCMAD> res = new ArrayList<JCMAD>();
		try {
		TypedQuery<String>	r22 = 	em.createNamedQueryS("JCMAD.finddistcmad", String.class);
		
		List<String> temp22 = r22.getResultList();
		
		
		
		for(String kmac: temp22) {
			
			TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
			r.setParameter(1, kmac);
			List<JCMAD> temp = r.getResultList();
			if(key.length()>0 ) {
				if(kmac.equals(key)) {
					res.add(temp.get(0));
				}
			}else
				res.add(temp.get(0));
		}
		
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		return res;
		//return new ArrayList<JCMAD>();

	}

	@Operation(summary = "Service Dati CMAD All", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati CMAD All", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@Path("/ALL/{key:.*}")
	@GET
	public List<JCMAD> alldaticmad(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAll", JCMAD.class);
		List<JCMAD> res = r.getResultList();
		return res;
		//return new ArrayList<JCMAD>();
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		
		return null;
	}
	
/*	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Ricezione Commandi byte[]", description = "Service demo with authentication. Login is 'guest' and password is 'password'", security = { @SecurityRequirement(name = "basicAuth") }, responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Ricezione Commandi byte[]", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        }
	  , notes = "Service demo with authentication. Login is 'guest' and password is 'password'"
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/updateByte/{key:.*}")
	@POST
	public void receiveCommandByte(byte[] messagebyte, @PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		//Publisher p = new Publisher();
		//p.send(message,key);
		
		Runnable runnable = () -> {
			Publisher p = new Publisher();
			CommandCMAD command = new CommandCMAD();
			String keys = command.getMAC_ADR(messagebyte);
			
			p.send(messagebyte,keys);
			log.trace(Base64.getEncoder().encodeToString(messagebyte));
			try {
				Thread.sleep(1000);
				byte[] messagebytes = command.getMessageNull(keys);
				p.send(messagebytes,keys);
				log.trace(Base64.getEncoder().encodeToString(messagebytes));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(runnable);

        thread.start();
	}*/
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Ricezione Commandi XML", description = "Service demo with authentication. Login is 'guest' and password is 'password'", security = { @SecurityRequirement(name = "basicAuth") }, responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Ricezione Commandi XML", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        }
	  , notes = "Service demo with authentication. Login is 'guest' and password is 'password'"
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/update/{key:.*}")
	@POST
	public String receiveCommand(JCMADCommand message, @PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		/*Publisher p = new Publisher();
		CommandCMAD command = new CommandCMAD();
		p.send(command.getMessage(message),key);*/
		log.trace(message);
		try {
		TypedQuery<ConfigCommand>	r = 	em.createNamedQuery2("ConfigCommand.findAllimei", ConfigCommand.class);
		String imei = message.getId();
		r.setParameter(1, imei);
		List<ConfigCommand> res3 = r.getResultList();
		if(!res3.isEmpty()) {
			ConfigCommand res2 = res3.get(0);
			if(res2.isGod()) {
				PubThread th = new PubThread (message);
				Thread thread = new Thread(th);
				
		        thread.start();
			}
		}
		log.trace(res3);
		
		PubThread th = new PubThread (message);
		Thread thread = new Thread(th);
		
        thread.start();
		return "OK";
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return "KO";
	}

	
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service AuthInfo", description = "Service demo with authentication. Login is 'guest' and password is 'password'", security = { @SecurityRequirement(name = "basicAuth") }, responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service AuthInfo", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        }
	  , notes = "Service demo with authentication. Login is 'guest' and password is 'password'"
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/AuthInfo/{key:.*}")
	@POST
	public String AuthInfo(AuthInfo message, @PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		/*Publisher p = new Publisher();
		CommandCMAD command = new CommandCMAD();
		p.send(command.getMessage(message),key);*/
		log.trace(message);
		
		
		return "OK";
	}
	
	
}
