package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.service.MovieService;
import ro.ubb.cinema.web.converter.MovieConverter;
import ro.ubb.cinema.web.dto.ClientsDto;
import ro.ubb.cinema.web.dto.MovieDto;
import ro.ubb.cinema.web.dto.MoviesDto;

@RestController
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies")
    MoviesDto getAllMovies() {
        log.trace("getAllMovies - method entered");

        MoviesDto moviesDto = new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.getAllMovies()));

        log.trace("getAllMovies - method finished: moviesDto={}", moviesDto);

        return moviesDto;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto movieDto){
        log.trace("addMovie - method entered: movieDto={}", movieDto);
        var movie = movieConverter.convertDtoToModel(movieDto);

        var result = movieService.saveMovie(movie);

        var resultModel = movieConverter.convertModelToDto(result);

        log.trace("addMovie - method finished: resultModel={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable Long id,
                         @RequestBody MovieDto dto) {
        log.trace("updateMovie - method entered: movieDto={}", dto);

        MovieDto movieDto=  movieConverter.convertModelToDto(
                movieService.updateMovie(
                        movieConverter.convertDtoToModel(dto)
                ));

        log.trace("updateMovie - method finished: movieDto={}", movieDto);

        return movieDto;
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        log.trace("deleteMovie - method entered: id={}", id);
        movieService.deleteMovie(id);
        log.trace("deleteMovie - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/filterByName", method = RequestMethod.POST)
    MoviesDto filterMovieByName(@RequestBody String name) {
        log.trace("filterMovieByName - method entered: name={}", name);

        MoviesDto moviesDto = new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.filterMoviesByName(name)));

        log.trace("filterByName - method finished");
        return moviesDto;
    }
    @RequestMapping(value = "/movies/filterByGenre", method = RequestMethod.POST)
    MoviesDto filterMovieByGenre(@RequestBody String genre) {
        log.trace("filterMovieByName - method entered: genre={}", genre);

        MoviesDto moviesDto = new MoviesDto(
                movieConverter.convertModelsToDtos(
                        movieService.filterMoviesByGenre(genre)));

        log.trace("filterMovieByName - method finished");
        return moviesDto;
    }

}
