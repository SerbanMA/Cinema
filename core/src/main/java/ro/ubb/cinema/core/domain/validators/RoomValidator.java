package ro.ubb.cinema.core.domain.validators;

import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.domain.entities.Room;

import java.util.Optional;

/**
 * @author fivia.
 */
public class RoomValidator implements Validator<Room>{
    /**
     * Validates entity of type room
     * throws exception in case the room entity is not valid
     *
     */
    @Override
    public void validate(Room entity) throws ValidatorException {

        Optional.of(entity)
                .filter(movie -> movie.getName().length() > 0)
                .orElseThrow(()-> {throw new ValidatorException("Room name must contain at least one character");});

        Optional.of(entity)
                .filter(movie -> movie.getNumberOfSeats()>=0)
                .orElseThrow(()-> {throw new ValidatorException("Number of seats has to be positive");});

        Optional.of(entity)
                .filter(movie -> movie.getFloorNumber()>=0)
                .orElseThrow(()-> {throw new ValidatorException("Floor number has to be positive");});
    }
}
