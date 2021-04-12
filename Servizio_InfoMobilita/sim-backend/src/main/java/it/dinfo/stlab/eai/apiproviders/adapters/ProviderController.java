package it.dinfo.stlab.eai.apiproviders.adapters;

import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;

import java.io.IOException;
import java.util.List;

public interface ProviderController {

	List<VehicleDto> getAvailableVehicles(InfomobilityServiceProvider infomobilityServiceProvider,
	                                      SmartStation smartStation) throws IOException;

}
