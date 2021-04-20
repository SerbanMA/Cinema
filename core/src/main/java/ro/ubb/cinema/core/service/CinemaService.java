package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.entities.Cinema;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;

import java.util.List;

public interface CinemaService {
    /**
     * Adds the given cinema to the repository
     * @param cinema - the cinema that will be added
     */
    Cinema saveCinema(Cinema cinema) throws ValidatorException;

    /**
     * Deletes from the repository the cinema that has the same as id as the cinema that was passed as a parameter
     * @param id - the cinema to be deleted
     */
    void deleteCinema(Long id);

    /**
     * Updates in the repository the cinema that has the same id as the given one
     * @param cinema - cinema object to be deleted
     */
    Cinema updateCinema(Cinema cinema) throws ValidatorException;

    /**
     *
     * @return a Set containing all the cinemas from the repository
     */
    List<Cinema> getAllCinemas();

    /**
     * Returns all cinemas whose name contain a given string passed as a parameter
     * @param string - the string which should be included in the name
     * @return a Set of the filtered cinemas
     */
    List<Cinema> filterCinemaByName(String string);
//
//    void deleteCinemaByAddress(String string);
//
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the cinema that have the given identifier
//     * @param identifier - long, the identifier of the cinema searched for
//     * @return - cinema object
//     * @throws ArrayIndexOutOfBoundsException
//     *          if cinema not found
//     */
//    Cinema get(Long identifier) throws  ArrayIndexOutOfBoundsException;
}
