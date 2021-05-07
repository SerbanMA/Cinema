package ro.ubb.cinema.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomDto extends BaseDto{
    private Integer floorNumber;
    private String name;
    private Integer numberOfSeats;
    private CinemaDto cinema;

}
