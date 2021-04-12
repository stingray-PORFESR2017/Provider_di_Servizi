package it.dinfo.stlab.eai.apiproviders.adapters;

import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;

import java.util.ArrayList;
import java.util.List;

public abstract class VehicleAdapter<T extends ProviderVehicle> {

	public abstract VehicleDto getVehicle(T providerVehicle);

	public List<VehicleDto> getVehicles(List<T> providerVehicles) {
		List<VehicleDto> vehicles = new ArrayList<>();
		if (providerVehicles != null) {
			for (T v : providerVehicles) {
				vehicles.add(getVehicle(v));
			}
		}
		return vehicles;
	}

}
