package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.core.domain.entities.Movie;
import ro.ubb.cinema.core.domain.validators.MovieValidator;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.repository.MovieJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for managing Movies
 *
 * @author camelia-lazar
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieJDBCRepository repository;

    private final MovieValidator movieValidator = new MovieValidator();
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);


    @Override
    public Movie saveMovie(Movie movie) throws ValidatorException {
        log.trace("addMovie - method entered: movie={}", movie);
        movieValidator.validate(movie);
        Movie returnedMovie = repository.save(movie);
        log.trace("addMovie - method finished");
        return returnedMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        log.trace("deleteMovie - method entered: movieId={}", id);
            repository.deleteById(id);
        log.trace("deleteMovie - method finished");
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        log.trace("updateMovie - method entered: movie={}", movie);
        movieValidator.validate(movie);
        Movie updateMovie = repository.findById(movie.getId()).orElseThrow();
        updateMovie.setName(movie.getName());
        updateMovie.setGenre(movie.getGenre());
        updateMovie.setDuration(movie.getDuration());
        log.trace("updateMovie - method finished");
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies - method entered");

        List<Movie> movies = repository.findAll();

        log.trace("getAllMovies - method finished: movies={}", movies);
        return movies;
    }

   @Override
    public List<Movie> filterMoviesByName(String nameToFilter) {
       log.trace("filterMoviesByName - method entered: nameToFilter={}", nameToFilter);
       Iterable<Movie> movies = repository.findAll();

        List<Movie> filteredMovies = repository.getAllByNameContaining(nameToFilter);

       log.trace("filterMoviesByName - method finished: filteredMovies={}", filteredMovies);

       return filteredMovies;
    }

    @Override
    public List<Movie> filterMoviesByGenre(String genreToFilter) {
        log.trace("filterMoviesByGenre - method entered: genre={}", genreToFilter);

        List<Movie> filteredMovies = repository.getAllByGenreContaining(genreToFilter);

        log.trace("filterMoviesByGenre - method finished: filteredMovies={}", filteredMovies);

        return filteredMovies;
    }
}
