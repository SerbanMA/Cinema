import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../shared/client.service";
import {Client} from "../shared/client.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {

  formGroup = new FormGroup({
    formFirstName : new FormControl('', [Validators.required]),
    formLastName : new FormControl('', [Validators.required]),
    formEmail : new FormControl('', [Validators.required, Validators.email]),
    formAge : new FormControl('', [Validators.min(0), Validators.max(200)]),
  });

  constructor(private clientService : ClientService, private router : Router, private snack : MatSnackBar) {
  }

  saveClient(): void{
    const client : Client = <Client>{
      id: null,
      firstName: this.formGroup.controls['formFirstName'].value,
      lastName: this.formGroup.controls['formLastName'].value,
      email: this.formGroup.controls['formEmail'].value,
      age: this.formGroup.controls['formAge'].value,
    };

    this.clientService.saveClient(client).subscribe(() => {
      this.router.navigate(['/clients']).
        then(() => this.snack.open("Cinema successfully added", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
    this.formGroup.reset();
  }

  ngOnInit(): void {}

}
