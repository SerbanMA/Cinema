import {Component, OnInit, ViewChild} from '@angular/core';
import {Cinema} from "../../cinema/shared/cinema.model"
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Room} from "../shared/room.model";
import {RoomService} from "../shared/room.service";
import {MatTableDataSource} from "@angular/material/table";
import {FormControl} from "@angular/forms";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  selectedRoom : Room;

  formSearch = new FormControl();

  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator : MatPaginator;
  @ViewChild(MatSort) sort : MatSort;

  constructor(private roomService : RoomService) {}

  ngOnInit(): void {
    this.roomService.getRooms()
      .subscribe(rooms => {
        this.dataSource.data = rooms;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.sortingDataAccessor = (item, property) => {
          switch (property) {
            case 'cinemaName':
              // @ts-ignore
              return item.cinema.name;
            default:
              return item[property];
          }
        };
        this.dataSource.filter = '';
        this.dataSource.filterPredicate = function(data, filter: string): boolean {
          // @ts-ignore
          return (data.name.toLowerCase()).includes(filter.toLowerCase()) || (data.floorNumber.toString()).includes(filter) || (data.cinema.name.toLowerCase()).includes(filter.toLowerCase());
        };
    });

    setTimeout(() => {
      this.formSearch.valueChanges.subscribe(search => {this.dataSource.filter = search; });
    })
  }

  onSelected(room : Room) : void {
    this.selectedRoom = room;
  }
}
