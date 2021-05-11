import {Component, Inject, OnInit} from '@angular/core';
import {CinemaService} from "../shared/cinema.service";
import {Cinema} from "../shared/cinema.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-cinema-add',
  templateUrl: './cinema-add.component.html',
  styleUrls: ['./cinema-add.component.css']
})
export class CinemaAddComponent implements OnInit {

  formGroup = new FormGroup({
    formName : new FormControl('', [Validators.required]),
    formAddress : new FormControl('', [Validators.required]),
  });

  constructor(private cinemaService : CinemaService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) {}

  saveCinema(): void{
    const cinema : Cinema = <Cinema>{
      id: null,
      name: this.formGroup.controls['formName'].value,
      address: this.formGroup.controls['formAddress'].value,
    }
    this.cinemaService.saveCinema(cinema).subscribe(() => {
      this.router.navigate(['/cinemas']).
      then(() => this.snack.open("Cinema successfully added", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

  ngOnInit(): void {
  }

}
