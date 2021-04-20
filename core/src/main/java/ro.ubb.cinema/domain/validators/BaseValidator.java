package ro.ubb.cinema.domain.validators;

import ro.ubb.cinema.domain.entities.*;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;


public class BaseValidator<T> implements Validator<T>{

    /**
     * Validate an object of type BaseValidator
     * @param object - Object
     *               - the entity to be validated
     * @throws ValidatorException
     *                      if the entity is not valid
     */
    @Override
    public void validate(Object object) throws ValidatorException {

        if(object instanceof Client)
        {
            ClientValidator clientValidator = new ClientValidator();
            clientValidator.validate((Client)object);
        }
        else if(object instanceof Movie)
        {
            MovieValidator movieValidator = new MovieValidator();
            movieValidator.validate((Movie)object);
        }
        else if(object instanceof Room)
        {
            RoomValidator roomValidator = new RoomValidator();
            roomValidator.validate((Room)object);
        }
        else if(object instanceof Ticket)
        {
            TicketValidator ticketValidator = new TicketValidator();
            ticketValidator.validate((Ticket)object);
        }
        else if(object instanceof Cinema)
        {
            CinemaValidator cinemaValidator = new CinemaValidator();
            cinemaValidator.validate((Cinema)object);
        }
    }
}
