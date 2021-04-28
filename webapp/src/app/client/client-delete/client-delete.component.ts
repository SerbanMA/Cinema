import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {

  client : Client;

  constructor(private clientService : ClientService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(client => this.client = client);
  }

  deleteCinema(): void{
    const client : Client = <Client>{id:this.client.id}
    this.clientService.deleteClient(client).subscribe(() => {
      this.router.navigate(['/clients']).
      then(() => this.snack.open("Client successfully deleted", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
