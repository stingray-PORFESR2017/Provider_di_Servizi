package isti;

import org.glassfish.grizzly.http.server.HttpServer;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {

	
	public static void main(String[] args) {
		Weld weld = new Weld();
		   WeldContainer container = weld.initialize();
		   Application application = container.select(Application.class).get();
		   application.run();
		   
		   isti.rest.StartGrizzly mai = container.select(isti.rest.StartGrizzly.class).get();
		   mai.starthttps();
		   
		   
		 //  weld.shutdown();
		 
		
	}
}
