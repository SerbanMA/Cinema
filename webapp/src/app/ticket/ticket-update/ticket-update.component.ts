import { Component, OnInit } from '@angular/core';
import {Movie} from "../../movie/shared/movie.model";
import {Observable} from "rxjs";
import {Room} from "../../room/shared/room.model";
import {Client} from "../../client/shared/client.model";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {TicketService} from "../shared/ticket.service";
import {MovieService} from "../../movie/shared/movie.service";
import {RoomService} from "../../room/shared/room.service";
import {ClientService} from "../../client/shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map, startWith} from "rxjs/operators";
import {formatDate} from "@angular/common";
import {Ticket} from "../shared/ticket.model";

@Component({
  selector: 'app-ticket-update',
  templateUrl: './ticket-update.component.html',
  styleUrls: ['./ticket-update.component.css']
})
export class TicketUpdateComponent implements OnInit {

  id : number;

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

  constructor(private ticketService : TicketService, private movieService : MovieService, private roomService : RoomService, private clientService : ClientService, private router : Router, private activatedRoute : ActivatedRoute, private snack : MatSnackBar) {
  }

  ngOnInit(): void {

    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(ticket => {
        this.id = ticket.id;
        this.formGroup.controls['formPrice'].setValue(ticket.price);
        this.formGroup.controls['formDate'].setValue(new Date(ticket.date));
        this.formGroup.controls['formMovie'].setValue(ticket.movie);
        this.formGroup.controls['formRoom'].setValue(ticket.room);
        this.formGroup.controls['formClient'].setValue(ticket.client);
      });

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

  updateTicket(): void{

    let client = this.formGroup.controls['formClient'].value
    client.idCard.dateOfBirth = formatDate(new Date(client.idCard.dateOfBirth), 'dd/MM/yyyy', 'en-US')


    const ticket : Ticket = <Ticket><unknown>{
      id: this.id,
      price: this.formGroup.controls['formPrice'].value,
      date: formatDate(this.formGroup.controls['formDate'].value, 'dd/MM/yyyy', 'en-US'),
      time: formatDate(this.formGroup.controls['formDate'].value, 'HH:mm', 'en-US'),
      movie : this.formGroup.controls['formMovie'].value,
      room : this.formGroup.controls['formRoom'].value,
      client : this.formGroup.controls['formClient'].value,
    };

    this.ticketService.updateTicket(ticket).subscribe(() => {
      this.router.navigate(['/tickets']).
      then(() => this.snack.open("Ticket successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}))
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
