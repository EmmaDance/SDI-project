package ro.ubb.movieapp.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
