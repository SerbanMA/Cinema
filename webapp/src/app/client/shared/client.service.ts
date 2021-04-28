import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "./client.model";


@Injectable()
export class ClientService {
  private url = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients() : Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.url);
  }

  saveClient(client : Client) : Observable<Client>{
    return this.httpClient.post<Client>(this.url, client);
  }

  updateClient(client : Client) : Observable<Client>{
    return this.httpClient.put<Client>(`${this.url}/${client.id}`, client);
  }

  deleteClient(client : Client) : Observable<Client>{
    return this.httpClient.delete<Client>(`${this.url}/${client.id}`);
  }
}
