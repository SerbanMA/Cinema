package ro.ubb.cinema.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovieDto extends BaseDto{
    private String name;
    private Integer duration;
    private String genre;
}
