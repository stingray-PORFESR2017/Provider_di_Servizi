package it.dinfo.stlab.controllers;

import it.dinfo.stlab.dao.InfomobilityServiceDao;
import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dto.InfomobilityServiceDTO;
import it.dinfo.stlab.dto.InfomobilityServiceDTOLight;
import it.dinfo.stlab.eai.apiproviders.adapters.ProviderController;
import it.dinfo.stlab.eai.apiproviders.adapters.train.TrainController;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.mappers.InfomobilityServiceMapper;
import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.user.UserAccount;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class InfomobilityServiceController {

	@Inject
	private InfomobilityServiceDao infomobilityServiceDao;
	@Inject
	private SmartStationDao smartStationDao;
	@Inject
	private InfomobilityServiceMapper infomobilityServiceMapper;
	@Inject
	private TrainController trainController;

	public List<InfomobilityServiceDTO> getAll() {
		List<InfomobilityServiceDTO> infomobilityServiceDTOs = new ArrayList<>();
		List<InfomobilityServiceProvider> infomobilityServiceProviders = infomobilityServiceDao.findAll();
		infomobilityServiceProviders.sort(new InfomobilityServiceComparator());
		for (InfomobilityServiceProvider infomobilityServiceProvider : infomobilityServiceProviders) {
			InfomobilityServiceDTO dto = infomobilityServiceMapper.convert(infomobilityServiceProvider);
			infomobilityServiceDTOs.add(dto);
		}
		return infomobilityServiceDTOs;
	}

	public List<InfomobilityServiceDTO> getAllAuthorizedIspForAdmin(UserAccount user) {
		List<InfomobilityServiceDTO> infomobilityServiceDTOs = new ArrayList<>();
		List<InfomobilityServiceProvider> isps = infomobilityServiceDao.findAllAuthorizedIspForAdmin(user);
		isps.sort(new InfomobilityServiceComparator());
		for (InfomobilityServiceProvider isp : isps) {
			InfomobilityServiceDTO dto = infomobilityServiceMapper.convert(isp);
			infomobilityServiceDTOs.add(dto);
		}
		return infomobilityServiceDTOs;
	}

	public InfomobilityServiceDTO getById(String uuid) {
		InfomobilityServiceProvider infomobilityServiceProvider = infomobilityServiceDao.findById(uuid);
		InfomobilityServiceDTO dto = infomobilityServiceMapper.convert(infomobilityServiceProvider);
		return dto;
	}

	public String create(InfomobilityServiceDTO dtoReceived) {
		InfomobilityServiceProvider isp = infomobilityServiceMapper.transfer(dtoReceived,
		                                                                     new InfomobilityServiceProvider());
		isp.setId(UUID.randomUUID().toString());
		infomobilityServiceDao.save(isp);
		return isp.getId();
	}

	public void update(String uuid, InfomobilityServiceDTO dtoReceived) {
		InfomobilityServiceProvider byId = infomobilityServiceDao.findById(uuid);
		infomobilityServiceMapper.transfer(dtoReceived, byId);
		infomobilityServiceDao.update(byId);
	}

	public byte[] getPicture(String uuid) {
		InfomobilityServiceProvider byId = infomobilityServiceDao.findById(uuid);
		return byId.getPicture();
	}

	public void updatePicture(String uuid, InputStream inputStream) throws IOException {
		byte[] image = new byte[inputStream.available()];
		inputStream.read(image);
		InfomobilityServiceProvider byId = infomobilityServiceDao.findById(uuid);
		byId.setPicture(image);
		infomobilityServiceDao.update(byId);
	}

	public void delete(String uuid) {
		infomobilityServiceDao.delete(uuid);
	}

	public InfomobilityServiceDTO enableDisable(String uuid, Boolean enable) {
		InfomobilityServiceProvider isp = infomobilityServiceDao.findById(uuid);
		isp.setEnabled(enable);
		infomobilityServiceDao.update(isp);
		return infomobilityServiceMapper.convert(isp);
	}

	public List<InfomobilityServiceDTOLight> getAllIspAssociableForOneSsForOneAdmin(
			UserAccount user,
			SmartStation ss) {
		Municipality municipality = ss.getMunicipality();
		List<InfomobilityServiceProvider> isps =
				infomobilityServiceDao.findAllIspForMunicipalityAuthorizationForAdmin(user, municipality);
		isps.sort(new InfomobilityServiceComparator());
		List<InfomobilityServiceDTOLight> ispDTOLights = new ArrayList<>();
		for (InfomobilityServiceProvider isp : isps) {
			ispDTOLights.add(new InfomobilityServiceDTOLight(isp));
		}
		return ispDTOLights;
	}

	public List<VehicleDto> getAvailableVehicles(SmartStation ss, InfomobilityServiceProvider isp)
			throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			InstantiationException, IOException {
		Class providerClass = Class.forName(isp.getControllerClassName());
		Constructor<ProviderController> ctor = providerClass.getConstructor();
		ProviderController providerController = ctor.newInstance();
		return providerController.getAvailableVehicles(isp, ss);
	}

	public List<InfomobilityServiceDTO> getAllIspInOneSS(String ssId) {
		SmartStation ss = smartStationDao.findById(ssId);
		List<InfomobilityServiceProvider> isps = ss.getInfomobilityServiceProviders();
		isps.sort(new InfomobilityServiceComparator());
		List<InfomobilityServiceDTO> infomobilityServiceDTOs = new ArrayList<>();
		for (InfomobilityServiceProvider isp : isps) {
			if (isp.getEnabled()) {
				InfomobilityServiceDTO dto = infomobilityServiceMapper.convert(isp);
				infomobilityServiceDTOs.add(dto);
			}
		}
		return infomobilityServiceDTOs;
	}

	private class InfomobilityServiceComparator implements Comparator<InfomobilityServiceProvider> {
		@Override
		public int compare(InfomobilityServiceProvider o1, InfomobilityServiceProvider o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

}
