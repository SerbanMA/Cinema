package ro.ubb.cinema.core.repository;

import ro.ubb.cinema.core.domain.entities.Ticket;

import java.util.List;

public interface TicketJDBCRepository extends Repository<Long, Ticket> {
    List<Ticket> getAllByPriceLessThanEqual(Double price);
}
