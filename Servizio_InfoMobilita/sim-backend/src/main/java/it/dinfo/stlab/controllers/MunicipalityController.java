package it.dinfo.stlab.controllers;

import it.dinfo.stlab.dao.MunicipalityDao;
import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dto.MunicipalityDTO;
import it.dinfo.stlab.mappers.MunicipalityMapper;
import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.user.UserAccount;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class MunicipalityController {

	@Inject
	private MunicipalityDao municipalityDao;
	@Inject
	private MunicipalityMapper municipalityMapper;
	@Inject
	private SmartStationDao smartStationDao;

	public List<MunicipalityDTO> getAll() {
		List<MunicipalityDTO> municipalityDTOS = new ArrayList<>();
		List<Municipality> municipalities = municipalityDao.findAll();
		municipalities.sort(new MunicipalityComparator());
		for (Municipality municipality : municipalities) {
			MunicipalityDTO dto = municipalityMapper.convert(municipality);
			municipalityDTOS.add(dto);
		}
		return municipalityDTOS;
	}

	public List<MunicipalityDTO> getAllNotEmpty() {
		List<MunicipalityDTO> municipalityDTOS = new ArrayList<>();
		List<Municipality> municipalities = municipalityDao.findNotEmpty();
		municipalities.sort(new MunicipalityComparator());
		for (Municipality municipality : municipalities) {
			MunicipalityDTO dto = municipalityMapper.convert(municipality);
			municipalityDTOS.add(dto);
		}
		return municipalityDTOS;
	}

	public List<MunicipalityDTO> getAllForAdmin(UserAccount user) {
		List<MunicipalityDTO> municipalityDTOS = new ArrayList<>();
		List<Municipality> municipalities = municipalityDao.findAllForAdmin(user);
		municipalities.sort(new MunicipalityComparator());
		for (Municipality municipality : municipalities) {
			MunicipalityDTO dto = municipalityMapper.convert(municipality);
			municipalityDTOS.add(dto);
		}
		return municipalityDTOS;
	}

	public MunicipalityDTO getById(String uuid) {
		Municipality municipality = municipalityDao.findById(uuid);
		MunicipalityDTO dto = municipalityMapper.convert(municipality);
		return dto;
	}

	public String create(MunicipalityDTO dtoReceived) {
		Municipality municipality = municipalityMapper.transfer(dtoReceived, new Municipality());
		municipality.setId(UUID.randomUUID().toString());
		municipalityDao.save(municipality);
		return municipality.getId();
	}

	public void update(String uuid, MunicipalityDTO dtoReceived) {
		Municipality byId = municipalityDao.findById(uuid);
		municipalityMapper.transfer(dtoReceived, byId);
		municipalityDao.update(byId);
	}

	public byte[] getPicture(String uuid) {
		Municipality byId = municipalityDao.findById(uuid);
		return byId.getPicture();
	}

	public void updatePicture(String uuid, InputStream inputStream) throws IOException {
		byte[] image = new byte[inputStream.available()];
		inputStream.read(image);
		Municipality byId = municipalityDao.findById(uuid);
		byId.setPicture(image);
		municipalityDao.update(byId);
	}

	public boolean delete(String uuid) {
		List<SmartStation> allSSForMunicipality =
				smartStationDao.findAllSSForMunicipality(municipalityDao.findById(uuid));
		if (allSSForMunicipality == null || allSSForMunicipality.size() == 0) {
			municipalityDao.delete(uuid);
			return true;
		}
		return false;
	}

	private class MunicipalityComparator implements Comparator<Municipality> {
		@Override
		public int compare(Municipality o1, Municipality o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

}
