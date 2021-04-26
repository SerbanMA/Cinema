import {Cinema} from "../../cinema/shared/cinema.model";

export class Room {
  id : number;
  name : string;
  floorNumber : number;
  numberOfSeats : number;
  cinema : Cinema;
}
