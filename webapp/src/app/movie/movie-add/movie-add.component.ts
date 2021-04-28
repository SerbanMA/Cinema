import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MovieService} from "../shared/movie.service";
import {Movie} from "../shared/movie.model";

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {

  formGroup = new FormGroup({
    formName : new FormControl('', [Validators.required]),
    formGenre : new FormControl(),
    formDuration : new FormControl('', [Validators.required]),
  });

  constructor(private movieService : MovieService, private router : Router, private snack : MatSnackBar) {
  }

  saveMovie(): void{
    const movie : Movie = <Movie>{
      id: null,
      name: this.formGroup.controls['formName'].value,
      genre: this.formGroup.controls['formGenre'].value,
      duration: this.formGroup.controls['formDuration'].value,
    };

    this.movieService.saveMovie(movie).subscribe(() => {
      this.router.navigate(['/movies']).
      then(() => this.snack.open("Movie successfully added", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
    this.formGroup.reset();
  }

  ngOnInit(): void {}

}
