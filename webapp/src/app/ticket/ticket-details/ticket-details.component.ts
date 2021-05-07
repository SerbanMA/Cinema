import {Component, Input, OnInit} from '@angular/core';
import {Ticket} from "../shared/ticket.model";

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {

  @Input() ticket : Ticket;

  isOpenMovie : boolean = false;
  isOpenRoom : boolean = false;
  isOpenClient : boolean = false;


  constructor() { }

  ngOnInit(): void {
  }

  openMovie() : void {
    this.isOpenMovie = !this.isOpenMovie;
    this.isOpenRoom = false;
    this.isOpenClient = false;
  }

  openRoom() : void {
    this.isOpenMovie = false;
    this.isOpenRoom = !this.isOpenRoom;
    this.isOpenClient = false;
  }

  openClient() : void {
    this.isOpenMovie = false;
    this.isOpenRoom = false;
    this.isOpenClient = !this.isOpenClient;
  }
}


