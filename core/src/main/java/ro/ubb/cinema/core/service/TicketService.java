package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.domain.entities.Ticket;

import java.util.List;

public interface TicketService {
    /**
     * Returns all the tickets of a repository
     *
     * @return all tickets.
     */
    List<Ticket> getAllTickets();

    /**
     * Adds the given ticket.
     *
     * @param ticket
     *          must not be null.
     * @throws IllegalArgumentException
     *          if the given entity is null.
     * @throws ValidatorException
     *          if the ticket is not valid.
     */
    Ticket saveTicket(Ticket ticket) throws ValidatorException;

    /**
     * Removes the given ticket.
     *
     * @param id
     *          must not be null.
     */
    void deleteTicket(Long id);

    /**
     * Updates the given ticket.
     *
     * @param ticket
     *          must not be null.
     */
    Ticket updateTicket(Ticket ticket) throws ValidatorException;


    /**
     * Returns all Tickets whose price is less than the given value.
     *
     * @param i - integer
     * @return an {@code Set<Ticket>} - set of tickets that have the price less than the given value.
     */
    List<Ticket> filterTicketsByPrice(Double i);

//    /**
//     * Deletes all Tickets whose date is equal with the given date.
//     *
//     * @param date - date
//     */
//    void deleteTicketsByDate(LocalDate date);
//
//    /**
//     * Check if a ticket with the given identifier exists in the repository
//     * @param identifier - long, the identifier of the ticket searched for
//     * @return - boolean
//     *              true, if the repo contains a ticket with the given identifier
//     *              false, otherwise
//     */
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the ticket that have the given identifier
//     * @param identifier - long, the identifier of the ticket searched for
//     * @return - ticket object
//     * @throws ArrayIndexOutOfBoundsException
//     *          if ticket not found
//     */
//    Ticket get(Long identifier) throws ArrayIndexOutOfBoundsException;
//
//    /**
//     * Returns the repository
//     *
//     * @return the repository
//     */
//    Repository<Long, Ticket> getRepository();
}
