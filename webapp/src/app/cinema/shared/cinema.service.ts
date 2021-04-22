import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

import {map} from "rxjs/operators"
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

}
