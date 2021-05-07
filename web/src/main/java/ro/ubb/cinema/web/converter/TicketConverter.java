package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.core.domain.entities.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto>{

    private final ClientConverter clientConverter = new ClientConverter();
    private final MovieConverter movieConverter = new MovieConverter();
    private final RoomConverter roomConverter = new RoomConverter();

    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setClient(clientConverter.convertDtoToModel(dto.getClient()));
        model.setMovie(movieConverter.convertDtoToModel(dto.getMovie()));
        model.setRoom(roomConverter.convertDtoToModel(dto.getRoom()));
        model.setPrice(dto.getPrice());
        model.setTime(
                (dto.getTime() != null) ?
                        LocalTime.of(
                                Integer.parseInt(dto.getTime().split(":")[0]),
                                Integer.parseInt(dto.getTime().split(":")[1])
                        ) :
                        null
        );
        model.setDate(
                (dto.getDate() != null) ?
                        LocalDate.of(
                                Integer.parseInt(dto.getDate().split("/")[2]),
                                Integer.parseInt(dto.getDate().split("/")[1]),
                                Integer.parseInt(dto.getDate().split("/")[0])
                        ) :
                        null
        );

        return model;
    }

    @Override
    public TicketDto convertModelToDto(Ticket ticket) {

        String time = ticket.getTime() != null ?
                ticket.getTime().toString() :
                null;
        String date = ticket.getDate() != null ?
                ticket.getDate().getDayOfMonth() + " " + ticket.getDate().getMonth() + " " + ticket.getDate().getYear() :
                null;

        TicketDto dto = new TicketDto(ticket.getPrice(), date, time, movieConverter.convertModelToDto(ticket.getMovie()), roomConverter.convertModelToDto(ticket.getRoom()), clientConverter.convertModelToDto(ticket.getClient()));
        dto.setId(ticket.getId());
        return dto;
    }
}
