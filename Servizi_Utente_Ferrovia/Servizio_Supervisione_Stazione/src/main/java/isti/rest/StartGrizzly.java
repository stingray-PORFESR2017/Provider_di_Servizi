package isti.rest;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import isti.serviziosupervisionestazione.apirest.aut.AuthenticationFilter;
import isti.serviziosupervisionestazione.apirest.impl.HeaderFilter;

public class StartGrizzly {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI;
	public static final String BASEH_URI;
	public static final String protocol;
	public static final Optional<String> host;
	public static final String path;
	public static final Optional<String> port;
	private static final String KEYSTORE_SERVER_FILE;
	private static final String KEYSTORE_SERVER_PWD;


	static{
		protocol = "http://";
		host = Optional.ofNullable(System.getenv("HOSTNAME_STINGRAY"));
		port = Optional.ofNullable(System.getenv("PORT_STINGRAY"));
		path = "serviziosupervisionestazione";
		BASEH_URI = "https://" + host.orElse("0.0.0.0") + ":" + port.orElse("8443") + "/" + path + "/";
		BASE_URI = protocol + host.orElse("0.0.0.0") + ":" + port.orElse("9090") + "/" + path + "/";
		KEYSTORE_SERVER_FILE = "./src/main/config/jetty-server-ssl.jks";
		KEYSTORE_SERVER_PWD = "jetty8";
	}

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and providers
		// in com.example.rest package
			
		final ResourceConfig rc = new ResourceConfig().packages("isti.serviziosupervisionestazione.apirest.impl");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}
	
    public static void starthttps()  {
    	try {
        // Grizzly ssl configuration
        SSLContextConfigurator sslContext = new SSLContextConfigurator();

        // set up security context
        sslContext.setKeyStoreFile(KEYSTORE_SERVER_FILE); // contains server keypair
        sslContext.setKeyStorePass(KEYSTORE_SERVER_PWD);
        sslContext.setTrustStoreFile(KEYSTORE_SERVER_FILE); // contains client certificate
        sslContext.setTrustStorePass(KEYSTORE_SERVER_PWD);

        
        String resources = "isti.serviziosupervisionestazione.apirest.impl";
		 BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0.0");
	        beanConfig.setSchemes(new String[]{"https"});
	        //beanConfig.setHost(BASEH_URI);
	        beanConfig.setBasePath("/" + path + "/");
	        beanConfig.setResourcePackage(resources);
	        beanConfig.setScan(true);
        
        
        ResourceConfig rc = new ResourceConfig().packages(resources);
        rc.register(AuthenticationFilter.class);
        //rc.register(HeaderFilter.class);
        OpenApiResource openApiResource = new OpenApiResource();
        rc.register(openApiResource);
        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
       // rc.register(LoggingFilter.class);
        rc.register(JacksonFeature.class);     
        rc.register(JacksonXMLProvider.class); 
      
        final HttpServer grizzlyServer = GrizzlyHttpServerFactory.createHttpServer(
        		URI.create(BASEH_URI),
                rc,
                true,
                new SSLEngineConfigurator(sslContext).setClientMode(false).setNeedClientAuth(false)
        );

        
        
      /*  URL swaggerDistLocation =
        		StartGrizzly.class.getClassLoader().getResource("META-INF/resources/webjars/swagger-ui/2.2.2/");
        		 CLStaticHttpHandler swaggerDist = new CLStaticHttpHandler(new URLClassLoader(new URL[]{swaggerDistLocation}));
       */ 		 
        //grizzlyServer.getServerConfiguration().addHttpHandler(new CLStaticHttpHandler(StartGrizzly.class.getClassLoader(), "/docs/"), "/index.html");
        grizzlyServer.getServerConfiguration().addHttpHandler(new CLStaticHttpHandler(StartGrizzly.class.getClassLoader(), "/docs/"), "/docs/*");
      /*  ClassLoader loader = StartGrizzly.class.getClassLoader();
        CLStaticHttpHandler docsHandler = new CLStaticHttpHandler(loader, "swagger-ui/");
        docsHandler.setFileCacheEnabled(false);

        ServerConfiguration cfg = grizzlyServer.getServerConfiguration();
        cfg.addHttpHandler(swaggerDist, "/docs/");

        */
        
        // start Grizzly embedded server //
        System.out.println(String.format("Jersey app started with WADL available at "
				+ "%sapplication.wadl\nHit enter to stop it...", BASEH_URI));
        grizzlyServer.start();

    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
        //System.in.read();
        //grizzlyServer.stop();
    }


	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//starthttps();
		
		final HttpServer server = startServer();
		System.out.println(String.format("Jersey app started with WADL available at "
				+ "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
		System.in.read();
		server.stop();
	}
}



