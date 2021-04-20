package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.service.RoomService;
import ro.ubb.cinema.web.converter.RoomConverter;
import ro.ubb.cinema.web.dto.CinemasDto;
import ro.ubb.cinema.web.dto.RoomDto;
import ro.ubb.cinema.web.dto.RoomsDto;

@RestController
public class RoomController {
    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomConverter roomConverter;

    @RequestMapping(value = "/rooms")
    RoomsDto getAllRooms() {
        log.trace("getAllRooms - method entered");

        RoomsDto roomsDto = new RoomsDto(
                roomConverter.convertModelsToDtos(
                        roomService.getAllRooms()));

        log.trace("getAllRooms - method finished: roomsDto={}", roomsDto);

        return roomsDto;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    RoomDto addRoom(@RequestBody RoomDto roomDto){
        log.trace("addRoom - method entered: roomDto={}", roomDto);
        var room = roomConverter.convertDtoToModel(roomDto);

        var result = roomService.saveRoom(room);

        var resultModel = roomConverter.convertModelToDto(result);

        log.trace("addRoom - method finished: resultMode={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT)
    RoomDto updateRoom(@PathVariable Long id,
                       @RequestBody RoomDto dto) {
        log.trace("updateRoom - method entered: roomDto={}", dto);

        RoomDto roomDto = roomConverter.convertModelToDto(
                roomService.updateRoom(
                        roomConverter.convertDtoToModel(dto)
                ));
        log.trace("updateRoom - method finished: roomDto={}", roomDto);

        return roomDto;
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        log.trace("deleteRoom - method entered: id={}", id);
        roomService.deleteRoom(id);
        log.trace("deleteRoom - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms/filterByNumberOfSeats", method = RequestMethod.POST)
    RoomsDto filterRoomByNumberOfSeats(@RequestBody Integer numberOfSeatsToCompareWith) {
        log.trace("filterRoomByNumberOfSeats - method entered: numberOfSeatsToCompareWith={}", numberOfSeatsToCompareWith);

        RoomsDto roomsDto = new RoomsDto(
                roomConverter.convertModelsToDtos(
                        roomService.filterRoomByNoOfSeats(numberOfSeatsToCompareWith)));

        log.trace("filterRoomByNumberOfSeats - method finished");
        return roomsDto;
    }
}
