package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.Entity;

/**
 * Class for the Movie entity
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long>{
    private String name;
    private Integer duration;
    private String genre;
}
