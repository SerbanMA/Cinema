import { Component, OnInit } from '@angular/core';
import {Cinema} from "../shared/cinema.model";
import {CinemaService} from "../shared/cinema.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-cinema-delete',
  templateUrl: './cinema-delete.component.html',
  styleUrls: ['./cinema-delete.component.css']
})
export class CinemaDeleteComponent implements OnInit {

  cinema : Cinema;

  constructor(public cinemaService : CinemaService, public activatedRoute : ActivatedRoute, public router : Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(cinema => this.cinema = cinema);
  }

  deleteCinema(): void{
    const cinema : Cinema = <Cinema>{id:this.cinema.id}
    this.cinemaService.deleteCinema(cinema).subscribe(cinema => console.log(cinema));
  }

}
