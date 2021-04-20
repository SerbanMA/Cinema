package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.domain.entities.Room;

import java.util.List;

public interface RoomService {

    /**
     * Adds new room
     *
     * @param room - the room to be added
     */
    Room saveRoom(Room room) throws ValidatorException;

    /**
     * Deletes given room
     *
     * @param id - the room to be deleted
     */
    void deleteRoom(Long id);

    /**
     * Updates given room
     *
     * @param room - the room to be updated
     */
    Room updateRoom(Room room) throws ValidatorException;

    /**
     * Returns all rooms
     *
     * @return - all rooms
     */
    List<Room> getAllRooms();


    /**
     * Returns all rooms whose no. of seats is greater or equal to the given integer.
     *
     * @param noToCompareWith - the seat number to be compared with
     * @return - filtered Set of Rooms
     */
    List<Room> filterRoomByNoOfSeats(Integer noToCompareWith);
//
//    /**
//     * Removes all rooms whose no. of seats is smaller than the given integer.
//     *
//     * @param noToCompareWith - the seat number to be compared with
//     */
//    void deleteRoomWithNoOfSeatsSmallerThanGiven(Integer noToCompareWith);
//
//    /**
//     * Returns all rooms whose no. of seats is the maximum value and the floor number is the minimum one.
//     *
//     * @return rooms
//     */
//    Set<Room> getRoomsWithLowestFloorNumberAndHighestNumberOfSeats();
//
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the room that have the given identifier
//     * @param identifier - long, the identifier of the room searched for
//     * @return - room object
//     * @throws ArrayIndexOutOfBoundsException
//     *          if room not found
//     */
//    Room get(Long identifier) throws ArrayIndexOutOfBoundsException;
}
