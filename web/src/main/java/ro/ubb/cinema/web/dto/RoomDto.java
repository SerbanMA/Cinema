package ro.ubb.cinema.web.dto;

import lombok.*;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.domain.entities.Room;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomDto extends BaseDto{
    private Integer floorNumber;
    private String name;
    private Integer numberOfSeats;
    private Cinema cinema;

}
