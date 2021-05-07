import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {IDCard} from "../../id-card/shared/id-card.model";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnInit {

  startAt: Date = new Date(2000, 0, 1);
  startView: string = "multi-year";

  id : number;

  formGroup = new FormGroup({
    formFirstName : new FormControl('', [Validators.required]),
    formLastName : new FormControl('', [Validators.required]),
    formEmail : new FormControl('', [Validators.required, Validators.email]),
    formCNP : new FormControl(),
    formSeries : new FormControl(),
    formNumber : new FormControl(),
    formAddress : new FormControl(),
    formDateOfBirth : new FormControl(),
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
        this.formGroup.controls['formCNP'].setValue(client.idCard.cnp);
        this.formGroup.controls['formSeries'].setValue(client.idCard.series);
        this.formGroup.controls['formNumber'].setValue(client.idCard.number);
        this.formGroup.controls['formAddress'].setValue(client.idCard.address);
        this.formGroup.controls['formDateOfBirth'].setValue(new Date(client.idCard.dateOfBirth));
      });
  }

  updateClient(): void{
    const client : Client = <Client>{
      id: this.id,
      firstName: this.formGroup.controls['formFirstName'].value,
      lastName: this.formGroup.controls['formLastName'].value,
      email:  this.formGroup.controls['formEmail'].value,
      idCard: <IDCard><unknown>{
        cnp: this.formGroup.controls['formCNP'].value,
        series: this.formGroup.controls['formSeries'].value,
        number: this.formGroup.controls['formNumber'].value,
        address: this.formGroup.controls['formAddress'].value,
        dateOfBirth: this.formGroup.controls['formDateOfBirth'].value ?
          formatDate(this.formGroup.controls['formDateOfBirth'].value, 'dd/MM/yyyy', 'en-US') :
          null,
      },
    }
    this.clientService.updateClient(client).subscribe(() => {
      this.router.navigate(['/clients']).
      then(() => this.snack.open("Client successfully updated", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
