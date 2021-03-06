package isti.mqtt.subscriber;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import isti.ai.StingrayAI;
import isti.message.MessageCMAD;
import isti.message.impl.cmad.CommandType;
import isti.message.impl.cmad.FormatoType;
import isti.message.impl.cmad.JCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.message.impl.red.JMadRed;
import isti.mqtt.Config;
import isti.mqtt.publisher.thread.PubThread;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Singleton
public class Application implements MqttCallback {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Application.class);

	private final int qos = 1;
	private final int maxMsgsInflight = 1;
	private String topic = "#";
	private IMqttClient publisher;
	private ExecutorService pool;  
	private WeldContainer weld;

	@Inject
	private TokenPersistence em;

	public void run() {
		try {
			log.debug("Subscriber initialized");
			pool = Executors.newFixedThreadPool(2); 
			
			String publisherId = "Stingray_cloud_sub_"+UUID.randomUUID().toString();

			Config config = new Config();
			String url = config.getMoqosquittoUrl();
			String mqttUserName = config.getMoqosquittoUser();
			String mqttPassword = config.getMoqosquittoPass();
			publisher = new MqttClient(url, publisherId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);
			options.setUserName(mqttUserName);
			options.setAutomaticReconnect(true);
			options.setMaxInflight(maxMsgsInflight);

			options.setPassword(mqttPassword.toCharArray());
			options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);

			publisher.setCallback(this);
			publisher.connect(options);
			log.info("Subcriber Conected...");
			publisher.subscribe(this.topic, qos);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		log.info("Connection lost because: " + cause);

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken cause) {
		// TODO Auto-generated method stub
		log.info("Connection lost because: " + cause);

	}

	private boolean check(String s) {
		if (s == null) {// checks if the String is null
			return false;
		}
		int len = s.length();
		for (int i = 0; i < len; i++) {
			// checks whether the character is not a letter
			// if it is not a letter ,it will return false
			if ((Character.isLetter(s.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws MqttException {
		try {
			if (topic.contains("STINGRAY_")) {
				//log.error(topic);
				log.info(String.format("[%s] %s", topic, new String(message.getPayload())));
				log.info(String.format("[%s] %s", topic, Base64.getEncoder().encodeToString(message.getPayload())));


				if(message.getPayload().length>60){ 
					MessageCMAD c = new MessageCMAD(message.getPayload());

					JCMAD ff = c.getJCMAD(); 

					if(check(ff.getCMAD_HEADER())) {

						/*	TypedQuery<JCMAD>	r = 	em.createNamedQuery("JCMAD.findAllMac", JCMAD.class);
					r.setParameter(1, ff.getId().getMAC_ADR());
					List<JCMAD> temp = r.getResultList();

					TypedQuery<JCMAD>	r2 = 	em.createNamedQuery("JCMAD.findAllorder", JCMAD.class);

					List<JCMAD> temp2 = r2.getResultList();

					TypedQuery<String>	r22 = 	em.createNamedQueryS("JCMAD.finddistcmad", String.class);

					List<String> temp22 = r22.getResultList();*/



						JCMAD elementRead = em.findid(JCMAD.class, ff.getId());
						if(elementRead==null){

							EntityTransaction trans = em.getTransaction(); 
							trans.begin(); 
							em.persist(ff);
							trans.commit();

							checkAI(ff);



						}else{/* Query query = em.createNativeQuery(
			  "UPDATE Jcmad SET c  WHERE PUBLIC.MAC_ADR = :p Jcmad c"); int updateCount =
			  query.setParameter(ff.getMAC_ADR(), 100000).executeUpdate();*/

							if(elementRead.equals(ff)) { 
								log.info("Ritrasmissione");

							}else { 
								EntityTransaction t = em.getTransaction(); t.begin(); em.update(ff);
								t.commit(); 
								} 
							System.out.println(); 
						} 
					} 
					/*try {

						JAXBContext jaxbContext = JAXBContext.newInstance(JCMAD.class);


						Marshaller marshaller = jaxbContext.createMarshaller();
						marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //
						//NOI18N
						marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
								Boolean.TRUE);

						marshaller.marshal( ff, System.out );


					} catch (JAXBException e) { // TODO Auto-generated catch block
						e.printStackTrace(); } */
				}

			}
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace(); } 

	}



	private void checkAI(JCMAD elementRead) {
		//Weld weld = new Weld();
	//	   WeldContainer container = weld.initialize();
		 StingrayAI f = weld.select(StingrayAI.class).get();//.get();
		//StingrayAI stai = new StingrayAI(elementRead);
		 f.setCMAD(elementRead);
		 pool.execute(f);
		

	}

	public void setWeld(WeldContainer container) {
		weld = container;
		
	}

}