package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.core.domain.entities.Ticket;
import ro.ubb.cinema.core.domain.validators.TicketValidator;
import ro.ubb.cinema.core.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.core.repository.ClientJDBCRepository;
import ro.ubb.cinema.core.repository.MovieJDBCRepository;
import ro.ubb.cinema.core.repository.RoomJDBCRepository;
import ro.ubb.cinema.core.repository.TicketJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketJDBCRepository repository;
    @Autowired
    private RoomJDBCRepository roomRepository;
    @Autowired
    private ClientJDBCRepository clientRepository;
    @Autowired
    private MovieJDBCRepository movieRepository;

    private final TicketValidator ticketValidator = new TicketValidator();
    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public Ticket saveTicket(Ticket ticket) throws ValidatorException {
        log.trace("addTicket - method entered: ticket={}", ticket);
        ticketValidator.validate(ticket);
        Ticket returnedTicket = repository.save(ticket);
        log.trace("addTicket - method finished");
        return returnedTicket;
    }

    @Override
    public void deleteTicket(Long id) {
        log.trace("deleteTicket - method entered: ticketId={}", id);
            repository.deleteById(id);
            log.trace("deleteTicket - method finished");
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) throws ValidatorException {
        log.trace("updateTicket - method entered: ticket={}", ticket);
        ticketValidator.validate(ticket);
        Ticket updateTicket = repository.findById(ticket.getId()).orElseThrow();
        updateTicket.setRoom(ticket.getRoom());
        updateTicket.setClient(ticket.getClient());
        updateTicket.setMovie(ticket.getMovie());
        updateTicket.setPrice(ticket.getPrice());
        updateTicket.setTime(ticket.getTime());
        updateTicket.setDate(ticket.getDate());
        log.trace("updateCinema - method finished");
        return ticket;

    }

    @Override
    public List<Ticket> getAllTickets() {
        log.trace("getAllTickets - method entered");
        List<Ticket> tickets = repository.findAll();

        log.trace("getAllTickets - method finished: tickets={}", tickets);
        return tickets;
    }

    @Override
    public List<Ticket> filterTicketsByPrice(Double priceToCompare) {
        log.trace("filterTicketsByPrice - method entered: price={}", priceToCompare);

        List<Ticket> filteredTickets = repository.getAllByPriceLessThanEqual(priceToCompare);

        log.trace("filterTicketsByPrice - method finished: filteredTickets={}", filteredTickets);
        return filteredTickets;
    }
}
