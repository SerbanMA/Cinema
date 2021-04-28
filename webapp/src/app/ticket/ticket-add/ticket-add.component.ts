import { Component, OnInit } from '@angular/core';
import {Cinema} from "../../cinema/shared/cinema.model";
import {Observable} from "rxjs";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {RoomService} from "../../room/shared/room.service";
import {CinemaService} from "../../cinema/shared/cinema.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map, startWith} from "rxjs/operators";
import {Room} from "../../room/shared/room.model";
import {Ticket} from "../shared/ticket.model";
import {TicketService} from "../shared/ticket.service";
import {formatDate} from "@angular/common";
import {Movie} from "../../movie/shared/movie.model";
import {Client} from "../../client/shared/client.model";
import {MovieService} from "../../movie/shared/movie.service";
import {ClientService} from "../../client/shared/client.service";

@Component({
  selector: 'app-ticket-add',
  templateUrl: './ticket-add.component.html',
  styleUrls: ['./ticket-add.component.css']
})
export class TicketAddComponent implements OnInit {

  movies : Movie[];
  filteredMovies: Observable<Movie[]> = null;

  rooms : Room[];
  filteredRooms: Observable<Room[]> = null;

  clients : Client[];
  filteredClients: Observable<Client[]> = null;

  formGroup = new FormGroup({

    formPrice : new FormControl('', Validators.required),
    formDate : new FormControl('', Validators.required),
    formMovie : new FormControl('', [Validators.required, autocompleteObjectValidator()]),
    formRoom : new FormControl('', [Validators.required, autocompleteObjectValidator()]),
    formClient : new FormControl('', [autocompleteObjectValidator()]),

  });

  constructor(private ticketService : TicketService, private movieService : MovieService, private roomService : RoomService, private clientService : ClientService, private router : Router, private snack : MatSnackBar) {
  }

  ngOnInit(): void {

    // Movie
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies);

    setTimeout(() => {
      this.filteredMovies = this.formGroup.controls['formMovie'].valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : (value ? value.name : "")),
          map(name => name ? this._filterMovie(name) : this.movies.slice())
        );
    }, 1000);

    // Room
    this.roomService.getRooms()
      .subscribe(rooms => this.rooms = rooms);

    setTimeout(() => {
      this.filteredRooms = this.formGroup.controls['formRoom'].valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : (value ? value.name : "")),
          map(name => name ? this._filterRoom(name) : this.rooms.slice())
        );
    }, 1000);

    // Client
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients);

    setTimeout(() => {
      this.filteredClients = this.formGroup.controls['formClient'].valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : (value ? value.name : "")),
          map(name => name ? this._filterClient(name) : this.clients.slice())
        );
    }, 1000);
  }

  saveTicket(): void{
    const ticket : Ticket = <Ticket><unknown>{
      id: null,
      price: this.formGroup.controls['formPrice'].value,
      date: formatDate(this.formGroup.controls['formDate'].value, 'dd/MM/yyyy', 'en-US'),
      time: formatDate(this.formGroup.controls['formDate'].value, 'HH:mm', 'en-US'),
      movie : this.formGroup.controls['formMovie'].value,
      room : this.formGroup.controls['formRoom'].value,
      client : this.formGroup.controls['formClient'].value,
    };

    this.ticketService.saveTicket(ticket).subscribe(() => {
      this.router.navigate(['/tickets']).
        then(() => this.snack.open("Ticket successfully added", "x", {duration: 3000, panelClass: ['snackbar']}))
    });
    this.formGroup.reset();

  }

  // Movie
  private _filterMovie(name: string): Movie[] {
    const filterName = name.toLowerCase();

    return this.movies.filter(movie => movie.name.toLowerCase().indexOf(filterName) === 0);
  }

  displayMovie(movie : Movie): string{
    return movie && movie.name ? movie.name : '';
  }

  // Room
  private _filterRoom(name: string): Room[] {
    const filterName = name.toLowerCase();

    return this.rooms.filter(room => room.name.toLowerCase().indexOf(filterName) === 0);
  }

  displayRoom(room : Room): string{
    return room && room.name ? room.name : '';
  }

  // Client
  private _filterClient(name: string): Client[] {
    const filterName = name.toLowerCase();

    return this.clients.filter(client => (client.firstName.toLowerCase() + ' ' + client.lastName.toLowerCase()).indexOf(filterName) === 0);
  }

  displayClient(client : Client): string{
    return client && client.firstName && client.lastName? client.firstName + ' ' + client.lastName : '';
  }
}

function autocompleteObjectValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (typeof control.value === 'string') {
      return { 'invalidAutocompleteObject': { value: control.value } }
    }
    return null  /* valid option selected */
  }
}
