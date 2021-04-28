package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.core.domain.entities.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto>{
    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setClient(dto.getClient());
        model.setMovie(dto.getMovie());
        model.setRoom(dto.getRoom());
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

        String time = ticket.getTime().toString();
        String date = ticket.getDate().getDayOfMonth() + " " + ticket.getDate().getMonth() + " " + ticket.getDate().getYear();

        TicketDto dto = new TicketDto(ticket.getPrice(), date, time, ticket.getMovie(), ticket.getRoom(), ticket.getClient());
        dto.setId(ticket.getId());
        return dto;
    }
}
