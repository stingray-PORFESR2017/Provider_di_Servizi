package it.dinfo.stlab.eai.apiproviders.adapters.train.controllers;

import it.dinfo.stlab.eai.apiproviders.adapters.train.TrainController;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.providers.ServiceProviderInfo;
import it.dinfo.stlab.model.providers.ServiceProviderType;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class TrainControllerTest {

	@Test
	public void testGetDepartureInfo(){
		TrainController trainController = new TrainController();

		InfomobilityServiceProvider isp3 = new InfomobilityServiceProvider();
		isp3.setServiceProviderInfo(new ServiceProviderInfo(ServiceProviderType.API, "https://stingray" +
				".isti.cnr.it:8443/serviziosupervisionestazione/rfi/FrontEnd/Train"));
		isp3.setControllerClassName(TrainController.class.getName());

		SmartStation sm1 = new SmartStation();
		sm1.setId(UUID.randomUUID().toString());
		sm1.setName("FirenzeSMN SmartStation");
		sm1.setEnabled(true);
		sm1.setExternalPlaceId("1325");//"1325");
		sm1.setCmadMacAddress("fffe000009f8");

		try {
			List<VehicleDto> departureInfo = trainController.getAvailableVehicles(isp3,sm1);
			System.out.println(departureInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
