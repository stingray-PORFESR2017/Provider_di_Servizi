export class Municipality {
    id: string;
    name: string;
    lat: number;
    lon: number;
    constructor(id?: string, name?: string, lat?: number, lon?: number ) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}
