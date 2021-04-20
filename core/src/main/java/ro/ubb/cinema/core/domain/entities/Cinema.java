package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for the Cinema entity
 *
 * @author razvan-kokovics
 */

@Entity(name = "Cinema")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "cinema")
public class Cinema extends BaseEntity<Long> {
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;

    public Cinema(Long cinemaId) {
        super(cinemaId);
    }
}
