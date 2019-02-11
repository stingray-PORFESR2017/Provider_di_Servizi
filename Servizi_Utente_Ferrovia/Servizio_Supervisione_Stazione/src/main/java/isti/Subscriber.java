package isti;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber implements MqttCallback{
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Subscriber.class);
	
	private final int qos = 1;
    private String topic = "#";
    private IMqttClient publisher;
	
	public Subscriber(String uri) throws MqttException {
		String publisherId = UUID.randomUUID().toString();
		publisher = new MqttClient("ssl://stingray.isti.cnr.it:8883",publisherId,new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		
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
		}
        
    }

}
