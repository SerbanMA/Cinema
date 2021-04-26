import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnInit {

  id : number;

  formGroup = new FormGroup({
    formFirstName : new FormControl('', [Validators.required]),
    formLastName : new FormControl('', [Validators.required]),
    formEmail : new FormControl('', [Validators.required, Validators.email]),
    formAge : new FormControl('', [Validators.min(0), Validators.max(200)]),
  });

  constructor(private clientService : ClientService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(client => {
        this.id = client.id;
        this.formGroup.controls['formFirstName'].setValue(client.firstName);
        this.formGroup.controls['formLastName'].setValue(client.lastName);
        this.formGroup.controls['formEmail'].setValue(client.email);
        this.formGroup.controls['formAge'].setValue(client.age);
        console.log(client)
      });
  }

  updateCinema(): void{
    const client : Client = <Client>{
      id: this.id,
      firstName: this.formGroup.controls['formFirstName'].value,
      lastName: this.formGroup.controls['formLastName'].value,
      email:  this.formGroup.controls['formEmail'].value,
      age: this.formGroup.controls['formAge'].value
    }
    this.clientService.updateClient(client).subscribe(() => {
      this.router.navigate(['/clients']).
      then(() => this.snack.open("Cinema successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
