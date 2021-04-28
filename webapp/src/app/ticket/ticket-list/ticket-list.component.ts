import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Ticket} from "../shared/ticket.model";
import {TicketService} from "../shared/ticket.service";

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  selectedTicket : Ticket;

  formSearch = new FormControl();

  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator : MatPaginator;
  @ViewChild(MatSort) sort : MatSort;

  constructor(private ticketService : TicketService) {}

  ngOnInit(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => {
        console.log(tickets);
        this.dataSource.data = tickets;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'movieName':
              // @ts-ignore
              return item.movie ? item.movie.name : null;
            case 'roomName':
              // @ts-ignore
              return item.room ? item.room.name : null;
            case 'clientName':
              // @ts-ignore
              return item.client && item.client.firstName && item.client.lastName ? item.client.firstName + item.client.lastName : null;
            default:
              return item[property];
          }
        };
        this.dataSource.filterPredicate = function(data, filter: string): boolean {
          // @ts-ignore
          return (data.movie.name.toLowerCase()).includes(filter.toLowerCase()) || (data.room.name.toLowerCase()).includes(filter) || ((`${data.client.firstName} ${data.client.lastName}`).toLowerCase()).includes(filter.toLowerCase()) || (data.price.toString()).includes(filter);
        };
      });

    setTimeout(() => {
      this.formSearch.valueChanges.subscribe(search => this.dataSource.filter = search);
    })
  }

  onSelected(ticket : Ticket) : void {
    this.selectedTicket = ticket;
  }

}
