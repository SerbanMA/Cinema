package ro.ubb.cinema.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IDCardDto {
    String cnp;
    String series;
    Integer number;
    String address;
    String dateOfBirth;
}
