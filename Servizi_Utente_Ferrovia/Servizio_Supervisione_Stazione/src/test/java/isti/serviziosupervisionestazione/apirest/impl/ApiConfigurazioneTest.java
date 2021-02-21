package isti.serviziosupervisionestazione.apirest.impl;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.inject.Singleton;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;

import isti.message.config.StationConfig;
import isti.message.impl.cmad.JCMADCommand;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

public class ApiConfigurazioneTest extends JerseyTest {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiConfigurazioneTest.class);

	
	
	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");

		AbstractBinder binder = new AbstractBinder() {

			@Override
			protected void configure() {
				bindFactory(PersistenceMemory.class).to(TokenPersistence.class).in(Singleton.class);

			}
		};
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class,ApiServizioSupervisioneStazioneRFI.class, ApiServizioCMAD.class, ApiConfigurazione.class);
		r.register(binder);

		return ServletDeploymentContext.forServlet(new ServletContainer(r))
				.build();

	}

	@Override
	protected javax.ws.rs.core.Application configure() {


		AbstractBinder binder = new AbstractBinder() {

			@Override
			protected void configure() {
				bindFactory(PersistenceMemory.class).to(TokenPersistence.class).in(Singleton.class);



			}
		};
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class,ApiServizioSupervisioneStazioneRFI.class, ApiServizioCMAD.class, ApiConfigurazione.class);
		r.register(binder);
		return r;
	}
	@Test
	public void test() throws JAXBException {
		String commandstatiion  = "<StationConfig>\n" + 
				"    <id>string</id>\n" + 
				"    <Descrizione>string</Descrizione>\n" + 
				"    <ListCMAD>\n" + 
				"        <MacCMAD>eee</MacCMAD>\n" + 
				"        <MacCMAD>444</MacCMAD>\n" + 
				"    </ListCMAD>\n" + 
				"</StationConfig>";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(StationConfig.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(commandstatiion);//.replaceAll("\\n", "").trim());
		StationConfig jcmadcommand = (StationConfig) unmarshaller.unmarshal(reader);
		
		
		Entity<StationConfig> rex = Entity.entity(jcmadcommand, MediaType.APPLICATION_XML_TYPE);


		 Response response = target("/conf/update/station/").request(MediaType.APPLICATION_XML).post(rex);



		String res2 = response.readEntity(new GenericType<String>() {
		});
		//Thread.sleep(5000);
		System.out.print(res2);
	}
	
	@Test
	public void test2() throws JAXBException {
		
		
		Response response = target("/conf/station/all/").request().get();
		int x = response.getStatus();
		//assertEquals(200, x);
		String res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);
		
		
	}

}
