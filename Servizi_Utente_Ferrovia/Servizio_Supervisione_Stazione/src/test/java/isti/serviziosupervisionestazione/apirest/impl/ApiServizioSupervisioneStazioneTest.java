package isti.serviziosupervisionestazione.apirest.impl;

import static org.junit.Assert.assertEquals;

import java.util.Base64;

import javax.inject.Singleton;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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

import isti.message.MessageCMAD;
import isti.message.impl.cmad.JCMAD;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;



//@RunWith(WeldJUnit4Runner.class)
public class ApiServizioSupervisioneStazioneTest extends JerseyTest {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApiServizioSupervisioneStazioneTest.class);


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
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class, ApiServizioCMAD.class);
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
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class, ApiServizioCMAD.class);
		r.register(binder);
		return r;
	}

	/*
	@Rule
    public WeldInitiator weld = WeldInitiator.from(new Weld().enableDevMode())//.addAlternative(PersistenceMemory.class))
        .inject(this)
        .build();
	 */


	//@Inject 
	//private TokenPersistence em;



	@Test
	public void test() {
		//WeldInitiator weld = WeldInitiator.from(TokenPersistence.class).inject(PersistenceMemory.class).build();
		//PersistenceMemory ema = weld.select(PersistenceMemory.class).get();
		//isti.Application applica = weld.select(isti.Application.class).get();
		//applica.run();<
		String encodedString = "Q///BQYHCAAZCgBDTUFEIEdJT1JHSU8gICAgICAgIEBLTACA8PoCAQARAEkAIgBgAL0A1QAHAQAA5AF/ACYAEAHQAeABvAKfAAAAeQFOAVYAAAAAAAAAAAAAAAAAAAAAAAAAAFYt";

		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

		MessageCMAD mCMAD = new MessageCMAD(decodedBytes);
		JCMAD ff = mCMAD.getJCMAD();
		
		

		PersistenceMemory pem = new PersistenceMemory();
		TokenPersistence em = pem.provide();

		JCMAD elementRead = em.findid(JCMAD.class, ff.getId());

		
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(ff);
		trans.commit();
		
		elementRead = em.findid(JCMAD.class, ff.getId());

		if(elementRead==null){
			if(elementRead.equals(ff)) {
				System.out.println("Ritrasmissione");
			}
		}

		Response response = target("/pis/viaggiatreno/regione/S12878").request().get();

		int x = response.getStatus();
		assertEquals(200, x);
		String res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);

		response = target("/CMAD/ALL/").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);

		response = target("/CMAD/MAC_ADR_ALL/ffff0506070").request().get();
		response = target("/CMAD/MAC_ADR_ALL/ffff05060708").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);


		response = target("/CMAD/MAC_ADR/ffff0506070").request().get();
		response = target("/CMAD/MAC_ADR/ffff05060708").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);



	}

}
