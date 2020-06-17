package ro.ubb.movieapp.web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieDto extends BaseDto {

    private String title;
    private int year;
}
