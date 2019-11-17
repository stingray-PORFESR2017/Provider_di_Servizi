package isti.mqtt.publisher.thread;

import isti.message.CommandCMAD;
import isti.message.impl.cmad.JCMADCommand;
import isti.mqtt.publisher.Publisher;

public class PubThread implements Runnable {

	private JCMADCommand message;
	
	
	public PubThread(JCMADCommand mess) {
		this.message = mess;
	}
	
	@Override
	public void run() {
		Publisher p = new Publisher();
		CommandCMAD command = new CommandCMAD();
		String key = message.getMAC_ADR();
		p.send(command.getMessage(message),key);
		
		try {
			Thread.sleep(10000);
			p.send(command.getMessageNull(message),key);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
