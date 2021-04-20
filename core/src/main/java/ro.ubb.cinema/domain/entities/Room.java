package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author fivia.
 *
 */

@Entity(name = "Room")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "room")
public class Room extends BaseEntity<Long>{
    @Column(name = "floorNumber")
    private Integer floorNumber;
    @Column(name = "name")
    private String roomName;
    @Column(name = "numberOfSeats")
    private Integer numberOfSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cinemaId")
    private Cinema cinema;

    public Room(Long roomId) {
        super(roomId);
    }
}
