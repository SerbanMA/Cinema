package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.domain.validators.RoomValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.RoomJDBCRepository;

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
        updateRoom.setRoomName(room.getRoomName());
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
    public List<Room> filterRoomByNoOfSeats(Integer noToCompareWith) //>= than given number
    {
        log.trace("filterRoomByNoOfSeats - method entered: noToCompareWith={}", noToCompareWith);

        List<Room> filteredRooms = repository.getAllByNumberOfSeatsGreaterThanEqual(noToCompareWith);

        log.trace("filterRoomByNoOfSeats - method finished: filteredRooms={}", filteredRooms);
        return filteredRooms;
    }

//    @Override
//    public void deleteRoomWithNoOfSeatsSmallerThanGiven(Integer noToCompareWith)
//    {
//        log.trace("deleteRoomWithNoOfSeatsSmallerThanGiven - method entered: noToCompareWith={}", noToCompareWith);
//        Iterable<Room> rooms = repository.findAll();
//
//        Set<Room> filteredRooms = new HashSet<>();
//        rooms.forEach(filteredRooms::add);
//        filteredRooms.removeIf(room -> room.getNumberOfSeats() >= noToCompareWith);
//        filteredRooms.forEach(room -> repository.deleteById(room.getId()));
//
//        log.trace("deleteRoomWithNoOfSeatsSmallerThanGiven - method finished");
//    }
//
//
//    @Override
//    public Set<Room> getRoomsWithLowestFloorNumberAndHighestNumberOfSeats()
//    {
//        log.trace("getRoomsWithLowestFloorNumberAndHighestNumberOfSeats - method entered");
//        Iterable<Room> allOfTheRooms = repository.findAll();
//        Set<Room> rooms = new HashSet<>();
//
//        allOfTheRooms.forEach(rooms::add);
//
//        int minimumFloorNumber = rooms.stream().mapToInt(Room::getFloorNumber)
//                .min().orElseThrow(RuntimeException::new);
//        int maximumNumberOfSeats = rooms.stream().mapToInt(Room::getNumberOfSeats)
//                .max().orElseThrow(RuntimeException::new);
//
//        rooms.removeIf(room -> !room.getFloorNumber().equals(minimumFloorNumber));
//        rooms.removeIf(room -> !room.getNumberOfSeats().equals(maximumNumberOfSeats));
//
//        log.trace("getRoomsWithLowestFloorNumberAndHighestNumberOfSeats - method finished: rooms={}",rooms);
//
//        return rooms;
//    }
//
//    @Override
//    public Boolean containsOne(Long identifier)
//    {
//        log.trace("containsOne - method entered");
//
//        Boolean result = this.repository.findById(identifier).isPresent();
//
//        log.trace("containsOne - method finished: result={}", result);
//
//        return result;    }
//
//    @Override
//    public Room get(Long identifier){
//        log.trace("get - method entered: identifier={}", identifier);
//
//        Optional<Room> room = this.repository.findById(identifier);
//        if (room.isPresent())
//        {
//            log.trace("get - method finished");
//            return room.get();
//        }
//        else
//        {
//            log.trace("get - exception found");
//            throw new ArrayIndexOutOfBoundsException("Room not found");
//        }
//    }
//
//    private void getCinema(Room room) {
//        log.trace("getCinema - method entered: room={}", room);
//        Optional<Cinema> cinema = this.cinemaRepository.findById(room.getCinema().getId());
//        if (cinema.isPresent())
//            room.setCinema(cinema.get());
//        else
//            throw new ServiceValidatorException("Cinema ID does not exists.");
//        log.trace("getCinema - method finished");
//    }
}
