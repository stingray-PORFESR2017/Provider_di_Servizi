import {InfomobilityServiceLite} from './infomobility-service-lite.model';
import {Municipality} from './municipality.model';

export class AdminAuthorization {

  id: string;
  municipalityName: string;
  expire_date: any;
  isp: InfomobilityServiceLite;
  userAccount: any;
  municipality: Municipality;

  constructor(id?: string, expire_date?: Date, userAccount?: any, isp?: InfomobilityServiceLite, municipality?: Municipality) {
    this.id = id;
    this.expire_date = expire_date;
    this.userAccount = userAccount;
    this.isp = isp;
    this.municipality = municipality;
  }
}
