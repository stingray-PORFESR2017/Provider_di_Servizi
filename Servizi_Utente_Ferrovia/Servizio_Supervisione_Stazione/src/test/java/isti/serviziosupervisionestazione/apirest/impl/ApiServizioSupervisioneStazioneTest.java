package isti.serviziosupervisionestazione.apirest.impl;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;


public class ApiServizioSupervisioneStazioneTest extends JerseyTest {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiServizioSupervisioneStazioneTest.class);

	
	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return ServletDeploymentContext.forServlet(new ServletContainer(new ResourceConfig(ApiServizioSupervisioneStazione.class)))
				.build();

	}

	@Override
	protected Application configure() {
		return new ResourceConfig(ApiServizioSupervisioneStazione.class);
	}


	@Test
	public void test() {
		
		
		Response response = target("/pis/viaggiatreno/regione/S12878").request().get();

		int x = response.getStatus();
		assertEquals(200, x);
		String res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);
		
	}

}
