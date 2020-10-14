package it.dinfo.stlab.controllers;

import it.dinfo.stlab.dao.MunicipalityDao;
import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dto.SmartStationDTO;
import it.dinfo.stlab.dto.SmartStationDTOLight;
import it.dinfo.stlab.eai.apiproviders.dto.Location;
import it.dinfo.stlab.mappers.SmartStationMapper;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.Municipality;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.user.UserAccount;


import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class SmartStationController {

    @Inject private SmartStationDao smartStationDao;
    @Inject private SmartStationMapper smartStationMapper;
    @Inject private MunicipalityDao municipalityDao;

    public List<SmartStationDTO> getAll(){
        List<SmartStationDTO> smartStationDTOs = new ArrayList<>();
        List<SmartStation> smartStations = smartStationDao.findAll();
        smartStations.sort(new SmartStationComparator());
        for (SmartStation smartStation : smartStations) {
            SmartStationDTO dto = smartStationMapper.convert(smartStation);
            smartStationDTOs.add(dto);
        }
        return smartStationDTOs;
    }

    public List<SmartStationDTO> getAllAuthorizedIspForAdmin(UserAccount user){
        List<SmartStationDTO> smartStationDTOs = new ArrayList<>();
        List<SmartStation> smartStations = smartStationDao.findAllAuthorizedSSForAdmin(user);
        smartStations.sort(new SmartStationComparator());
        for (SmartStation smartStation : smartStations) {
            SmartStationDTO dto = smartStationMapper.convert(smartStation);
            smartStationDTOs.add(dto);
        }
        return smartStationDTOs;
    }

    public SmartStationDTO getById(String uuid){
        SmartStation smartStation = smartStationDao.findById(uuid);
        SmartStationDTO dto = smartStationMapper.convert(smartStation);
        return dto;
    }

    public Location getLocationById(String uuid){
        SmartStation smartStation = smartStationDao.findById(uuid);
        Location dto = new Location("", smartStation.getLat(), smartStation.getLon());
        return dto;
    }

    public String create(SmartStationDTO dtoReceived){
        SmartStation smartStation = smartStationMapper.transfer(dtoReceived,new SmartStation());
        smartStation.setId(UUID.randomUUID().toString());
        smartStationDao.save(smartStation);
        return smartStation.getId();
    }

    public void update(String uuid, SmartStationDTO dtoReceived){
        SmartStation byId = smartStationDao.findById(uuid);
        smartStationMapper.transfer(dtoReceived,byId);
        smartStationDao.update(byId);
    }

    public byte[] getPicture(String uuid) {
        SmartStation byId = smartStationDao.findById(uuid);
        return byId.getPicture();
    }

    public void updatePicture(String uuid, InputStream inputStream) throws IOException {
        byte[] image = new byte[inputStream.available()];
        inputStream.read(image);
        SmartStation byId = smartStationDao.findById(uuid);
        byId.setPicture(image);
        smartStationDao.update(byId);
    }

    public void delete(String uuid){
        smartStationDao.delete(uuid);
    }

    public SmartStationDTO enableDisable(String uuid, Boolean enable){
        SmartStation ss = smartStationDao.findById(uuid);
        ss.setEnabled(enable);
        smartStationDao.update(ss);
        return smartStationMapper.convert(ss);
    }

    public List<SmartStationDTOLight> getAllSsAssociableForOneIspForOneAdmin(UserAccount user, InfomobilityServiceProvider isp){
        List<Municipality> municipalities = municipalityDao.findAllMunicipalityForAdminAuth(user, isp);
        List<SmartStationDTOLight> ssDTOLights = new ArrayList<>();
        List<SmartStation> smartStations = new ArrayList<>();
        for(Municipality m: municipalities){
            smartStations.addAll(smartStationDao.findAllSSForMunicipality(m));
        }
        smartStations.sort(new SmartStationComparator());
        for(SmartStation ss: smartStations){
            ssDTOLights.add(new SmartStationDTOLight(ss));
        }
        return ssDTOLights;
    }

    public List<SmartStationDTO> getAllEnabledSsByMunicipality(String municipalityId){
        List<SmartStationDTO> smartStationDTOs = new ArrayList<>();
        Municipality m = municipalityDao.findById(municipalityId);
        List<SmartStation> smartStations = smartStationDao.findAllEnabledSSForMunicipality(m);
        smartStations.sort(new SmartStationComparator());
        for (SmartStation smartStation : smartStations) {
            SmartStationDTO dto = smartStationMapper.convert(smartStation);
            smartStationDTOs.add(dto);
        }
        return smartStationDTOs;
    }

    private class SmartStationComparator implements Comparator<SmartStation> {
        @Override
        public int compare(SmartStation o1, SmartStation o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

}
