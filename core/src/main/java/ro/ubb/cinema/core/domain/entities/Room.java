package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class for the Room entity
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Room extends BaseEntity<Long>{
    private Integer floorNumber;
    private String name;
    private Integer numberOfSeats;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

}
