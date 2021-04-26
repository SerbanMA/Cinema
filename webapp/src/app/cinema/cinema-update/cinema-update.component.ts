import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Cinema} from "../shared/cinema.model";
import {map} from "rxjs/operators";
import {CinemaService} from "../shared/cinema.service";

@Component({
  selector: 'app-cinema-update',
  templateUrl: './cinema-update.component.html',
  styleUrls: ['./cinema-update.component.css']
})
export class CinemaUpdateComponent implements OnInit {

  cinema : Cinema;

  constructor(public cinemaService : CinemaService, public activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(cinema => this.cinema = cinema);
  }

  updateCinema(name:string, address:string): void{
    const cinema : Cinema = <Cinema>{id:this.cinema.id, name, address}
    this.cinemaService.updateCinema(cinema).subscribe(cinema => console.log(cinema));
  }

}
