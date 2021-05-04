package isti.mqtt.publisher.thread;

import java.util.Base64;

import isti.message.CommandCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.mqtt.publisher.Publisher;

public class PubThread implements Runnable {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PubThread.class);

	private JCMADCommand message;
	
	
	public PubThread(JCMADCommand mess) {
		this.message = mess;
	}
	
	@Override
	public void run() {
		Publisher p = new Publisher();
		CommandCMAD command = new CommandCMAD();
		String key = message.getMAC_ADR();
		byte[] messagebyte = command.getMessage(message);
		p.send(messagebyte,key);
		log.trace(Base64.getEncoder().encodeToString(messagebyte));
		/*try {
			//Thread.sleep(1000);
			//messagebyte = command.getMessageNull(message);
			//p.send(messagebyte,key);
			//log.trace(Base64.getEncoder().encodeToString(messagebyte));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

}
