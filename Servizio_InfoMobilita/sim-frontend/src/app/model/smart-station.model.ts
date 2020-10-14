import {Municipality} from './municipality.model';
import {InfomobilityServiceLite} from './infomobility-service-lite.model';

export interface AmbientalData {
  cmadAnalogInfo: AnalogInfo;
}
interface AnalogInfo {
  tempEst: string;
  tempSuolo: string;
}
export class SmartStation {
  tempEst: string;
  tempSuolo: string;
  cmadMacAddress: string;
  externalPlaceId: string;
  id: string;
  name: string;
  lat: number;
  lon: number;
  enabled: boolean;
  municipality: Municipality;
  infomobilityServices: InfomobilityServiceLite[];

  constructor(
    cmadMacAddress?: string,
    externalPlaceId?: string,
    id?: string,
    name?: string,
    lat?: number,
    lon?: number,
    enabled?: boolean,
    municipality?: Municipality,
    infomobilityServices?: InfomobilityServiceLite[]
    ) {
      this.cmadMacAddress = cmadMacAddress;
      this.externalPlaceId = externalPlaceId;
      this.id = id;
      this.name = name;
      this.lat = lat;
      this.lon = lon;
      this.enabled = enabled;
      this.municipality = municipality;
      this.infomobilityServices = infomobilityServices;
  }
}


