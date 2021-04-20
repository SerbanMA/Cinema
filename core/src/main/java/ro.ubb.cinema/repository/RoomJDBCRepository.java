package ro.ubb.cinema.repository;

import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

public interface RoomJDBCRepository extends Repository<Long, Room> {
        List<Room> getAllByNumberOfSeatsGreaterThanEqual(Integer numberOfSeats);
}
