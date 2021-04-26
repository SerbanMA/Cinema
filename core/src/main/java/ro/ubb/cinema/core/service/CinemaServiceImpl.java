package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.core.domain.entities.Cinema;
import ro.ubb.cinema.core.domain.validators.CinemaValidator;
import ro.ubb.cinema.core.repository.CinemaJDBCRepository;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;

import java.util.Comparator;
import java.util.List;

/**
 * Service for managing Cinemas
 *
 * @author razvan-kokovics
 */

@Service
public class CinemaServiceImpl implements CinemaService {
    private static final Logger log = LoggerFactory.getLogger(CinemaServiceImpl.class);

    @Autowired
    private CinemaJDBCRepository repository;

    private final CinemaValidator cinemaValidator = new CinemaValidator();

    @Override
    public Cinema saveCinema(Cinema cinema) throws ValidatorException{
        log.trace("saveCinema - method entered: cinema={}", cinema);
//        cinemaValidator.validate(cinema);
        Cinema returnedCinema = repository.save(cinema);
        log.trace("saveCinema - method finished");
        return returnedCinema;
    }

    @Override
    public void deleteCinema(Long id) throws ValidatorException {
        log.trace("deleteCinema - method entered: cinemaId={}", id);
        repository.deleteById(id);
        log.trace("deleteCinema - method finished");
    }

    @Override
    @Transactional
    public Cinema updateCinema(Cinema cinema) {
        log.trace("updateCinema - method entered: cinema={}", cinema);
//        cinemaValidator.validate(cinema);
        Cinema updateCinema = repository.findById(cinema.getId()).orElseThrow();
        updateCinema.setName(cinema.getName());
        updateCinema.setAddress(cinema.getAddress());
        log.trace("updateCinema - method finished");
        return cinema;
    }

    @Override
    public List<Cinema> getAllCinemas() {
        log.trace("getAllCinemas - method entered");

        List<Cinema> cinemas = repository.findAll();
        cinemas.sort(Comparator.comparing(Cinema::getId));

        log.trace("getAllCinemas - method finished: cinemas={}", cinemas);

        return cinemas;
    }

    @Override
    public List<Cinema> filterCinemaByName(String nameToFilter) {
        log.trace("filterCinemaByName - method entered: string={}", nameToFilter);

        List<Cinema> filteredCinemas = repository.getAllByNameContaining(nameToFilter);

        log.trace("filterCinemaByName - method finished: filteredCinemas={}", filteredCinemas);

        return filteredCinemas;
    }

    @Override
    public List<Cinema> filterCinemaByAllFields(String string) {
        log.trace("filterCinemaByName - method entered: string={}", string);

        List<Cinema> filteredCinemas = repository.getAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(string, string);

        log.trace("filterCinemaByName - method finished: filteredCinemas={}", filteredCinemas);

        return filteredCinemas;
    }
}
