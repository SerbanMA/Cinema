import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../client/shared/client.model";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-id-card',
  templateUrl: './id-card.component.html',
  styleUrls: ['./id-card.component.css']
})
export class IdCardComponent implements OnInit {

  client : Client;

  constructor(private activatedRoute : ActivatedRoute, private router : Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(client => {
        this.client = client;
      });
  }

}
