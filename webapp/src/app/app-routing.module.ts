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

const routes: Routes = [
  {path: 'cinemas', component: CinemaComponent},
  {path: 'cinemas-add', component: CinemaAddComponent},
  {path: 'cinemas-update', component: CinemaUpdateComponent},
  {path: 'cinemas-delete', component: CinemaDeleteComponent},

  {path: 'clients', component: ClientComponent},
  {path: 'clients-add', component: ClientAddComponent},
  {path: 'clients-update', component: ClientUpdateComponent},
  {path: 'clients-delete', component: ClientDeleteComponent},

  {path: 'rooms', component: RoomComponent},
  {path: 'rooms-add', component: RoomAddComponent},
  {path: 'rooms-update', component: RoomUpdateComponent},
  {path: 'rooms-delete', component: RoomDeleteComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation:'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
