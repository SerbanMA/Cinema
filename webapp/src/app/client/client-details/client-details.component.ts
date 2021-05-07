import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {

  @Input() client : Client;
  @Input() fromTicket : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
