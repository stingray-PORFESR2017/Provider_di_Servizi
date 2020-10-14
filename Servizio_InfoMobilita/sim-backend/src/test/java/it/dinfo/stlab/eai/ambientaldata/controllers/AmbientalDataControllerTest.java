package it.dinfo.stlab.eai.ambientaldata.controllers;

import it.dinfo.stlab.eai.ambientaldata.dto.AmbientalDataDTO;
import org.junit.Test;

import java.io.IOException;

public class AmbientalDataControllerTest {

	@Test
	public void testGetAmbientalDataInfo() {
		AmbientalDataController ambientalDataController = new AmbientalDataController();
		try {
			AmbientalDataDTO ambientalDataInfo = ambientalDataController.getAmbientalDataInfo("abff100009e8");
			System.out.println( ambientalDataInfo.getCmadAnalogInfo().getTempEst() );
			System.out.println( ambientalDataInfo.getCmadAnalogInfo().getTempSuolo() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
