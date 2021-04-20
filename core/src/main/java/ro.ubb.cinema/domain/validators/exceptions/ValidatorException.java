package ro.ubb.cinema.domain.validators.exceptions;

import ro.ubb.cinema.domain._exceptions.CinemaBaseException;

public class ValidatorException extends CinemaBaseException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
