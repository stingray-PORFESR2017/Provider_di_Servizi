package isti.mqtt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Config {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Config.class);

	
	public String getMoqosquittoUrl() {
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){

		//try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            
            log.trace(prop.getProperty("mosquitto.url"));
            return prop.getProperty("mosquitto.url");
           

        } catch (IOException ex) {
        	log.error(ex,ex);
        }
		return "ssl://stingray.isti.cnr.it:8883";
	}

}
