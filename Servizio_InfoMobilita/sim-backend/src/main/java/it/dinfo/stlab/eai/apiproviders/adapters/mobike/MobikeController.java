package it.dinfo.stlab.eai.apiproviders.adapters.mobike;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.dinfo.stlab.eai.apiproviders.adapters.ProviderController;
import it.dinfo.stlab.eai.apiproviders.adapters.mobike.MobikeVehicleAdapter;
import it.dinfo.stlab.eai.apiproviders.adapters.mobike.dto.MobikeResponseDto;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class MobikeController implements ProviderController {

	@Override
	public List<VehicleDto> getAvailableVehicles(
			InfomobilityServiceProvider infomobilityServiceProvider,
			SmartStation smartStation) throws IOException {

		Client client = ClientBuilder.newClient();
		String response =
				client.target(infomobilityServiceProvider.getServiceProviderInfo().getServiceProviderUri())
		                        .queryParam("latitude", smartStation.getLat())
		                        .queryParam("longitude", smartStation.getLon())
		                        .request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

//		System.out.println(response);

		MobikeResponseDto responseDto = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(response, MobikeResponseDto.class);

		return new MobikeVehicleAdapter().getVehicles(responseDto.getBike());
	}

}
