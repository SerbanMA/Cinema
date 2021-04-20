package ro.ubb.cinema.domain.validators;

import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Optional;

public class TicketValidator implements Validator<Ticket>{

    public TicketValidator(){}

    @Override
    public void validate(Ticket entity) throws ValidatorException {

        Optional.of(entity)
                .filter(ticket -> String.valueOf(ticket.getPrice()).matches("^[0-9]*.?[0-9]*$") && ticket.getPrice() >= 0)
                .orElseThrow( () -> new ValidatorException("Invalid Price format"));

        Optional.of(entity)
                .filter(ticket -> ChronoUnit.DAYS.between(ticket.getDate(), LocalDate.now()) >= -7 )
                .orElseThrow( () -> new ValidatorException("Invalid Date"));
    }
}

