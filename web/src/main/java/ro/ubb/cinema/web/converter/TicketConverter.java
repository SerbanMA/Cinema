package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.core.domain.entities.Ticket;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto>{
    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setClient(dto.getClient());
        model.setDate(dto.getDate());
        model.setMovie(dto.getMovie());
        model.setPrice(dto.getPrice());
        model.setRoom(dto.getRoom());
        model.setTime(dto.getTime());
        return model;
    }

    @Override
    public TicketDto convertModelToDto(Ticket ticket) {
        TicketDto dto = new TicketDto(ticket.getPrice(), ticket.getDate(), ticket.getTime(), ticket.getMovie(), ticket.getRoom(), ticket.getClient());
        dto.setId(ticket.getId());
        return dto;
    }
}
