package isti.mqtt.publisher;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.UUID;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import isti.message.impl.cmad.JCMAD;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import isti.message.MessageCMAD;




public class Publisher {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Publisher.class);

	
	public void send(byte[] message, String key)  {
		
		try {

	    String messageString = "Hello World from Java!";
	    
	   
	    log.trace("== START PUBLISHER ==");
	    
	    String serverUrl = "ssl://stingray.isti.cnr.it:8883";
		//String caFilePath = "/Users/spagnolo/github/stingray_ssl/combo2.pem";
		String clientCrtFilePath = "/your_ssl/client.pem";
		String clientKeyFilePath = "/your_ssl/client.key";
		String mqttUserName = "guest";
		String mqttPassword = "123123";
 
		MqttClient mqttClient;
		try {
			String publisherId = UUID.randomUUID().toString();

			mqttClient = new MqttClient(serverUrl, publisherId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			//options.setUserName(mqttUserName);
			//options.setPassword(mqttPassword.toCharArray());
			
			options.setConnectionTimeout(60);
			options.setKeepAliveInterval(60);
			options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);

			
			SSLSocketFactory socketFactory = getSocketFactory(//caFilePath,
					clientCrtFilePath, clientKeyFilePath, "");
			options.setSocketFactory(socketFactory);

			log.trace("starting connect the server...");
			mqttClient.connect(options);
			log.trace("connected!");
			

			MqttMessage messagemqtt = new MqttMessage(message);

			messagemqtt.setQos(0);     //sets qos level 1
			messagemqtt.setRetained(true); //sets retained message 

			MqttTopic topic2 = mqttClient.getTopic("STINGRAY_SUBS"+key);

			  
			    topic2.publish(messagemqtt);   
			    
			
			//Thread.sleep(1000);
			mqttClient.disconnect();
			log.trace("disconnected!");


		} catch (MqttException e) {
			log.error(e);
		}
		}catch (Exception e) {
			log.error(e);
		}
	}

	

	private static SSLSocketFactory getSocketFactory(//final String caCrtFile,
			final String crtFile, final String keyFile, final String password)
			throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// load CA certificate
		X509Certificate caCert = null;
		
		InputStream is2 = Publisher.class.getClassLoader().getResourceAsStream("combo2.pem");

		//FileInputStream fis = new FileInputStream(caCrtFile);
		BufferedInputStream bis = new BufferedInputStream(is2);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		while (bis.available() > 0) {
			caCert = (X509Certificate) cf.generateCertificate(bis);
			// log.trace(caCert.toString());
		}
/*
		// load client certificate
		bis = new BufferedInputStream(new FileInputStream(crtFile));
		X509Certificate cert = null;
		while (bis.available() > 0) {
			cert = (X509Certificate) cf.generateCertificate(bis);
			// log.trace(caCert.toString());
		}

		// load client private key
		PEMParser pemParser = new PEMParser(new FileReader(keyFile));
		Object object = pemParser.readObject();
		PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder()
				.build(password.toCharArray());
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
				.setProvider("BC");
		KeyPair key;
		if (object instanceof PEMEncryptedKeyPair) {
			log.trace("Encrypted key - we will use provided password");
			key = converter.getKeyPair(((PEMEncryptedKeyPair) object)
					.decryptKeyPair(decProv));
		} else {
			log.trace("Unencrypted key - no password needed");
			key = converter.getKeyPair((PEMKeyPair) object);
		}
		pemParser.close();
*/
		// CA certificate is used to authenticate server
		KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
		caKs.load(null, null);
		caKs.setCertificateEntry("ca-certificate", caCert);
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
		tmf.init(caKs);

		// client key and certificates are sent to server so it can authenticate
		// us
		/*KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(null, null);
		ks.setCertificateEntry("certificate", cert);
		ks.setKeyEntry("private-key", key.getPrivate(), password.toCharArray(),
				new java.security.cert.Certificate[] { cert });*/
		//KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
		//		.getDefaultAlgorithm());
		//kmf.init(ks, password.toCharArray());

		// finally, create SSL socket factory
		SSLContext context = SSLContext.getInstance("TLSv1.2");
		//context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		context.init(null, tmf.getTrustManagers(), null);

		return context.getSocketFactory();
	}



	public void send(JCMAD message, String key) {
		byte [] messageByte = message.toByte();
		send(messageByte,key);
	}

}
