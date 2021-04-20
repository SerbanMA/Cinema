package ro.ubb.cinema.core.domain.validators;

import ro.ubb.cinema.core.domain.entities.Cinema;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;

import java.util.Optional;

public class CinemaValidator implements Validator<Cinema> {

    //Fia
    /**
     * Validate an object of Cinema type
     * @param entity - Cinema
     *               - the entity that needs to be validated
     * @throws ValidatorException
     *               - if the entity does not meet all the criteria
     */
    @Override
    public void validate(Cinema entity) throws ValidatorException {

        Optional.of(entity)
                .filter(cinema -> cinema.getName().length() > 0)
                .orElseThrow(()-> new ValidatorException("Cinema name should contain at least one character.")
                );

        Optional.of(entity)
                .filter(cinema -> cinema.getAddress().length() > 0)
                .orElseThrow(()->new ValidatorException("Cinema address should contain at least one character."));
    }
}
