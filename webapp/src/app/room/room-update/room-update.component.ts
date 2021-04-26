import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map, startWith} from "rxjs/operators";
import {Room} from "../shared/room.model";
import {RoomService} from "../shared/room.service";
import {Cinema} from "../../cinema/shared/cinema.model";
import {Observable} from "rxjs";
import {CinemaService} from "../../cinema/shared/cinema.service";

@Component({
  selector: 'app-room-update',
  templateUrl: './room-update.component.html',
  styleUrls: ['./room-update.component.css']
})
export class RoomUpdateComponent implements OnInit {

  cinemas : Cinema[];
  filteredCinemas: Observable<Cinema[]> = null;

  id : number;

  formGroup = new FormGroup({

    formName : new FormControl('', [Validators.required]),
    formFloorNumber : new FormControl(),
    formNumberOfSeats : new FormControl(),
    formCinema : new FormControl('', [Validators.required, autocompleteObjectValidator()]),

  });

  constructor(private roomService : RoomService, private cinemaService : CinemaService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(room => {
        this.id = room.id;
        this.formGroup.controls['formName'].setValue(room.name);
        this.formGroup.controls['formFloorNumber'].setValue(room.floorNumber);
        this.formGroup.controls['formNumberOfSeats'].setValue(room.numberOfSeats);
        this.formGroup.controls['formCinema'].setValue(room.cinema);
      });

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

  updateRoom(): void{
    const room : Room = <Room><unknown>{
      id: this.id,
      name: this.formGroup.controls['formName'].value,
      floorNumber: this.formGroup.controls['formFloorNumber'].value,
      numberOfSeats: this.formGroup.controls['formNumberOfSeats'].value,
      cinema: this.formGroup.controls['formCinema'].value,
    };

    this.roomService.updateRoom(room).subscribe(() => {
      console.log(room);
      this.router.navigate(['/rooms']).
      then(() => this.snack.open("Room successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}));
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
