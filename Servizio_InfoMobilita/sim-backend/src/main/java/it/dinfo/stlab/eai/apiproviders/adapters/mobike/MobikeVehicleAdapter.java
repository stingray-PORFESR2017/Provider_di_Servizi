package it.dinfo.stlab.eai.apiproviders.adapters.mobike;

import it.dinfo.stlab.eai.apiproviders.dto.Location;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.eai.apiproviders.adapters.VehicleAdapter;
import it.dinfo.stlab.eai.apiproviders.adapters.mobike.dto.BikeDto;
import it.dinfo.stlab.model.providers.MobilityType;

public class MobikeVehicleAdapter extends VehicleAdapter<BikeDto> {

	@Override
	public VehicleDto getVehicle(BikeDto providerVehicle) {
		VehicleDto vehicle = new VehicleDto();
		vehicle.setNumber(providerVehicle.getBikeIds());
		vehicle.setDescription(providerVehicle.getBiketype() + " " + providerVehicle.getOperateType());
		vehicle.setBookingLink("");
		vehicle.setHourlyCost(1);
		vehicle.setPowerType("Super ecologico");
		vehicle.setCurrentLocation(new Location("", providerVehicle.getDistY(), providerVehicle.getDistX()));
		vehicle.setMobilityType(MobilityType.BIKE);
		return vehicle;
	}

}
