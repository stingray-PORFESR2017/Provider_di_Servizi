import { Location } from './location.model';

export class Vehicle {
    bookingLink?: string;
    currentLocation?: Location;
    delay?: string;
    description?: string;
    hourlyCost?: number;
    mobilityType: string;
    nominalDepartureTime: string;
    number?: string;
    otherInfos?: any;
    powerType?: string;
    upcomingStops?: Location[];
}
