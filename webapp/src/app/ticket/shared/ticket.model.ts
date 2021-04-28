import {Movie} from "../../movie/shared/movie.model";
import {Room} from "../../room/shared/room.model";
import {Client} from "../../client/shared/client.model";

export class Ticket {
  id : number;
  price : number;
  date : Date;
  time : Date;
  movie : Movie;
  room : Room;
  client : Client;
}
