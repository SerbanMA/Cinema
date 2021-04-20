package ro.ubb.cinema.core.domain.entities;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for the Client entity
 *
 * @author fiamardar
 */

@Entity(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "client")
public class Client extends BaseEntity<Long>{
    @Column(name="firstName")
	private String clientFirstName;
    @Column(name="lastName")
    private String clientLastName;
    @Column(name="email")
	private String clientEmail;
    @Column(name="age")
	private Integer clientAge;

    public Client(Long clientId) {
        super(clientId);
    }
}
