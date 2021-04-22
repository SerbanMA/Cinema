package ro.ubb.cinema.core.domain.validators;

import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.domain.entities.Client;

import java.util.Optional;

public class ClientValidator implements Validator<Client>{

    /**
     * Validate an object of type Client
     * @param entity - Client
     *               - the entity to be validated
     * @throws ValidatorException
     *                      if the entity is not valid
     */
    @Override
    public void validate(Client entity) throws ValidatorException {

        Optional.ofNullable(entity.getFirstName())
                .filter(firstName -> firstName.matches("^[a-zA-Z-_]+$"))
                .orElseThrow( () -> new ValidatorException("Client's First Name is NOT valid: it must contain only letters."));

        Optional.ofNullable(entity.getLastName())
                .filter(lastName -> lastName.matches("^[a-zA-Z-_]+$"))
                .orElseThrow( () -> new ValidatorException("Client's Last Name is NOT valid: it must contain only letters."));

        Optional.ofNullable(entity.getEmail())
                .filter(email -> email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                .orElseThrow( () -> new ValidatorException("Client's Email is NOT valid. Please try again."));

        Optional.ofNullable(entity.getAge())
                .filter(age -> String.valueOf(age).matches("^[0-9]+$") && age >= 0)
                .orElseThrow( () -> new ValidatorException("Client's Age is NOT valid: it has to be a positive number."));
    }
}
