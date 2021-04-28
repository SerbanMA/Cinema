import {Component, Input, OnInit} from '@angular/core';
import {Movie} from "../shared/movie.model";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  @Input() movie : Movie;

  constructor() { }

  ngOnInit(): void {
  }

}
