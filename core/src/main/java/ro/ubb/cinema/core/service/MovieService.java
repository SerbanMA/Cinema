package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.entities.Movie;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;

import java.util.List;

public interface MovieService {
    /**
     * Adds the given movie to the movies repository.
     *
     * @param movie
     *            must not be null
     */
     Movie saveMovie(Movie movie) throws ValidatorException;

    /**
     * Removes from the repository the movie which has the same id as the given parameter
     *
     * @param id
     *            must not be null.
     */
     void deleteMovie(Long id);

    /**
     * Updates in the repository the movie which has the same id as the parameter
     *
     * @param movie
     *            must not be null.
     */
     Movie updateMovie(Movie movie) throws ValidatorException;

    /**
     *
     * @return all Movie entities.
     */
    List<Movie> getAllMovies();


    /**
     * Returns all movies whose name contain the given string.
     *
     * @param s - string which has to be contained in the name
     * @return an Set<Movie> - all the movies which contains the string in the name
     */
    List<Movie> filterMoviesByName(String s);
//
    /**
     * Returns all movies whose name contain the given string.
     *
     * @param genre - the genre to be deleted
     * @return an Set<Movie> - all the movies which contains the string in the name
     */
    List<Movie> filterMoviesByGenre(String genre);
//
//    /**
//     * Returns the movies with the maximum duration
//     *
//     * @return an Set<Movie> - the longest movies
//     */
//     Set<Movie> getLongestMovies();
//
//    /**
//     * Check if a movie with the given identifier exists in the repository
//     * @param identifier - long, the identifier of the movie searched for
//     * @return - boolean
//     *              true, if the repo contains a movie with the given identifier
//     *              false, otherwise
//     */
//
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the movie that have the given identifier
//     * @param identifier - long, the identifier of the movie searched for
//     * @return - movie object
//     * @throws ArrayIndexOutOfBoundsException
//     *          if movie not found
//     */
//    Movie get(Long identifier) throws ArrayIndexOutOfBoundsException;
}
