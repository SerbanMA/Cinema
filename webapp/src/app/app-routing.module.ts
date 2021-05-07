import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CinemaComponent} from "./cinema/cinema.component";
import {CinemaAddComponent} from "./cinema/cinema-add/cinema-add.component";
import {CinemaUpdateComponent} from "./cinema/cinema-update/cinema-update.component";
import {CinemaDeleteComponent} from "./cinema/cinema-delete/cinema-delete.component";

import {ClientComponent} from "./client/client.component";
import {ClientAddComponent} from "./client/client-add/client-add.component";
import {ClientUpdateComponent} from "./client/client-update/client-update.component";
import {ClientDeleteComponent} from "./client/client-delete/client-delete.component";

import {RoomComponent} from "./room/room.component";
import {RoomAddComponent} from "./room/room-add/room-add.component";
import {RoomUpdateComponent} from "./room/room-update/room-update.component";
import {RoomDeleteComponent} from "./room/room-delete/room-delete.component";

import {MovieComponent} from "./movie/movie.component";
import {MovieAddComponent} from "./movie/movie-add/movie-add.component";
import {MovieUpdateComponent} from "./movie/movie-update/movie-update.component";
import {MovieDeleteComponent} from "./movie/movie-delete/movie-delete.component";

import {TicketComponent} from "./ticket/ticket.component";
import {TicketAddComponent} from "./ticket/ticket-add/ticket-add.component";
import {TicketUpdateComponent} from "./ticket/ticket-update/ticket-update.component";
import {TicketDeleteComponent} from "./ticket/ticket-delete/ticket-delete.component";
import {IdCardComponent} from "./id-card/id-card.component";
import {CinemaDetailsComponent} from "./cinema/cinema-details/cinema-details.component";

const routes: Routes = [
  {path: 'cinemas', component: CinemaComponent},
  {path: 'cinemas-add', component: CinemaAddComponent},
  {path: 'cinemas-update', component: CinemaUpdateComponent},
  {path: 'cinemas-delete', component: CinemaDeleteComponent},

  {path: 'clients', component: ClientComponent},
  {path: 'clients-add', component: ClientAddComponent},
  {path: 'clients-update', component: ClientUpdateComponent},
  {path: 'clients-delete', component: ClientDeleteComponent},

  {path: 'movies', component: MovieComponent},
  {path: 'movies-add', component: MovieAddComponent},
  {path: 'movies-update', component: MovieUpdateComponent},
  {path: 'movies-delete', component: MovieDeleteComponent},

  {path: 'rooms', component: RoomComponent},
  {path: 'rooms-add', component: RoomAddComponent},
  {path: 'rooms-update', component: RoomUpdateComponent},
  {path: 'rooms-delete', component: RoomDeleteComponent},

  {path: 'tickets', component: TicketComponent},
  {path: 'tickets-add', component: TicketAddComponent},
  {path: 'tickets-update', component: TicketUpdateComponent},
  {path: 'tickets-delete', component: TicketDeleteComponent},

  {path: 'id-card', component: IdCardComponent},
  {path: 'cinema-details', component: CinemaDetailsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation:'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
