package ro.ubb.movieapp.web.dto;

import lombok.*;
import ro.ubb.movieapp.core.model.Actor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieWithActorsDto extends BaseDto {

    private String title;
    private int year;
    private List<Actor> actors;
}
