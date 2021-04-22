package ro.ubb.cinema.core.domain.entities;
import lombok.*;

import javax.persistence.Entity;

/**
 * Class for the Client entity
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long>{
	private String firstName;
    private String lastName;
	private String email;
	private Integer age;
}
