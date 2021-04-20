package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for the Movie entity
 *
 * @author camelia-lazar
 */

@Entity(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "movie")
public class Movie extends BaseEntity<Long>{
    @Column(name="name")
    private String name;
    @Column(name="duration")
    private Integer duration;
    @Column(name="genre")
    private String genre;

    public Movie(Long movieId) {
        super(movieId);
    }
}
