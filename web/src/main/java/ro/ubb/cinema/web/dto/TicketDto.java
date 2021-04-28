package ro.ubb.cinema.web.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ro.ubb.cinema.core.domain.entities.Client;
import ro.ubb.cinema.core.domain.entities.Movie;
import ro.ubb.cinema.core.domain.entities.Room;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Movie movie;
    private Room room;
    private Client client;
}
