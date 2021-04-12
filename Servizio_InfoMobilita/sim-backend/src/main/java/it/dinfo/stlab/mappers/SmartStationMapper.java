package it.dinfo.stlab.mappers;

import it.dinfo.stlab.dao.InfomobilityServiceDao;
import it.dinfo.stlab.dao.MunicipalityDao;
import it.dinfo.stlab.dto.InfomobilityServiceDTOLight;
import it.dinfo.stlab.dto.SmartStationDTO;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.SmartStation;

import javax.inject.Inject;
import java.util.List;

public class SmartStationMapper {

    @Inject
    private InfomobilityServiceDao ispDao;
    @Inject
    private MunicipalityDao municipalityDao;
    @Inject
    private MunicipalityMapper municipalityMapper;

    public SmartStationDTO convert(SmartStation ss){
        if(ss == null)
            return null;

        SmartStationDTO dto = new SmartStationDTO();
        dto.setId(ss.getId());
        dto.setName(ss.getName());
        dto.setEnabled(ss.getEnabled());
        dto.setLat(ss.getLat());
        dto.setLon(ss.getLon());
        dto.setMunicipality(municipalityMapper.convert(ss.getMunicipality()));
        serializeInfomobilityServiceProvider(dto,ss.getInfomobilityServiceProviders());
        dto.setExternalPlaceId(ss.getExternalPlaceId());
        dto.setCmadMacAddress(ss.getCmadMacAddress());

        return dto;
    }

    public void serializeInfomobilityServiceProvider(SmartStationDTO dto, List<InfomobilityServiceProvider> isps){
        if(isps != null){
            for(InfomobilityServiceProvider isp : isps){
                dto.getInfomobilityServices().add(new InfomobilityServiceDTOLight(isp));
            }
        }
    }

    public SmartStation transfer(SmartStationDTO dto, SmartStation ss){
        if(dto == null)
            return null;
        if(ss == null)
            return null;

        ss.setName(dto.getName());
        ss.setEnabled(dto.getEnabled());
        ss.setLat(dto.getLat());
        ss.setLon(dto.getLon());
        ss.setMunicipality(municipalityDao.findByName(dto.getMunicipality().getName()));
        deSerializeInfomobilityServiceProvider(ss,dto.getInfomobilityServices());
        ss.setExternalPlaceId(dto.getExternalPlaceId());
        ss.setCmadMacAddress(dto.getCmadMacAddress());

        return ss;
    }


    public void deSerializeInfomobilityServiceProvider(SmartStation ss, List<InfomobilityServiceDTOLight> ispDTOLights){
        ss.getInfomobilityServiceProviders().clear();

        if(ispDTOLights != null){
            for(InfomobilityServiceDTOLight ispDTOLight : ispDTOLights){
                InfomobilityServiceProvider isp = ispDao.findById(ispDTOLight.getId());
                if(isp != null)
                    ss.getInfomobilityServiceProviders().add(isp);
            }
        }
    }
}
