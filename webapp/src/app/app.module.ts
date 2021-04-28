import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CinemaComponent } from './cinema/cinema.component';
import { CinemaListComponent } from './cinema/cinema-list/cinema-list.component';
import { CinemaService } from "./cinema/shared/cinema.service";
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from "@angular/material/card";
import { CinemaDetailsComponent } from './cinema/cinema-details/cinema-details.component';
import { CinemaAddComponent } from './cinema/cinema-add/cinema-add.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {MatInputModule} from "@angular/material/input";
import { RoomComponent } from './room/room.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { RoomAddComponent } from './room/room-add/room-add.component';
import {RoomService} from "./room/shared/room.service";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import { CinemaUpdateComponent } from './cinema/cinema-update/cinema-update.component';
import { CinemaDeleteComponent } from './cinema/cinema-delete/cinema-delete.component';
import { ClientComponent } from './client/client.component';
import { ClientListComponent } from './client/client-list/client-list.component';
import { ClientAddComponent } from './client/client-add/client-add.component';
import { ClientDeleteComponent } from './client/client-delete/client-delete.component';
import { ClientUpdateComponent } from './client/client-update/client-update.component';
import { ClientDetailsComponent } from './client/client-details/client-details.component';
import {ClientService} from "./client/shared/client.service";
import {MatSortModule} from "@angular/material/sort";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { RoomDeleteComponent } from './room/room-delete/room-delete.component';
import { RoomUpdateComponent } from './room/room-update/room-update.component';
import { MovieComponent } from './movie/movie.component';
import { MovieDetailsComponent } from './movie/movie-details/movie-details.component';
import { MovieListComponent } from './movie/movie-list/movie-list.component';
import { MovieAddComponent } from './movie/movie-add/movie-add.component';
import { MovieUpdateComponent } from './movie/movie-update/movie-update.component';
import { MovieDeleteComponent } from './movie/movie-delete/movie-delete.component';
import {MovieService} from "./movie/shared/movie.service";
import { TicketComponent } from './ticket/ticket.component';
import { TicketListComponent } from './ticket/ticket-list/ticket-list.component';
import { TicketDetailsComponent } from './ticket/ticket-details/ticket-details.component';
import { TicketAddComponent } from './ticket/ticket-add/ticket-add.component';
import { TicketDeleteComponent } from './ticket/ticket-delete/ticket-delete.component';
import { TicketUpdateComponent } from './ticket/ticket-update/ticket-update.component';
import {TicketService} from "./ticket/shared/ticket.service";
import {MatNativeDateModule} from "@angular/material/core";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {NgxMatDatetimePickerModule, NgxMatNativeDateModule} from "@angular-material-components/datetime-picker";


@NgModule({
  declarations: [
    AppComponent,
    CinemaComponent,
    CinemaListComponent,
    CinemaDetailsComponent,
    CinemaAddComponent,
    CinemaUpdateComponent,
    CinemaDeleteComponent,
    RoomComponent,
    RoomListComponent,
    RoomDetailsComponent,
    RoomAddComponent,
    ClientComponent,
    ClientListComponent,
    ClientAddComponent,
    ClientDeleteComponent,
    ClientUpdateComponent,
    ClientDetailsComponent,
    RoomDeleteComponent,
    RoomUpdateComponent,
    MovieComponent,
    MovieDetailsComponent,
    MovieListComponent,
    MovieAddComponent,
    MovieUpdateComponent,
    MovieDeleteComponent,
    TicketComponent,
    TicketListComponent,
    TicketDetailsComponent,
    TicketAddComponent,
    TicketDeleteComponent,
    TicketUpdateComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatExpansionModule,
    MatCardModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSortModule,
    MatSnackBarModule,
    MatNativeDateModule,
    MatDatepickerModule,
    NgxMatNativeDateModule,
    NgxMatDatetimePickerModule,
  ],
  providers: [CinemaService, ClientService, MovieService, RoomService, TicketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
