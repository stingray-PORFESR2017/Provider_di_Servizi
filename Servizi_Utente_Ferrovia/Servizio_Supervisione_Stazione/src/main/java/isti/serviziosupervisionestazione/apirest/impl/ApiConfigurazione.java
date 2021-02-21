package isti.serviziosupervisionestazione.apirest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import isti.message.config.ConfigCommand;
import isti.message.config.StationConfig;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Api(value = "conf")
/*@SwaggerDefinition(securityDefinition = @SecurityDefinition(basicAuthDefinitions = {
	    @BasicAuthDefinition(key = "basicAuth")
	    }) )
@OpenAPIDefinition(info = @Info(title = "Configure Services"))*/
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_JSON)
@Path("/conf")
public class ApiConfigurazione {
	
	
	@Inject 
	TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiConfigurazione.class);
	
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Ricezione Configurazione XML", description = "Service demo with authentication.", security = { @SecurityRequirement(name = "basicAuth") }, responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Ricezione Configurazione XML", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        }
	  , notes = "Service demo with authentication."
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/update/{key:.*}")
	@POST
	public String receiveCommand(ConfigCommand message, @PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		
		log.trace(message);
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persistConfigCommand(message);
		trans.commit();
		return "OK";
	}
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Ricezione Configurazione XML", description = "Service demo with authentication. ", security = { @SecurityRequirement(name = "basicAuth") }, responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Ricezione Configurazione XML", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        }
	  , notes = "Service demo with authentication. "
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/update/station/{key:.*}")
	@POST
	public String receiveConfig(StationConfig message, @PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) throws JAXBException {
		
		log.trace(message);
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(StationConfig.class);

			
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //
		//NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
				Boolean.TRUE);

		marshaller.marshal( message, System.out );
		
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persistStationConfing(message);
		trans.commit();
		return "OK";
	}
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Lista di tutte le Configurazioni Attive XML", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Lista Configurazioni Attive XML", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        },
	  notes = "Service demo without authentication."
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/station/all")
	@GET
	public List<StationConfig> getStationAll(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		

		
		TypedQuery<StationConfig>	r = 	em.createNamedQueryStationConfig("StationConfig.findAll", StationConfig.class);
		List<StationConfig> res3 = r.getResultList();
		log.trace(res3);
		return res3;
	}
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Lista di tutte le Configurazioni Attive XML", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Lista Configurazioni Attive XML", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        },
	  notes = "Service demo without authentication."
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/all")
	@GET
	public List<ConfigCommand> getAll(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		

		
		TypedQuery<ConfigCommand>	r = 	em.createNamedQuery2("ConfigCommand.findAll", ConfigCommand.class);
		List<ConfigCommand> res3 = r.getResultList();
		log.trace(res3);
		return res3;
	}
	
	@RolesAllowed("ADMIN")
	@Operation(summary = "Service Configurazioni Attive per specifico ID", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service onfigurazioni Attive per specifico ID", 
			authorizations = {
		            @Authorization(value = "basicAuth", scopes={})
		        },
	  notes = "Service demo without authentication."
	)
	@ApiResponses(value = { @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
	        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized") })
	@Path("/spec/{key:.*}")
	@GET
	public ConfigCommand getSpec(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		
		
		TypedQuery<ConfigCommand>	r = 	em.createNamedQuery2("ConfigCommand.findAllimei", ConfigCommand.class);
		r.setParameter(1, key);
		ConfigCommand res3 = r.getSingleResult();
		log.trace(res3);
		
		return res3;
	}

}
