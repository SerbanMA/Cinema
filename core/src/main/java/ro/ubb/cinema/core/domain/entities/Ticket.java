package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for the Client entity
 *
 * @author maerean-serban
 */

@Entity(name = "Ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "ticket")
public class Ticket extends BaseEntity<Long>{
    @Column(name="price")
    private Double price;
    @Column(name="date")
    private LocalDate date;
    @Column(name="time")
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
