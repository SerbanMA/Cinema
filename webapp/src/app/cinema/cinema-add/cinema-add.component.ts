import {Component, Inject, OnInit} from '@angular/core';
import {CinemaService} from "../shared/cinema.service";
import {Cinema} from "../shared/cinema.model";


@Component({
  selector: 'app-cinema-add',
  templateUrl: './cinema-add.component.html',
  styleUrls: ['./cinema-add.component.css']
})
export class CinemaAddComponent implements OnInit {

  cinema = new Cinema;

  constructor(private cinemaService : CinemaService) {}

  saveCinema(): void{
    const cinema : Cinema = <Cinema>{name:this.cinema.name, address:this.cinema.address}
    this.cinemaService.saveCinema(cinema).subscribe(cinema => {
      console.log(cinema);
    });
  }

  ngOnInit(): void {
  }

}
