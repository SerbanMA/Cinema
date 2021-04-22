package ro.ubb.cinema.core.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Class for the Cinema entity
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Cinema extends BaseEntity<Long> {
    private String name;
    private String address;
}
