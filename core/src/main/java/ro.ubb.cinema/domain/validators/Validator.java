package ro.ubb.cinema.domain.validators;

import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
