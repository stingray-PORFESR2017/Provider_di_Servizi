package isti.rest;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import isti.mqtt.subscriber.Application;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;

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

        ResourceConfig rc = new ResourceConfig().packages("isti.serviziosupervisionestazione.apirest.impl");

        final HttpServer grizzlyServer = GrizzlyHttpServerFactory.createHttpServer(
        		URI.create(BASEH_URI),
                rc,
                true,
                new SSLEngineConfigurator(sslContext).setClientMode(false).setNeedClientAuth(false)
        );

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
