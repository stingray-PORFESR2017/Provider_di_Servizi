package isti.serviziosupervisionestazione.apirest.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.paho.client.mqttv3.MqttException;
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
import isti.message.impl.ill.JMADILL;
import isti.message.impl.red.JMadRed;
import isti.mqtt.subscriber.Subscriber;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;
import junit.framework.Assert;



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
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class,ApiServizioSupervisioneStazioneRFI.class, ApiServizioCMAD.class);
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
		ResourceConfig r = new ResourceConfig(ApiServizioSupervisioneStazione.class,ApiServizioSupervisioneStazioneRFI.class, ApiServizioCMAD.class);
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
	public void test1() throws JAXBException, IOException {
		
String encodedString = "Q6qu/6CIBwABWABDTUFEIERJIFRFU1QgICAgICAgIAA1DADQ3QYACAAAAg0AjQAvALAAKgFAnJABQJyIE4gTiBOIE3AXcBdg6mDqYOpwF3AXAAAAAAAAAAAAAAAAAAAAAAAAAGxmUgAAACIAAAEAAABNQURSRUQgREkgVEVTVCAgICAgIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABMAAAAAAA0AgAAAE1BRElMTCBESSBURVNUICAgICAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

		MessageCMAD mCMAD = new MessageCMAD(decodedBytes);
		JCMAD ff = mCMAD.getJCMAD();
		JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(JCMAD.class);
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		 marshaller.marshal( ff, System.out );
		
	}
	
	@Test
	public void test2() throws JAXBException, IOException {
		
		//https://stingray.isti.cnr.it:8443/serviziosupervisionestazione/rfi/FrontEnd/Train/GetArrivals?PlaceId=2156
		
		Response response = target("/rfi/FrontEnd/Train/GetArrivals").queryParam("PlaceId", "2156").request().get();

		int x = response.getStatus();
		assertEquals(200, x);
		String res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);
		
	}


	@Test
	public void test() throws ParseException, MqttException {
		//WeldInitiator weld = WeldInitiator.from(TokenPersistence.class).inject(PersistenceMemory.class).build();
		//PersistenceMemory ema = weld.select(PersistenceMemory.class).get();
		//isti.Application applica = weld.select(isti.Application.class).get();
		//applica.run();<
		//String encodedString = "Q///BQYHCAAZCgBDTUFEIEdJT1JHSU8gICAgICAgIEBLTACA8PoCAQARAEkAIgBgAL0A1QAHAQAA5AF/ACYAEAHQAeABvAKfAAAAeQFOAVYAAAAAAAAAAAAAAAAAAAAAAAAAAFYt";
		Subscriber s = new Subscriber("");
		
		assertNotNull(s);
		
		
		String encodedString = "Q6qu/6CIBwABWABDTUFEIERJIFRFU1QgICAgICAgIAA1DADQ3QYACAAAAg0AjQAvALAAKgFAnJABQJyIE4gTiBOIE3AXcBdg6mDqYOpwF3AXAAAAAAAAAAAAAAAAAAAAAAAAAGxmUgAAACIAAAEAAABNQURSRUQgREkgVEVTVCAgICAgIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABMAAAAAAA0AgAAAE1BRElMTCBESSBURVNUICAgICAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

		String encodedString2 = "Q6qu/6CIBwABWABDTUFE1ERJIFRFU1QgICAgICAgIAA1DADQ3QYACAAAAg0AjQAvALAAKgFAnJABQJyIE4gTiBOIE3AXcBdg6mDqYOpwF3AXAAAAAAAAAAAAAAAAAAAAAAAAAGxmUgAAACIAAAEA1ABNQURSRUQgREkgVEVTVCAgICAgIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1AABMAAAAAAA0AgAAAE1BRElMTCBESSBURVNUICAgICAgAAAAAAAAAAAAA1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		
		byte[] decodedBytes2 = Base64.getDecoder().decode(encodedString2);

		MessageCMAD mCMAD = new MessageCMAD(decodedBytes);
		
		MessageCMAD mCMAD2 = new MessageCMAD(decodedBytes2);
		
		JCMAD ff = mCMAD.getJCMAD();
		
		JCMAD ff2 = mCMAD2.getJCMAD();
		boolean d = ff.equals(ff2);
		
		List<JMADILL> lmill = ff.getListill();
		List<JMADILL> lmill1 = ff2.getListill();
		List<JMadRed> lmred = ff.getListred();
		List<JMadRed> lmred1 = ff2.getListred();
		
		if(lmill!=null & lmill1!=null)
			if(!lmill.isEmpty() & !lmill1.isEmpty())
				lmill.equals(lmill1);
		
		if(lmred!=null & lmred1!=null)
			if(!lmred.isEmpty() & !lmred1.isEmpty())
				lmred.equals(lmred1);
		

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
		}else
		elementRead.hashCode();
		
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
		response = target("/CMAD/MAC_ADR_ALL/aaaeffa08807").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);


		response = target("/CMAD/MAC_ADR/ffff0506070").request().get();
		response = target("/CMAD/MAC_ADR/aaaeffa08807").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);
		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date();

		

		response = target("/CMAD/MAC_ADR_BT/ffff0506070").queryParam("datei", formatter.format(today)).queryParam("datef",  formatter.format(today)).request().get();
		response = target("/CMAD/MAC_ADR_BT/aaaeffa08807").request().get();
		x = response.getStatus();
		//assertEquals(200, x);
		res = response.readEntity(new GenericType<String>() {
		});
		log.info(res);

		

	}

}
