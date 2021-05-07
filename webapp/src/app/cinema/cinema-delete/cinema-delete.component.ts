import { Component, OnInit } from '@angular/core';
import {Cinema} from "../shared/cinema.model";
import {CinemaService} from "../shared/cinema.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {Client} from "../../client/shared/client.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-cinema-delete',
  templateUrl: './cinema-delete.component.html',
  styleUrls: ['./cinema-delete.component.css']
})
export class CinemaDeleteComponent implements OnInit {

  cinema : Cinema;

  constructor(public cinemaService : CinemaService, public activatedRoute : ActivatedRoute, public router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(cinema => this.cinema = cinema);
  }

  deleteCinema(): void{
    const cinema : Cinema = <Cinema>{id:this.cinema.id}
    this.cinemaService.deleteCinema(cinema).subscribe(() => {
      this.router.navigate(['/cinemas']).
      then(() => this.snack.open("Cinema successfully deleted", "x", {duration: 3000, panelClass: ['snackbar']}));
    });

  }

}
