import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../shared/client.service";
import {Client} from "../shared/client.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {IDCard} from "../../id-card/shared/id-card.model";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {

  startAt: Date = new Date(2000, 0, 1);
  startView: string = "multi-year";

  formGroup = new FormGroup({
    formFirstName : new FormControl('', [Validators.required]),
    formLastName : new FormControl('', [Validators.required]),
    formEmail : new FormControl('', [Validators.required, Validators.email]),
    formCNP : new FormControl(),
    formSeries : new FormControl(),
    formNumber : new FormControl(),
    formAddress : new FormControl(),
    formDateOfBirth : new FormControl()
  });

  constructor(private clientService : ClientService, private router : Router, private snack : MatSnackBar) {
  }

  saveClient(): void{
    const client : Client = <Client>{
      id: null,
      firstName: this.formGroup.controls['formFirstName'].value,
      lastName: this.formGroup.controls['formLastName'].value,
      email: this.formGroup.controls['formEmail'].value,
      idCard: <IDCard><unknown>{
        cnp: this.formGroup.controls['formCNP'].value,
        series: this.formGroup.controls['formSeries'].value,
        number: this.formGroup.controls['formNumber'].value,
        address: this.formGroup.controls['formAddress'].value,
        dateOfBirth: this.formGroup.controls['formDateOfBirth'].value ?
          formatDate(this.formGroup.controls['formDateOfBirth'].value, 'dd/MM/yyyy', 'en-US') :
          null,
      },
    };

    this.clientService.saveClient(client).subscribe(() => {
      this.router.navigate(['/clients']).
        then(() => this.snack.open("Cinema successfully added", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
    this.formGroup.reset();
  }

  ngOnInit(): void {}

}
