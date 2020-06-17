package ro.ubb.movieapp.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "movieWithActors",
                attributeNodes = @NamedAttributeNode(value = "actors"))})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long> {
    private String title;

    private int year;

    //actors
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movieId")
    private List<Actor> actors = new ArrayList<>();

}
