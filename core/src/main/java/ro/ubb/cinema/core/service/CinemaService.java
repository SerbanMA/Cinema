package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.entities.Cinema;

import java.util.List;

public interface CinemaService {
    /**
     * Adds the given cinema to the repository
     * @param cinema - the cinema that will be added
     */
    Cinema saveCinema(Cinema cinema);

    /**
     * Deletes from the repository the cinema that has the same as id as the cinema that was passed as a parameter
     * @param id - the cinema to be deleted
     */
    void deleteCinema(Long id);

    /**
     * Updates in the repository the cinema that has the same id as the given one
     * @param cinema - cinema object to be deleted
     */
    Cinema updateCinema(Cinema cinema);

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

    /**
     * Returns all cinemas whose fields contain a given string passed as a parameter
     * @param string - the string which should be included in the fields
     * @return a Set of the filtered cinemas
     */
    List<Cinema> filterCinemaByAllFields(String string);

}
