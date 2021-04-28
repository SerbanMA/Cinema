import {Component, Input, OnInit} from '@angular/core';
import {Ticket} from "../shared/ticket.model";

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {

  @Input() ticket : Ticket;

  constructor() { }

  ngOnInit(): void {
  }

}
