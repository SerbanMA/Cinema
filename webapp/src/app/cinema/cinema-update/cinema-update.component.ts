import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Cinema} from "../shared/cinema.model";
import {map} from "rxjs/operators";
import {CinemaService} from "../shared/cinema.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-cinema-update',
  templateUrl: './cinema-update.component.html',
  styleUrls: ['./cinema-update.component.css']
})
export class CinemaUpdateComponent implements OnInit {

  id : number;

  formGroup = new FormGroup({
    formName : new FormControl('', [Validators.required]),
    formAddress : new FormControl('', [Validators.required]),
  });

  constructor(private cinemaService : CinemaService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(cinema => {
        this.id = cinema.id;
        this.formGroup.controls['formName'].setValue(cinema.name);
        this.formGroup.controls['formAddress'].setValue(cinema.address);
      });
  }

  updateCinema(): void{
    const cinema : Cinema = <Cinema>{
      id: this.id,
      name: this.formGroup.controls['formName'].value,
      address: this.formGroup.controls['formAddress'].value,
    }
    this.cinemaService.updateCinema(cinema).subscribe(() => {
      this.router.navigate(['/cinemas']).
      then(() => this.snack.open("Cinema successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
