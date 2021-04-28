import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map} from "rxjs/operators";
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";

@Component({
  selector: 'app-movie-delete',
  templateUrl: './movie-delete.component.html',
  styleUrls: ['./movie-delete.component.css']
})
export class MovieDeleteComponent implements OnInit {

  movie : Movie;

  constructor(private movieService: MovieService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(movie => this.movie = movie);
  }

  deleteMovie(): void{
    const movie : Movie = <Movie>{id:this.movie.id}
    this.movieService.deleteMovie(movie).subscribe(() => {
      this.router.navigate(['/movies']).
      then(() => this.snack.open("Movie successfully deleted", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
