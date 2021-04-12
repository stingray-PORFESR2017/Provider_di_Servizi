package it.dinfo.stlab.mappers;

import it.dinfo.stlab.dao.SmartStationDao;
import it.dinfo.stlab.dto.InfomobilityServiceDTO;
import it.dinfo.stlab.dto.SmartStationDTOLight;
import it.dinfo.stlab.model.*;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;
import it.dinfo.stlab.model.providers.MobilityType;
import it.dinfo.stlab.model.providers.ServiceProviderInfo;
import it.dinfo.stlab.model.providers.ServiceProviderType;

import javax.inject.Inject;
import java.util.List;

public class InfomobilityServiceMapper {

    @Inject
    SmartStationDao ssDao;

    public InfomobilityServiceDTO convert(InfomobilityServiceProvider isp){
        if(isp == null)
            return null;

        InfomobilityServiceDTO dto = new InfomobilityServiceDTO();
        dto.setId(isp.getId());
        dto.setName(isp.getName());
        dto.setEnabled(isp.getEnabled());
        dto.setServiceProviderType(isp.getServiceProviderInfo().getServiceProviderType().name());
        dto.setServiceProviderTypeContent(isp.getServiceProviderInfo().getServiceProviderUri());
        serializeMobilityType(dto, isp.getMobilityTypes());
        serializeSmartStation(dto, isp.getSmartStations());

        return dto;
    }

    public void serializeMobilityType(InfomobilityServiceDTO dto, List<MobilityType> types){
        if(types != null){
            for(MobilityType type : types){
                dto.getMobilityTypes().add(type.name());
            }
        }
    }

    public void serializeSmartStation(InfomobilityServiceDTO dto, List<SmartStation> smartStations){
        if(smartStations != null){
            for(SmartStation ss : smartStations){
                dto.getSmartStations().add(new SmartStationDTOLight(ss));
            }
        }
    }

    public InfomobilityServiceProvider transfer(InfomobilityServiceDTO dto, InfomobilityServiceProvider isp){
        if(dto == null)
            return null;
        if(isp == null)
            return null;

        isp.setName(dto.getName());
        isp.setEnabled(dto.getEnabled());
        isp.setServiceProviderInfo(new ServiceProviderInfo(ServiceProviderType.valueOf(dto.getServiceProviderType()), dto.getServiceProviderTypeContent()));
        deSerializeMobilityType(isp, dto.getMobilityTypes());
        deSerializeSmartStation(isp, dto.getSmartStations());
        return isp;
    }

    public void deSerializeMobilityType(InfomobilityServiceProvider isp, List<String> mobilityType){
        isp.getMobilityTypes().clear();

        if(mobilityType != null){
            for(String mtStr : mobilityType){
                isp.getMobilityTypes().add(MobilityType.valueOf(mtStr));
            }
        }
    }

    public void deSerializeSmartStation(InfomobilityServiceProvider isp, List<SmartStationDTOLight> ssDTOLights){
        isp.getSmartStations().clear();
        if(ssDTOLights != null){
            for(SmartStationDTOLight ssDTOLight : ssDTOLights){
                SmartStation ss = ssDao.findById(ssDTOLight.getId());
                if(ss != null)
                    isp.getSmartStations().add(ss);
            }
        }
    }

}
