package it.dinfo.stlab.eai.apiproviders.adapters.train;

import it.dinfo.stlab.eai.apiproviders.adapters.VehicleAdapter;
import it.dinfo.stlab.eai.apiproviders.adapters.train.dto.DepartureDto;
import it.dinfo.stlab.eai.apiproviders.dto.Location;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.providers.MobilityType;
import java.util.ArrayList;
import java.util.List;

public class TrainVehicleAdapter extends VehicleAdapter<DepartureDto> {

	@Override
	public VehicleDto getVehicle(DepartureDto providerVehicle) {
		VehicleDto vehicleDto = new VehicleDto();
		vehicleDto.setCurrentLocation(new Location(providerVehicle.getPlatform(), 0., 0.));

		vehicleDto.setNumber(providerVehicle.getTrainNumber());
		vehicleDto.setDescription(providerVehicle.getCancelled() ? providerVehicle.getMessages().getAdditionalData().getContent() : "");

		vehicleDto.setBookingLink("www.google.com");
		vehicleDto.setHourlyCost(0f);
		vehicleDto.setPowerType("Elettrico");

		vehicleDto.setNominalDepartureTime(providerVehicle.getTime());
		vehicleDto.setDelay(providerVehicle.getDelay());

		vehicleDto.setUpcomingStops(getStops(providerVehicle.getMessages().getAdditionalData().getContent()));
		vehicleDto.setOtherInfos(providerVehicle.getTrainHeaders());

		vehicleDto.setMobilityType(MobilityType.TRAIN);
		return vehicleDto;
	}

	private List<Location> getStops(String content) {
		List<Location> stops = new ArrayList<>();
		try {
			content = content.split(": ")[1];
			String[] split = content.split(" - ");
			for (String s : split) {
				stops.add(new Location(s, 0., 0.));
			}
		} catch (Exception e) {}
		return stops;
	}

}
