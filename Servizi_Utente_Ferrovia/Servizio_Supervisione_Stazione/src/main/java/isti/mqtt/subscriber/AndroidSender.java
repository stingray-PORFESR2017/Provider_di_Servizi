package isti.mqtt.subscriber;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;



public class AndroidSender {
	
	
	
	public static void sendToToken(String title, String msg)  {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
    	
    	//AAAA0RsheDo:APA91bHsMFA-PUXukeJrJY-aTBiYch4MuRvPfxpAOicC8oQfOcb2ttrkMhJrP1Hr3OxJcT9kKzdd3c86xkK3VtfZW_0XlWtheJSLZhc5gTrxrU4OUHVgdrjx8CQ694x-GQDvFxkyQE0Q
    	try {
    		InputStream is = Subscriber.class.getClassLoader().getResourceAsStream("stringray-2371d-firebase-adminsdk-elk4e-fdbfc512f5.json");
    		

    				FirebaseOptions options = new FirebaseOptions.Builder()
    				  .setCredentials(GoogleCredentials.fromStream(is))
    				  .build();

    				FirebaseApp app;
    		if(FirebaseApp.getApps().size()>0) {
    			app = FirebaseApp.getInstance();
    		}else
    			 app = FirebaseApp.initializeApp(options);
    	    
    	    
    	    
    		
       // String registrationToken = "epnR__qJvcQ:APA91bGqeoJ_YvYuBr5q7KpIgbAig51sc1uunjqo7kpOTHt0GX6k8NQHwS3z1sIX5gMDimxfCAQsK2e3bmSUqwU-e8FoQSDjY1oim1pmOqHYDokaaBkadtn2ExkNnCfQEVnd287tUYzC";
    		String registrationToken = "AAAA0RsheDo:APA91bHsMFA-PUXukeJrJY-aTBiYch4MuRvPfxpAOicC8oQfOcb2ttrkMhJrP1Hr3OxJcT9kKzdd3c86xkK3VtfZW_0XlWtheJSLZhc5gTrxrU4OUHVgdrjx8CQ694x-GQDvFxkyQE0Q";


        
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                		.setTtl(60 * 1000) // 1 minute in milliseconds
                  //  .setTtl(300 * 1000) // 5 minutes in milliseconds
                    .setPriority(AndroidConfig.Priority.NORMAL)
                    .setNotification(AndroidNotification.builder()
                        .setTitle(title)
                        .setBody(msg)
                      /*  .setIcon("stock_ticker_update")
                        .setColor("#f45342")*/
                        .build())
                    .build())
                //.setToken(registrationToken)
                .setTopic("Stingray")
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance(app).send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        // [END send_to_token]
        
    	}catch (FirebaseMessagingException e) {
			System.out.print(e.getLocalizedMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }

}
