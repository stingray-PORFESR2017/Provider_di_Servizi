export class InfomobilityServiceLite {

  id: string;
  name: string;
  serviceProviderType?: string;

  constructor(id?: string, name?: string, serviceProviderType?: string) {
    this.id = id;
    this.name = name;
    this.serviceProviderType = serviceProviderType;
  }
}
