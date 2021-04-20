package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.validators.MovieValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.MovieJDBCRepository;

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
    public List<Movie> filterMoviesByGenre(String genre) {
        log.trace("filterMoviesByGenre - method entered: genre={}", genre);

        List<Movie> filteredMovies = repository.getAllByGenreContaining(genre);

        log.trace("filterMoviesByGenre - method finished: filteredMovies={}", filteredMovies);

        return filteredMovies;
    }
//
//    @Override
//    public Set<Movie> getLongestMovies() {
//        log.trace("filterMoviesByName - method entered");
//
//        Iterable<Movie> allMovies = repository.findAll();
//
//        Set<Movie> movies = new HashSet<>();
//        allMovies.forEach(movies::add);
//
//        int maxDuration = movies.stream().mapToInt(Movie::getDuration)
//                .max().orElseThrow(RuntimeException::new);
//
//        movies.removeIf(movie -> !movie.getDuration().equals(maxDuration));
//
//        log.trace("filterMoviesByName - method finished: longestMovies={}", movies);
//
//        return movies;
//    }
//
//    @Override
//    public Boolean containsOne(Long identifier)
//    {
//        log.trace("containsOne - method entered");
//
//        Boolean result = this.repository.findById(identifier).isPresent();
//
//        log.trace("containsOne - method finished: result={}", result);
//
//        return result;
//    }
//
//    @Override
//    public Movie get(Long identifier){
//        log.trace("get - method entered: identifier={}", identifier);
//
//        Optional<Movie> movie = this.repository.findById(identifier);
//        if (movie.isPresent())
//        {
//            log.trace("get - method finished");
//            return movie.get();
//        }
//        else
//        {
//            log.trace("get - exception found");
//            throw new ArrayIndexOutOfBoundsException("Movie not found");
//        }
//    }
}
