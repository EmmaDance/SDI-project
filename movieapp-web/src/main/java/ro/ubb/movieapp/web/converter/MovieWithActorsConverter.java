package ro.ubb.movieapp.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.web.dto.MovieWithActorsDto;

@Component
public class MovieWithActorsConverter extends BaseConverter<Movie, MovieWithActorsDto> {
    @Override
    public Movie convertDtoToModel(MovieWithActorsDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .actors(dto.getActors())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieWithActorsDto convertModelToDto(Movie movie) {
        MovieWithActorsDto dto = MovieWithActorsDto.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .actors(movie.getActors())
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}