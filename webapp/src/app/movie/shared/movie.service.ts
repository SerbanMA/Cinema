import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie.model";

@Injectable()
export class MovieService {
  private url = 'http://localhost:8080/api/movies';

  constructor(private httpClient: HttpClient) {
  }

  getMovies() : Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.url);
  }

  saveMovie(movie : Movie) : Observable<Movie>{
    return this.httpClient.post<Movie>(this.url, movie);
  }

  updateMovie(movie : Movie) : Observable<Movie>{
    return this.httpClient.put<Movie>(`${this.url}/${movie.id}`, movie);
  }

  deleteMovie(movie : Movie) : Observable<Movie>{
    return this.httpClient.delete<Movie>(`${this.url}/${movie.id}`);
  }
}
