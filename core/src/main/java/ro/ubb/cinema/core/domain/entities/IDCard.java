package ro.ubb.cinema.core.domain.entities;


import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class for the ID Card entity
 *
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IDCard implements Serializable{
    String cnp;
    String series;
    Integer number;
    String address;
    LocalDate dateOfBirth;
}
