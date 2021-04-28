import {Component, Input, OnInit} from '@angular/core';
import {Room} from "../shared/room.model";
import {Cinema} from "../../cinema/shared/cinema.model";

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {

  @Input() room : Room;

  constructor() { }

  ngOnInit(): void {
  }

}
