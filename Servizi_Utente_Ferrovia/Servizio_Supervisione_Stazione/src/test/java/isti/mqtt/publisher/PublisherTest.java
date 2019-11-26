package isti.mqtt.publisher;

import static org.junit.Assert.*;

import java.util.Base64;

import org.junit.Test;

import isti.message.CommandCMAD;

public class PublisherTest {

	@Test
	public void test() {
		Publisher p = new Publisher();
		
		String encodedString = "Q6qu/6CIBwABWABDTUFEIERJIFRFU1QgICAgICAgIAA1DADQ3QYACAAAAg0AjQAvALAAKgFAnJABQJyIE4gTiBOIE3AXcBdg6mDqYOpwF3AXAAAAAAAAAAAAAAAAAAAAAAAAAGxmUgAAACIAAAEAAABNQURSRUQgREkgVEVTVCAgICAgIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABMAAAAAAA0AgAAAE1BRElMTCBESSBURVNUICAgICAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

		
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		
		CommandCMAD c = new CommandCMAD();
		
		
		//p.send(decodedBytes, "test");
	}

}
