import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map} from "rxjs/operators";
import {Ticket} from "../shared/ticket.model";
import {TicketService} from "../shared/ticket.service";

@Component({
  selector: 'app-ticket-delete',
  templateUrl: './ticket-delete.component.html',
  styleUrls: ['./ticket-delete.component.css']
})
export class TicketDeleteComponent implements OnInit {

  ticket : Ticket;

  constructor(private ticketService : TicketService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(ticket => this.ticket = ticket);
  }

  deleteTicket(): void{
    const ticket : Ticket = <Ticket>{id:this.ticket.id}
    this.ticketService.deleteTicket(ticket).subscribe(() => {
      this.router.navigate(['/tickets']).
      then(() => this.snack.open("Ticket successfully deleted", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
