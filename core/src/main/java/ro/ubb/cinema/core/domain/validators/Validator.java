package ro.ubb.cinema.core.domain.validators;

import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
