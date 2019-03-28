package isti;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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

import isti.serviziosupervisionestazione.apirest.persistence.TokenPersistence;

@Singleton
public class Application implements MqttCallback{
 
private static org.apache.log4j.Logger log  = org.apache.log4j.Logger.getLogger(Application.class);
 
private final int qos = 1;
private String topic = "#";
private IMqttClient publisher;

@Inject 
private TokenPersistence em;
 
public void run() {
	try {
    log.debug("application initialized");
    String publisherId = UUID.randomUUID().toString();
	publisher = new MqttClient("ssl://stingray.isti.cnr.it:8883",publisherId,new MemoryPersistence());
	MqttConnectOptions options = new MqttConnectOptions();
	
	options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);

    
    publisher.setCallback(this);
    publisher.connect(options);

    publisher.subscribe(this.topic, qos);
	}catch (Exception e) {
		System.out.print(e);
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

@Override
public void messageArrived(String topic, MqttMessage message) throws MqttException {
	if(topic.contains("STINGRAY_")){
		log.info(String.format("[%s] %s", topic, new String(message.getPayload())));
		if(message.getPayload().length>60){
			MessageCMAD c = new MessageCMAD(message.getPayload());
			JCMAD ff = c.getJCMAD();
			
			JCMAD elementRead = em.findid(JCMAD.class, ff.getId());
			if(elementRead==null){
			
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.persist(ff);
			trans.commit();/**/
			}else{
				/* Query query = em.createNativeQuery(
					      "UPDATE Jcmad SET c  WHERE PUBLIC.MAC_ADR = :p Jcmad c");
					  int updateCount = query.setParameter(ff.getMAC_ADR(), 100000).executeUpdate();*/
				if(elementRead.equals(ff)) {
					System.out.println("Ritrasmissione");
				}
				System.out.println();
			}
			
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