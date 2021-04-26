import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

import {Cinema} from "./cinema.model";

@Injectable()
export class CinemaService {
  private url = 'http://localhost:8080/api/cinemas';

  constructor(private httpClient: HttpClient) {
  }

  getCinemas() : Observable<Cinema[]> {
    return this.httpClient
      .get<Array<Cinema>>(this.url);
  }

  getFilteredCinemas(string : string) : Observable<Cinema[]>{
    return this.httpClient
      .post<Array<Cinema>>(this.url+'/filter', string);
  }

  getSortedFilteredCinemasByName(direction : string) : Observable<Cinema[]>{
    return this.httpClient
      .post<Array<Cinema>>(this.url+'/sort', direction);
  }

  saveCinema(cinema : Cinema) : Observable<Cinema>{
    return this.httpClient.post<Cinema>(this.url, cinema);
  }

  updateCinema(cinema : Cinema) : Observable<Cinema>{
    return this.httpClient.put<Cinema>(this.url + '/'+ cinema.id, cinema);
  }

  deleteCinema(cinema : Cinema) : Observable<Cinema>{
    return this.httpClient.delete<Cinema>(this.url + '/'+ cinema.id);
  }

}
