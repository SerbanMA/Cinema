import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "./ticket.model";



@Injectable()
export class TicketService {
  private url = 'http://localhost:8080/api/tickets';

  constructor(private httpClient: HttpClient) {
  }

  getTickets() : Observable<Ticket[]> {
    return this.httpClient
      .get<Array<Ticket>>(this.url);
  }

  saveTicket(ticket : Ticket) : Observable<Ticket>{
    return this.httpClient.post<Ticket>(this.url, ticket);
  }

  updateTicket(ticket : Ticket) : Observable<Ticket>{
    return this.httpClient.put<Ticket>(this.url + '/'+ ticket.id, ticket);
  }

  deleteTicket(ticket : Ticket) : Observable<Ticket>{
    return this.httpClient.delete<Ticket>(this.url + '/'+ ticket.id);
  }

}
