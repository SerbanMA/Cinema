package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.core.domain.entities.Ticket;
import ro.ubb.cinema.core.domain.entities.Tuple;
import ro.ubb.cinema.core.repository.ClientJDBCRepository;
import ro.ubb.cinema.core.repository.MovieJDBCRepository;
import ro.ubb.cinema.core.repository.RoomJDBCRepository;
import ro.ubb.cinema.core.repository.TicketJDBCRepository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Service
public class TicketServiceImpl implements TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketJDBCRepository repository;
    @Autowired
    private RoomJDBCRepository roomRepository;
    @Autowired
    private ClientJDBCRepository clientRepository;
    @Autowired
    private MovieJDBCRepository movieRepository;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        log.trace("addTicket - method entered: ticket={}", ticket);
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
    public Ticket updateTicket(Ticket ticket) {
        log.trace("updateTicket - method entered: ticket={}", ticket);
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
        tickets.sort(Comparator.comparing(Ticket::getId));

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

    public List<Tuple> getStatistics(){

        List<Tuple> solution = new ArrayList<>();
        List<Ticket> tickets = repository.findAll();

        List<String> ticketsDate = tickets.stream()
                .map(s -> s.getDate().getMonth().toString())
                .collect(Collectors.toList());

        Map<String, Long> counted = ticketsDate.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        counted.forEach((key, value) -> solution.add(new Tuple(key, Math.toIntExact(value))));

        solution.sort((a, b) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMMM");
            try {
                System.out.println(sdf.parse(a.getX()));
                return sdf.parse(a.getX()).compareTo(sdf.parse(b.getX()));
            } catch (ParseException e) {
                return a.getX().compareTo(b.getX());
            }
        });


        return solution;
    }

}


