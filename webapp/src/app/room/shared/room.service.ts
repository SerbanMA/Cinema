import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

import {map} from "rxjs/operators"
import {Room} from "./room.model";
import {Client} from "../../client/shared/client.model";

@Injectable()
export class RoomService {
  private url = 'http://localhost:8080/api/rooms';

  constructor(private httpClient: HttpClient) {
  }

  getRooms() : Observable<Room[]> {
    return this.httpClient
      .get<Array<Room>>(this.url);
  }

  saveRoom(room : Room) : Observable<Room>{
    return this.httpClient.post<Room>(this.url, room);
  }

  updateRoom(room : Room) : Observable<Room>{
    return this.httpClient.put<Room>(this.url + '/'+ room.id, room);
  }

  deleteRoom(room : Room) : Observable<Room>{
    return this.httpClient.delete<Room>(this.url + '/'+ room.id);
  }

}
