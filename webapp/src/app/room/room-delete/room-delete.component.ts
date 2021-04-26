import { Component, OnInit } from '@angular/core';
import {Client} from "../../client/shared/client.model";
import {ClientService} from "../../client/shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {map} from "rxjs/operators";
import {Room} from "../shared/room.model";
import {RoomService} from "../shared/room.service";

@Component({
  selector: 'app-room-delete',
  templateUrl: './room-delete.component.html',
  styleUrls: ['./room-delete.component.css']
})
export class RoomDeleteComponent implements OnInit {

  room : Room;

  constructor(private roomService : RoomService, private activatedRoute : ActivatedRoute, private router : Router, private snack : MatSnackBar) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
      .pipe(map(() => window.history.state))
      .subscribe(room => this.room = room);
  }

  deleteRoom(): void{
    const room : Room = <Room>{id:this.room.id}
    this.roomService.deleteRoom(room).subscribe(() => {
      this.router.navigate(['/rooms']).
      then(() => this.snack.open("Room successfully deleted", "x", {duration: 3000, panelClass: ['snackbar']}));
    });
  }

}
