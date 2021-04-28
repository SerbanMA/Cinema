package ro.ubb.cinema.core.repository;

import ro.ubb.cinema.core.domain.entities.Room;

import java.util.List;

public interface RoomJDBCRepository extends Repository<Long, Room> {

        List<Room> getAllByNumberOfSeatsGreaterThanEqual(Integer numberOfSeats);

}
