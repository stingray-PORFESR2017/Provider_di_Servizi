import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'longitude'
})
export class LongitudePipe implements PipeTransform {

  transform(lon: number): string {
    if (lon) {
      return lon >= 0 ? `E ${lon.toString().slice(0, 7)}` : `O ${(-1 * lon).toString().slice(0, 7)}`;
    }
    return null;
  }

}
