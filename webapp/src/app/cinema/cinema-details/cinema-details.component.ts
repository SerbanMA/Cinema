import {Component, Input, OnInit} from '@angular/core';
import {Cinema} from "../shared/cinema.model";

@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.css']
})
export class CinemaDetailsComponent implements OnInit {

  @Input() cinema : Cinema = new Cinema();

  constructor() { }

  ngOnInit(): void {
  }
}
