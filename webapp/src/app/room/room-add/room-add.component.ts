import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../cinema/shared/cinema.service";
import {Cinema} from "../../cinema/shared/cinema.model";
import {RoomService} from "../shared/room.service";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {Observable, of} from "rxjs";
import {map, retry, startWith} from "rxjs/operators";
import {Room} from "../shared/room.model";
import {group} from "@angular/animations";
import {ClientService} from "../../client/shared/client.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.css']
})
export class RoomAddComponent implements OnInit {

  cinemas : Cinema[];
  filteredCinemas: Observable<Cinema[]> = null;

  formGroup = new FormGroup({

    formName : new FormControl('', [Validators.required]),
    formFloorNumber : new FormControl(),
    formNumberOfSeats : new FormControl(),
    formCinema : new FormControl('', [Validators.required, autocompleteObjectValidator()]),

  });

  constructor(private roomService : RoomService, private cinemaService : CinemaService, private router : Router, private snack : MatSnackBar) {
  }

  ngOnInit(): void {
    this.cinemaService.getCinemas()
      .subscribe(cinemas => this.cinemas = cinemas);


    setTimeout(() => {
      this.filteredCinemas = this.formGroup.controls['formCinema'].valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : (value ? value.name : "")),
          map(name => name ? this._filter(name) : this.cinemas.slice())
        );
    }, 1000);
  }

  saveRoom(): void{
    const room : Room = <Room><unknown>{
      id: null,
      name: this.formGroup.controls['formName'].value,
      floorNumber: this.formGroup.controls['formFloorNumber'].value,
      numberOfSeats: this.formGroup.controls['formNumberOfSeats'].value,
      cinema: this.formGroup.controls['formCinema'].value,
    };

    this.roomService.saveRoom(room).subscribe(() => {
      this.router.navigate(['/rooms']).
      then(() => this.snack.open("Room successfully added", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
    this.formGroup.reset();

  }

  private _filter(name: string): Cinema[] {
    const filterName = name.toLowerCase();

    return this.cinemas.filter(cinema => cinema.name.toLowerCase().indexOf(filterName) === 0);
  }

  displayCinema(cinema : Cinema): string{
    return cinema && cinema.name ? cinema.name : '';
  }
}

function autocompleteObjectValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (typeof control.value === 'string') {
      return { 'invalidAutocompleteObject': { value: control.value } }
    }
    return null  /* valid option selected */
  }
}
