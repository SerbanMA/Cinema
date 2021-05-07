package ro.ubb.cinema.core.domain.entities;
import lombok.*;

import javax.persistence.*;

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
	@Embedded
	@AttributeOverrides({
			@AttributeOverride( name = "cnp", column = @Column(name = "cnp")),
			@AttributeOverride( name = "series", column = @Column(name = "ID_Series")),
			@AttributeOverride( name = "number", column = @Column(name = "ID_Number")),
			@AttributeOverride( name = "address", column = @Column(name = "address")),
			@AttributeOverride( name = "dateOfBirth", column = @Column(name = "date_of_birth"))
	})

	private IDCard idCard;
}
