package ro.ubb.cinema.core.repository;

import ro.ubb.cinema.core.domain.entities.Movie;

import java.util.List;

public interface MovieJDBCRepository extends Repository<Long, Movie> {

    List<Movie> getAllByNameContaining(String name);
    List<Movie> getAllByGenreContaining(String genre);

}
