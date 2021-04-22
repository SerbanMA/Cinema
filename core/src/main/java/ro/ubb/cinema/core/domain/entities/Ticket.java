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
    @JoinColumn(name = "movieId")
    private Movie movie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="roomId")
    private Room room;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clientId")
    private Client client;
}
