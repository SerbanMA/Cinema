package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for the Ticket entity
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Ticket extends BaseEntity<Long>{
    private Double price;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="room_id")
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
}
