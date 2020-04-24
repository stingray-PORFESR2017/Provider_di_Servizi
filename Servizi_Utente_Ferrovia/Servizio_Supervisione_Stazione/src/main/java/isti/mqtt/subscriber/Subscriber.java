package isti.mqtt.subscriber;

import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import isti.message.MessageCMAD;
import isti.message.impl.cmad.JCMAD;
import isti.mqtt.Config;
import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;


public class Subscriber implements MqttCallback{
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Subscriber.class);
	
	private final int qos = 1;
    private String topic = "#";
    private IMqttClient publisher;
    
    @Inject 
    private TokenPersistence em;
	
	public Subscriber(String uri) throws MqttException {
		String publisherId = UUID.randomUUID().toString();
		
		Config config = new Config();
		String url = config.getMoqosquittoUrl();
		String mqttUserName = config.getMoqosquittoUser();
		String mqttPassword = config.getMoqosquittoPass();
		publisher = new MqttClient(url,publisherId,new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName(mqttUserName.trim());
		options.setPassword(mqttPassword.trim().toCharArray());
		options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);

        
        publisher.setCallback(this);
        publisher.connect(options);

        publisher.subscribe(this.topic, qos);
    }
	

	public static void main(String[] args) {
		
		try {
			Subscriber s = new Subscriber("");
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*JCMAD ff = new JCMAD("coia", 2);
		try {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(JCMAD.class);
		
		
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal( ff, System.out );

		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*try {
			IMqttClient publisher = new MqttClient("tcp://stingray.isti.cnr.it:8883",publisherId);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			publisher.connect(options);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/

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

	@Override
	public void messageArrived(String topic, MqttMessage message) throws MqttException {
		if(topic.contains("STINGRAY_")){
			log.info(String.format("[%s] %s", topic, new String(message.getPayload())));
			if(message.getPayload().length>60){
				MessageCMAD c = new MessageCMAD(message.getPayload());
				JCMAD ff = c.getJCMAD();
				
				
				EntityTransaction trans = em.getTransaction();
				trans.begin();
				em.persist(ff);
				trans.commit();/**/
				
				try {
					
					JAXBContext jaxbContext = JAXBContext.newInstance(JCMAD.class);
					
					
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
					marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

					marshaller.marshal( ff, System.out );

					
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
        
    }

}
