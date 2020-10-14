package it.dinfo.stlab.eai.apiproviders.adapters.train;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import it.dinfo.stlab.eai.apiproviders.adapters.ProviderController;
import it.dinfo.stlab.eai.apiproviders.adapters.train.TrainVehicleAdapter;
import it.dinfo.stlab.eai.apiproviders.adapters.train.dto.TrainsDeparturesResponseDTO;
import it.dinfo.stlab.eai.apiproviders.dto.VehicleDto;
import it.dinfo.stlab.model.SmartStation;
import it.dinfo.stlab.model.providers.InfomobilityServiceProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class TrainController implements ProviderController {

    @Override
    public List<VehicleDto> getAvailableVehicles(
            InfomobilityServiceProvider infomobilityServiceProvider,
            SmartStation smartStation) throws IOException {

        String path = infomobilityServiceProvider.getServiceProviderInfo().getServiceProviderUri();

        Client client = ClientBuilder.newClient();
        String xml = client.target(path)
                           .path("GetDepartures")
                           .queryParam("PlaceId", smartStation.getExternalPlaceId())
                           .request(MediaType.APPLICATION_XML)
                           .get(String.class);

//        System.out.println(xml);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TrainsDeparturesResponseDTO trainsDeparturesResponseDTO = xmlMapper.readValue(xml, TrainsDeparturesResponseDTO.class);
        return new TrainVehicleAdapter().getVehicles(trainsDeparturesResponseDTO.getDepartures());
    }

}
