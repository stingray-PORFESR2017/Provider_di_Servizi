package isti.serviziosupervisionestazione.apirest.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import isti.JCMAD;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Produces(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_JSON)
@Path("/CMAD")
public class ApiServizioCMAD {

	@Inject 
	TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiServizioCMAD.class);


	@Path("/MAC_ADR/{key:.*}")
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
		JCMAD result = r.getSingleResult();

		if(result!=null){
			return result;
		}else{
			log.error("Element not found: "+key+";");
			return null;
		}

	}

	@Path("/MAC_ADR/{key:.*}")
	@GET
	public JCMAD daticmadmac(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {



		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
		r.setParameter(1, key);
		JCMAD result = r.getSingleResult();

		if(result!=null){
			return result;
		}else{
			log.error("Element not found: "+key+";");
			return null;
		}

	}

	@Path("/MAC_ADR_ALL/{key:.*}")
	@GET
	public List<JCMAD> daticmadmacall(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {



		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
		r.setParameter(1, key);
		List<JCMAD> result = r.getResultList();

		if(result!=null){
			return result;
		}else{
			log.error("Element not found: "+key+";");
			return null;
		}

	}


	@Path("/ALL/{key:.*}")
	@GET
	public List<JCMAD> alldaticmad(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {

		TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAll", JCMAD.class);
		List<JCMAD> res = r.getResultList();
		return res;
		//return new ArrayList<JCMAD>();

	}

}
