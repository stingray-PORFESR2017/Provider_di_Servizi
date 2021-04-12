import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'latitude'
})
export class LatitudePipe implements PipeTransform {

  transform(lat: number): string {
    if (lat ) {
      const latString = lat >= 0 ? `N ${lat.toString().slice(0, 7)} ` : `S ${(-1 * lat).toString().slice(0, 7)} `;
      return latString;
    }
    return null;
  }

}
