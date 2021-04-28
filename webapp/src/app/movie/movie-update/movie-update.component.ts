import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map} from "rxjs/operators";
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";

@Component({
  selector: 'app-movie-update',
  templateUrl: './movie-update.component.html',
  styleUrls: ['./movie-update.component.css']
})
export class MovieUpdateComponent implements OnInit {

  id : number;

  formGroup = new FormGroup({
    formName : new FormControl('', [Validators.required]),
    formGenre : new FormControl(),
    formDuration : new FormControl('', [Validators.required]),
  });

  constructor(private movieService : MovieService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(movie => {
        this.id = movie.id;
        this.formGroup.controls['formName'].setValue(movie.name);
        this.formGroup.controls['formGenre'].setValue(movie.genre);
        this.formGroup.controls['formDuration'].setValue(movie.duration);
      });
  }

  updateCinema(): void{
    const movie : Movie = <Movie>{
      id: this.id,
      name: this.formGroup.controls['formName'].value,
      genre: this.formGroup.controls['formGenre'].value,
      duration: this.formGroup.controls['formDuration'].value,
    };
    this.movieService.updateMovie(movie).subscribe(() => {
      this.router.navigate(['/movies']).
      then(() => this.snack.open("Movie successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
