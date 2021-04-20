package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.service.CinemaService;
import ro.ubb.cinema.web.converter.CinemaConverter;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.CinemasDto;

import java.util.List;

@RestController
public class CinemaController {
    private static final Logger log = LoggerFactory.getLogger(CinemaController.class);
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaConverter cinemaConverter;

    @RequestMapping(value = "/cinemas")
    CinemasDto getAllCinemas() {

        log.trace("getAllCinemas - method entered");

        CinemasDto cinemasDto = new CinemasDto(
                cinemaConverter.convertModelsToDtos(
                        cinemaService.getAllCinemas()));

        log.trace("getAllCinemas - method finished: cinemasDto={}", cinemasDto);

        return cinemasDto;
    }

    @RequestMapping(value = "/cinemas", method = RequestMethod.POST)
    CinemaDto addCinema(@RequestBody CinemaDto cinemaDto) {

        log.trace("addCinema - method entered: cinemaDto={}", cinemaDto);
        var cinema = cinemaConverter.convertDtoToModel(cinemaDto);

        var result = cinemaService.saveCinema(cinema);
        var resultModel = cinemaConverter.convertModelToDto(result);

        log.trace("addCinema - method finished: resultModel={}", resultModel);

        return resultModel;
    }

    @RequestMapping(value = "/cinemas/{id}", method = RequestMethod.PUT)
    CinemaDto updateCinema(@PathVariable Long id,
                           @RequestBody CinemaDto dto) {
        log.trace("updateCinema - method entered: cinemaDto={}", dto);

        CinemaDto cinemaDto = cinemaConverter.convertModelToDto(
                cinemaService.updateCinema(
                        cinemaConverter.convertDtoToModel(dto)
                ));

        log.trace("updateCinema - method finished: cinemaDto={}", cinemaDto);

        return cinemaDto;
    }

    @RequestMapping(value = "/cinemas/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCinema(@PathVariable Long id) {
        log.trace("deleteCinema - method entered: id={}", id);
        cinemaService.deleteCinema(id);
        log.trace("deleteCinema - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cinemas/filterByName", method = RequestMethod.POST)
    CinemasDto filterCinemaByName(@RequestBody String name) {
        log.trace("filterCinemaByName - method entered: name={}", name);

        CinemasDto cinemasDto = new CinemasDto(
                cinemaConverter.convertModelsToDtos(
                        cinemaService.filterCinemaByName(name)));

        log.trace("filterByName - method finished");
        return cinemasDto;
    }
}
