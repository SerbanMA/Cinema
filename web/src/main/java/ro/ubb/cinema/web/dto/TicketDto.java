package ro.ubb.cinema.web.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonSerialize
public class TicketDto extends BaseDto{
    private Double price;
    private String date;
    private String time;
    private MovieDto movie;
    private RoomDto room;
    private ClientDto client;
}
