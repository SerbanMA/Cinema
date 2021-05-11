package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.entities.Ticket;
import ro.ubb.cinema.core.domain.entities.Tuple;

import java.util.List;
import java.util.Map;

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
     */
    Ticket saveTicket(Ticket ticket);

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
    Ticket updateTicket(Ticket ticket);


    /**
     * Returns all Tickets whose price is less than the given value.
     *
     * @param i - integer
     * @return an {@code Set<Ticket>} - set of tickets that have the price less than the given value.
     */
    List<Ticket> filterTicketsByPrice(Double i);

    List<Tuple> getStatistics();
}
