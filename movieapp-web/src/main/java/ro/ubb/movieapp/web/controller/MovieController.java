package ro.ubb.movieapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.service.MovieService;
import ro.ubb.movieapp.web.converter.MovieConverter;
import ro.ubb.movieapp.web.converter.MovieWithActorsConverter;
import ro.ubb.movieapp.web.dto.MovieDto;
import ro.ubb.movieapp.web.dto.MovieWithActorsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private MovieWithActorsConverter movieWithActorsConverter;

    @RequestMapping(value = "/movies/{year}/{lessThan}", method = RequestMethod.GET)
    List<MovieDto> getMovies(@PathVariable Integer year, @PathVariable Integer lessThan) {
        log.trace("getMovies");
        List<Movie> movies;
        if (lessThan == 1){
             movies = movieService.getMoviesByYear(year,true);

        }
        else{
             movies = movieService.getMoviesByYear(year,false);

        }
        log.trace("getMovies: movies={}", movies);
        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
    }

    @RequestMapping(value = "/moviesWithActors/{year}/{lessThan}", method = RequestMethod.GET)
    List<MovieWithActorsDto> getMoviesWithActors(@PathVariable Integer year, @PathVariable Integer lessThan) {
        log.trace("getMoviesWithActors");
        List<Movie> movies;
        if (lessThan == 1){
            movies = movieService.getMoviesWithActorsByYear(year,true);

        }
        else{
            movies = movieService.getMoviesWithActorsByYear(year,false);

        }
        log.trace("getMoviesWithActors: movies={}", movies);
        return new ArrayList<>(movieWithActorsConverter.convertModelsToDtos(movies));
    }
//
//    @RequestMapping(value = "/movies", method = RequestMethod.POST)
//    MovieDto saveMovie(@RequestBody MovieDto dto) {
//        log.trace("saveMovie: dto={}", dto);
//        Optional<Movie> op = this.movieService.add(movieConverter.convertDtoToModel(dto));
//        try {
//            op.orElseThrow(Exception::new);
//        }catch(Exception e) {
//            return null;
//        }
//        MovieDto result = movieConverter.convertModelToDto(op.get());
//        log.trace("saveMovie: result={}", result);
//        return result;
//    }
//
//    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
//    MovieDto updateMovie(@PathVariable Integer id, @RequestBody MovieDto dto) {
//        log.trace("updateMovie: id={}, dto={}", id, dto);
//        Movie update = movieService.update(movieConverter.convertDtoToModel(dto)).get();
//        MovieDto result = movieConverter.convertModelToDto(update);
//        log.trace("updateMovie: result={}", result);
//        return result;
//    }
//
    @RequestMapping(value = "/delete/{id}/{movieId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id, @PathVariable Long movieId) {
        log.trace("deleteMovie: id={}", id);
        movieService.deleteActor( movieId, id);
        log.trace("deleteMovie --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/movies/filterG/{genre}", method = RequestMethod.GET)
//    List<MovieDto> getAllMoviesGenre(@PathVariable String genre) {
//        log.trace("filterMoviesG --- method entered");
//        Predicate<Movie> filterG = m -> m.getGenre().equals(genre);
//        List<Movie> movies = movieService.filterFunction(filterG);
//        log.trace("filterMoviesG: result=", movies);
//        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
//    }
//
//    @RequestMapping(value = "/movies/sortT", method = RequestMethod.GET)
//    List<MovieDto> sortAllMoviesTitle() {
//        log.trace("sortMoviesG--- method entered");
//        Comparator<Movie> func = Comparator.comparing(Movie::getTitle, String::compareTo);
//        List<Movie> movies = movieService.sortFunction(func);
//        log.trace("sortMoviesG: result={}", movies);
//        return new ArrayList<>(movieConverter.convertModelsToDtos(movies));
//    }
}