package it.dinfo.stlab.eai.apiproviders.adapters.funnybus;

import it.dinfo.stlab.eai.apiproviders.adapters.ProviderController;
import it.dinfo.stlab.eai.apiproviders.dto.Location;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.providers.MobilityType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunnyBusController implements ProviderController {

	private final double scale = 500.;

	@Override
	public List<VehicleDto> getAvailableVehicles(InfomobilityServiceProvider infomobilityServiceProvider, SmartStation smartStation) throws IOException {
		List<VehicleDto> busList = new ArrayList<>();
		List<Location> stopList = new ArrayList<>();
		int randomStops = (int)(Math.random()*3) + 3;
		for( int i = 0; i < randomStops; i++ ){
			Location stop = new Location("Fermata " + i,
			                             smartStation.getLat() + (Math.random() - 1.)/scale,
			                             smartStation.getLon() + (Math.random() - 1.)/scale);
			stopList.add(stop);
		}
		int randomBus = (int)(Math.random()*10) + 3;
		for( int i = 0; i < randomBus; i++ ){
			VehicleDto bus = new VehicleDto();
			bus.setCurrentLocation(stopList.get(i%stopList.size()));
			bus.setNumber(Integer.toString(i));
			bus.setDescription("This is a funny bus.");
			bus.setBookingLink("https://stlabtest.dinfo.unifi.it/scenedet/");
			bus.setHourlyCost(0f);
			bus.setPowerType("A pedali");
			bus.setNominalDepartureTime(Long.toString(System.currentTimeMillis()
					                                     + (long)(Math.random()*1800000)));
			bus.setDelay(Long.toString((long)(Math.random()*15.)));

			List<Location> upcomingStops = generateUpcomingStops(bus, i);
			bus.setUpcomingStops(upcomingStops);

			bus.setMobilityType(MobilityType.BUS);
			busList.add(bus);
		}
		return busList;
	}

	private List<Location> generateUpcomingStops(VehicleDto bus, int i) {
		int upcomingStopsNumber = (int) (Math.random() * 20) + 5;
		double[] direction = getRandomDirection();
		List<Location> upcomingStops = new ArrayList<>();
		Location firstStop = new Location("Fermata " + i + "_0",
		                                  bus.getCurrentLocation().getLat() + direction[0] * Math.random() / scale,
		                                  bus.getCurrentLocation().getLon() + direction[1] * Math.random() / scale);
		upcomingStops.add(firstStop);
		for (int j = 1; j < upcomingStopsNumber; j++) {
			Location upcomingStop = new Location("Fermata " + i + "_" + j,
			                                     upcomingStops.get(j - 1).getLat() + direction[0] * Math.random() / scale,
			                                     upcomingStops.get(j - 1).getLon() + direction[1] * Math.random() / scale);
			upcomingStops.add(upcomingStop);
		}
		return upcomingStops;
	}

	private double[] getRandomDirection() {
		double[] direction = new double[2];
		int randomDirection = (int) (Math.random() * 4);
		switch (randomDirection){
			case 0:
				direction[0] = 1.;
				direction[1] = 1.;
				break;
			case 1:
				direction[0] = 1.;
				direction[1] = -1.;
				break;
			case 2:
				direction[0] = -1.;
				direction[1] = 1.;
				break;
			case 3:
				direction[0] = -1.;
				direction[1] = -1.;
				break;
		}
		return direction;
	}

}
