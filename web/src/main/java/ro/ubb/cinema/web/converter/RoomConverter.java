package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.core.domain.entities.Room;
import ro.ubb.cinema.web.dto.RoomDto;

@Component
public class RoomConverter extends BaseConverter<Room, RoomDto>{
    @Override
    public Room convertDtoToModel(RoomDto dto) {
        var model = new Room();
        model.setId(dto.getId());
        model.setFloorNumber(dto.getFloorNumber());
        model.setNumberOfSeats(dto.getNumberOfSeats());
        model.setName(dto.getName());
        model.setCinema(dto.getCinema());
        return model;
    }

    @Override
    public RoomDto convertModelToDto(Room room) {
        RoomDto dto = new RoomDto(room.getFloorNumber(), room.getName(), room.getNumberOfSeats(), room.getCinema());
        dto.setId(room.getId());
        return dto;
    }
}
