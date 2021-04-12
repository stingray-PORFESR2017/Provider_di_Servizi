import {SmartStationLite} from './smart-station-lite.model';

export class InfomobilityService {

  id: string;
  name: string;
  enabled: boolean;
  mobilityTypes: string[];
  serviceProviderType: string;
  serviceProviderTypeContent: string;
  smartStations: SmartStationLite[];

  constructor(
    id?: string,
    name?: string,
    enabled?: boolean,
    mobilityTypes?: string[],
    serviceProviderType?: string,
    serviceProviderTypeContent?: string,
    smartStations?: SmartStationLite[]) {
      this.id = id;
      this.name = name;
      this.mobilityTypes = mobilityTypes;
      this.serviceProviderType = serviceProviderType;
      this.serviceProviderTypeContent = serviceProviderTypeContent;
      this.enabled = enabled;
      this.smartStations = smartStations;
  }
}
