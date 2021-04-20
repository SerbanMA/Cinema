package ro.ubb.cinema.repository;

import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

public interface TicketJDBCRepository extends Repository<Long, Ticket> {
    List<Ticket> getAllByPriceLessThanEqual(Double price);
}
