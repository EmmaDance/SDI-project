package ro.ubb.movieapp.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.movieapp.core.model.Movie;

import java.util.List;

/**
 * author: radu
 */
public interface MovieRepository extends MovieAppRepository<Movie, Long> {

    @Query("SELECT t FROM Movie t WHERE t.year <= :year")
    List<Movie> findAllWithYearLessThanEqual( @Param("year") int year);

    @Query("SELECT t FROM Movie t WHERE " +
            "t.year > :year")
    List<Movie> findAllWithYearGreaterThan( @Param("year") int year);

    @Query("select distinct t from Movie t WHERE " +
            "t.year <= :year")
    @EntityGraph(value = "movieWithActors", type =
            EntityGraph.EntityGraphType.LOAD, attributePaths = {"actors"})
    List<Movie> findAllWithActorsWithYearLessThanEqual( @Param("year") int year);

    @Query("select distinct t from Movie t WHERE " +
            "t.year > :year")
    @EntityGraph(value = "movieWithActors", type =
            EntityGraph.EntityGraphType.LOAD, attributePaths = {"actors"})
    List<Movie> findAllWithActorsWithYearGreaterThan( @Param("year") int year);

    @Query("select distinct m from Movie m where m.id = :id")
    @EntityGraph(value = "movieWithActors", type =
            EntityGraph.EntityGraphType.LOAD, attributePaths = {"actors"})
    Movie findOneWithActors(@Param("id") long id);

}
