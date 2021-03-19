package isti.serviziosupervisionestazione.apirest.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.dinfo.stlab.eai.rfi.*;
import it.dinfo.stlab.eai.viaggiatreno.DeparturingTrain;
import it.dinfo.stlab.eai.viaggiatreno.JourneyDto;
import it.dinfo.stlab.eai.viaggiatreno.JourneyStopDto;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Api(value = "RFI")
@Path("/rfi/")
public class SufRFIEndpoint {

	@PermitAll
	@GET
	public Response test() {
		return Response.ok().entity("It works!").build();
	}

	@Operation(summary = "Service Dati Treni", description = "Service demo without authentication. ",
			responses = { @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "401", description = "Unauthorized") })
	@ApiOperation(value = "Service Dati Treni", 
			
	  notes = "Service demo without authentication."
	)
	@PermitAll
	@GET
	@Path("/FrontEnd/{key:.*}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getDeparturingTrains(@QueryParam("PlaceId") String placeId) {

		String apiVT = "http://www.viaggiatreno.it/viaggiatrenonew/resteasy/viaggiatreno/partenze/";

		final String now = Instant.now().atZone(ZoneId.of("Europe/Rome"))
		                          .format(DateTimeFormatter.ofPattern("EEE MMM dd YYYY HH:mm:ss", Locale.ENGLISH));
		String xml = null;
		try {
		Client client = ClientBuilder.newClient();
		final List<DeparturingTrain> departuringTrains = client.target(apiVT)
		                                                       .path(placeId + "/" + now)
		                                                       .request(MediaType.APPLICATION_JSON)
		                                                       .get(new GenericType<List<DeparturingTrain>>() {
		                                                       });

		final TrainsDeparturesResponseDTO trainsDeparturesResponseDTO = convertVTtoRFI(departuringTrains, placeId);

		XmlMapper xmlMapper = new XmlMapper();
		
		
			xml = xmlMapper.writeValueAsString(trainsDeparturesResponseDTO);
		} catch (Exception e) {
			Logger.getAnonymousLogger().info("Conversione in XML fallita.");
			Logger.getAnonymousLogger().info(e.getLocalizedMessage());
		}

		return Response.ok().entity(xml).build();
	}

	private TrainsDeparturesResponseDTO convertVTtoRFI(List<DeparturingTrain> departuringTrains, String placeId) {
		Client client = ClientBuilder.newClient();
		String apiVT = "http://www.viaggiatreno.it/viaggiatrenonew/resteasy/viaggiatreno/andamentoTreno/";
		TrainsDeparturesResponseDTO trainsDeparturesResponseDTO = new TrainsDeparturesResponseDTO();
		List<DepartureDto> departures = new ArrayList<>();
		{
			for (DeparturingTrain depVT : departuringTrains) {
				DepartureDto depRFI = new DepartureDto();

				TrainHeaderDto trainHeaderDto = new TrainHeaderDto();
				trainHeaderDto.setId(Integer.toString(depVT.getNumeroTreno()));
				trainHeaderDto.setBrandCustomer("Trenitalia");
				trainHeaderDto.setBrandCategory(depVT.getCategoria());
				depRFI.setTrainHeaders(trainHeaderDto);

				depRFI.setCancelled(Boolean.FALSE);
				depRFI.setPlatform(depVT.getBinarioEffettivoPartenzaDescrizione());
				depRFI.setTime(Instant.ofEpochMilli(depVT.getOrarioPartenza()).atZone(ZoneId.of("Europe/Rome")).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
				depRFI.setDelay(Long.toString(depVT.getRitardo()));
				 JourneyDto journeyDto = null;
try {
				 journeyDto = client.target(apiVT)
				                                    .path(placeId + "/" + depVT.getNumeroTreno())
				                                    .request(MediaType.APPLICATION_JSON)
				                                    .get(new GenericType<JourneyDto>() {});

				if (journeyDto == null)
					continue;
}catch (Exception e) {
	continue;
}
				MessagesDto messagesDto = new MessagesDto();
				AdditionalDataDto additionalDataDto = new AdditionalDataDto();
				String content = "Ferma a: ";
				for (JourneyStopDto stopDto : journeyDto.getFermate()) {
					LocalDateTime hour =
							Instant.ofEpochMilli(stopDto.getProgrammata()).atZone(ZoneId.of("Europe/Rome")).toLocalDateTime();

					String stazioneEOrario = stopDto.getStazione().toUpperCase() +
							" " +
							hour.format(DateTimeFormatter.ofPattern("(HH:mm)", Locale.ENGLISH));

					content = content + stazioneEOrario + " - ";
				}
				content = content.substring(0, content.length()-3);
				additionalDataDto.setContent(content);
				messagesDto.setAdditionalData(additionalDataDto);
				depRFI.setMessages(messagesDto);

				departures.add(depRFI);
			}
		}
		trainsDeparturesResponseDTO.setDepartures(departures);
		return trainsDeparturesResponseDTO;
	}

}
