package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.repository.RoomJDBCRepository;
import ro.ubb.cinema.core.domain.entities.Room;
import ro.ubb.cinema.core.domain.validators.RoomValidator;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author fivia.
 */

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomJDBCRepository repository;

    private final RoomValidator roomValidator = new RoomValidator();
    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);


    @Override
    public Room saveRoom(Room room) throws ValidatorException {
        log.trace("addRoom - method entered: room={}", room);
        roomValidator.validate(room);
        Room updatedRoom = repository.save(room);
        log.trace("addRoom - method finished");
        return updatedRoom;
    }

    @Override
    public void deleteRoom(Long id){
        log.trace("deleteRoom - method entered: room={}", id);
        repository.deleteById(id);
        log.trace("deleteRoom - method finished");
    }

    @Override
    @Transactional
    public Room updateRoom(Room room) throws ValidatorException {
        log.trace("updateRoom - method entered: room={}", room);
        roomValidator.validate(room);
        Room updateRoom = repository.findById(room.getId()).orElseThrow();
        updateRoom.setName(room.getName());
        updateRoom.setCinema(room.getCinema());
        updateRoom.setNumberOfSeats(room.getNumberOfSeats());
        updateRoom.setFloorNumber(room.getFloorNumber());
        log.trace("updateRoom - method finished");
        return room;

    }

    @Override
    public List<Room> getAllRooms()
    {
        log.trace("getAllRooms - method entered");
        List<Room> rooms = repository.findAll();

        log.trace("getAllRooms - method finished: rooms={}", rooms);
        return rooms;
    }


    @Override
    public List<Room> filterRoomByNoOfSeats(Integer noToCompare) //>= than given number
    {
        log.trace("filterRoomByNoOfSeats - method entered: noToCompareWith={}", noToCompare);

        List<Room> filteredRooms = repository.getAllByNumberOfSeatsGreaterThanEqual(noToCompare);

        log.trace("filterRoomByNoOfSeats - method finished: filteredRooms={}", filteredRooms);
        return filteredRooms;
    }
}
