package ro.ubb.movieapp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieapp.core.model.Actor;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.repository.MovieRepository;

import java.util.Iterator;
import java.util.List;

/**
 * author: radu
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesByYear(int year, boolean lessThan) {
        List<Movie> movies;
        if (lessThan){
            movies =  movieRepository.findAllWithYearLessThanEqual(year);
        }
        else {
            movies =  movieRepository.findAllWithYearGreaterThan(year);
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesWithActorsByYear(int year, boolean lessThan) {
        if (lessThan){
            return movieRepository.findAllWithActorsWithYearLessThanEqual(year);
        }
        else {
            return movieRepository.findAllWithActorsWithYearGreaterThan(year);
        }
    }

    @Transactional
    @Override
    public void deleteActor(Long movieId, Long actorId) {
        Movie m = movieRepository.findOneWithActors(movieId);
        Actor toDelete = new Actor();
        for (Actor a : m.getActors()) {
            if (a.getId() == actorId) {
                toDelete = a;
                break;
            }
        }
        m.getActors().remove(toDelete);

    }
}
