package isti.serviziosupervisionestazione.apirest.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.grizzly.utils.Pair;


//@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_JSON)
@Path("/pis")
public class ApiServizioSupervisioneStazione {

	// @Inject
	// TokenPersistence em;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiServizioSupervisioneStazione.class);

	private static Map<String, BigDecimal> map = new HashMap<>();

	private static Map<String, Pair<Integer, Date>> timediff = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();

	@PermitAll
	@Path("/test")
	@GET
	public String test() {

		map = new HashMap<>();
		ricevuti = new HashMap<>();
		timediff = new HashMap<>();
		log.info("Test OK\n\r");
		return "<html><body>OK</body></html>";
	}

	@PermitAll
	@Path("/viaggiatreno/{key:.*}")
	@GET
	public void viaggiatreno(@PathParam("key") String key, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String path = "http://www.viaggiatreno.it/viaggiatrenonew/resteasy/viaggiatreno/";
		String url  = path + key;

		log.info(url+"\n\r");
		try {
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", url);
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		//return "<html><body>OK</body></html>";
	}
	
	@PermitAll
	@Path("/viaggiatreno/site/{key:.*}")
	@POST
	public String postviaggiatrenosite(@PathParam("key") String key, 
			@FormParam("stazione") String stazione, 
			@FormParam("lang") String lang, 
			@FormParam("codiceStazione") String codiceStazione,
			
			
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		String path = "http://www.viaggiatreno.it/vt_pax_internet/";
		
		
		
		String url  = path + key;

		log.info(url+"\n\r");
		
		
		String contenttype = "text/html";
		try {
			if(request!=null)
				contenttype = request.getContentType();
			log.info(url+"\n\r");
			

				Client client = ClientBuilder.newClient();
				if(contenttype==null)
					contenttype = "application/json";
				
				Form form = new Form();
				if(stazione!=null)
				form.param("stazione", stazione).param("lang", lang);
				if(codiceStazione!=null)
					form.param("codiceStazione", codiceStazione);
				//javax.ws.rs.client.Entity<String> e = javax.ws.rs.client.Entity.entity( tmp , "text/html");
				
				Response response1 = client.target(url).request().header("Content-Type", contenttype).post(javax.ws.rs.client.Entity.form(form));
				String responseAsString = response1.readEntity(String.class);
				String res = responseAsString.replaceAll("background-image: url(\"../images/header_mobile.png\");","");
				 res = res.replaceAll("http://www.viaggiatreno.it/vt_pax_internet/","https://localhost:8443/serviziosupervisionestazione/pis/viaggiatreno/site/");
				 res = res.replaceAll("/vt_pax_internet/","https://localhost:8443/serviziosupervisionestazione/pis/viaggiatreno/site/");
				 res = res.replaceAll("../images/iconaRicerca.png","http://www.viaggiatreno.it/vt_pax_internet/images/iconaRicerca.png");
				 res  = res.replaceAll("Copyright Trenitalia S.p.A. 2006.", "");
				res  = res.replaceAll("Tutti i diritti riservati.", "");
				 return res;
				/*response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", url);
				response.sendRedirect(url);*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e);
			}
			String err =  "<html><body>ERROR</body></html>";
			return err;
		
		
		
		//return "<html><body>OK</body></html>";
	}
	
	
	@PermitAll
	@Path("/viaggiatreno/site/{key:.*}")
	@GET
	public String viaggiatrenosite(@PathParam("key") String key, 
			@QueryParam("numeroTreno") String numeroTreno, 
			@QueryParam("codLocOrig") String codLocOrig, 
			@QueryParam("tipoRicerca") String tipoRicerca,
			@Context UriInfo uriInfo,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		String path = "http://www.viaggiatreno.it/vt_pax_internet/";
		
		
		
		
		//List<String> listparametri = new ArrayList<String>();
		
		
		String url  = path + key;

		log.info(url+"\n\r");
		
		
		String contenttype = null;
		try {
			if(request!=null)
				contenttype = request.getContentType();
			log.info(url+"\n\r");
			
			

				Client client = ClientBuilder.newClient();
				if(contenttype==null)
					contenttype = "application/json";
				
				
				 WebTarget target = client.target(url);
				    
				    // Instance variable uriInfo.
				    MultivaluedMap<String, String> parameters = uriInfo.getQueryParameters();
				    
				    if (parameters != null && parameters.size() > 0) {
				    	
				    	
				        Iterator it = parameters.keySet().iterator();
				        String k = null;
				        StringTokenizer st = null;
				        
				        while (it.hasNext()) {
				            k = (String)it.next();
				            
				            // RESTEasy API is quirky, here. It wraps the values in square 
				            // brackets; moreover, each value is separated by a comma and  
				            // padded by a space as well. ([one, two, three, etc])
				            
				            st = new StringTokenizer(parameters.get(k).toString(), "[,]");
				            while (st.hasMoreTokens()) {
				                target = target.queryParam(k, st.nextToken().trim());
				            }
				        }
				    }
				
				
				
				Response response1 = target.request().header("Content-Type", contenttype).get();
				String responseAsString = response1.readEntity(String.class);
				String res = responseAsString.replaceAll("background-image: url(\"../images/header_mobile.png\");","");
				 res = res.replaceAll("http://www.viaggiatreno.it/vt_pax_internet/","https://localhost:8443/serviziosupervisionestazione/pis/viaggiatreno/site/");
				 res = res.replaceAll("/vt_pax_internet/","https://localhost:8443/serviziosupervisionestazione/pis/viaggiatreno/site/");
				 res = res.replaceAll("../images/iconaRicerca.png","http://www.viaggiatreno.it/vt_pax_internet/images/iconaRicerca.png");
				 res  = res.replaceAll("Copyright Trenitalia S.p.A. 2006.", "");
				res  = res.replaceAll("Tutti i diritti riservati.", "");
				 return res;
				/*response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", url);
				response.sendRedirect(url);*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e);
			}
			String err =  "<html><body>ERROR</body></html>";
			return err;
		
		
		
		//return "<html><body>OK</body></html>";
	}



}
