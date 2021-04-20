package ro.ubb.cinema.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CinemaDto extends BaseDto{
    private String name;
    private String address;
}

