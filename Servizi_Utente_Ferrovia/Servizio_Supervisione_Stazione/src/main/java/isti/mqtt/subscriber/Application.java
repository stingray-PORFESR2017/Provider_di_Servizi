package isti.mqtt.subscriber;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

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
    //String publisherId = UUID.randomUUID().toString();
    

	Config config = new Config();
	String url = config.getMoqosquittoUrl();
	String mqttUserName = config.getMoqosquittoUser();
	String mqttPassword = config.getMoqosquittoPass();
	String publisherId = MqttAsyncClient.generateClientId();
	publisher = new MqttClient(url,publisherId,new MemoryPersistence());
	MqttConnectOptions options = new MqttConnectOptions();
	options.setUserName(mqttUserName);
	options.setPassword(mqttPassword.toCharArray());
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

@Override
public void messageArrived(String topic, MqttMessage message) throws MqttException {
	if(topic.contains("STINGRAY_")){
		log.info(String.format("[%s] %s", topic, new String(message.getPayload())));
		if(message.getPayload().length>60){
			MessageCMAD c = new MessageCMAD(message.getPayload());
			
			JCMAD ff = c.getJCMAD();
			if(check(ff.getCMAD_HEADER())) {
				
			
			JCMAD elementRead = em.findid(JCMAD.class, ff.getId());
			if(elementRead==null){
			
			EntityTransaction trans = em.getTransaction();
			trans.begin();
			em.persist(ff);
			trans.commit();/**/
			
			checkAI(elementRead);
			
			
			
			}else{
				/* Query query = em.createNativeQuery(
					      "UPDATE Jcmad SET c  WHERE PUBLIC.MAC_ADR = :p Jcmad c");
					  int updateCount = query.setParameter(ff.getMAC_ADR(), 100000).executeUpdate();*/
				if(elementRead.equals(ff)) {
					System.out.println("Ritrasmissione");
					
				}else {
					EntityTransaction t = em.getTransaction();
					t.begin();
					em.update(ff);
					t.commit();
				}
				System.out.println();
			}
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

private void checkAI(JCMAD elementRead) {
	if(true) {
		List<JMadRed> madreds = elementRead.getListred();
		for(JMadRed madred:madreds) {
			int temp1 = madred.getWIRE_ANALOG_INFO().getTemperatura1();

			if(temp1<10) {
				JCMADCommand messae = new JCMADCommand();
				messae.setMAC_ADR_RED(madred.getId().getMAC_ADR());
				CommandType com = new CommandType();
				com.setCommandred(FormatoType.fromValue("ON"));

				messae.setCommand(com);
				PubThread th = new PubThread (messae);
				Thread thread = new Thread(th);

				thread.start();
			}
			
			if(temp1>200) {
				JCMADCommand messae = new JCMADCommand();
				messae.setMAC_ADR_RED(madred.getId().getMAC_ADR());
				CommandType com = new CommandType();
				com.setCommandred(FormatoType.fromValue("OFF"));

				messae.setCommand(com);
				PubThread th = new PubThread (messae);
				Thread thread = new Thread(th);

				thread.start();
			}
		}
	}
	
	
	
}
 
}