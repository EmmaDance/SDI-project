package ro.ubb.movieapp.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Actor extends BaseEntity<Long> {
    private String name;

    private int rating;

    @JoinColumn(name = "movieId", referencedColumnName = "id")
    private long movieId;
}
