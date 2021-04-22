package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.web.converter.TicketConverter;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.core.service.TicketService;

import java.util.List;

@RestController
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @RequestMapping(value = "/tickets")
    List<TicketDto> getAllTickets() {
        log.trace("getAllTickets - method entered");

        List<TicketDto> ticketsDto = ticketConverter.convertModelsToDtos(
                        ticketService.getAllTickets());

        log.trace("getAllTickets - method finished: ticketsDto={}", ticketsDto);
        return ticketsDto;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    TicketDto addTicket(@RequestBody TicketDto ticketDto){
        log.trace("addTicket - method entered: ticketDto={}", ticketDto);
        var ticket = ticketConverter.convertDtoToModel(ticketDto);

        var result = ticketService.saveTicket(ticket);

        var resultModel = ticketConverter.convertModelToDto(result);

        log.trace("addTicket - method finished: resultModel={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.PUT)
    TicketDto updateTicket(@PathVariable Long id,
                           @RequestBody TicketDto dto) {
        log.trace("updateTicket - method entered: ticketDto={}", dto);

        TicketDto ticketDto = ticketConverter.convertModelToDto(
                ticketService.updateTicket(
                        ticketConverter.convertDtoToModel(dto)
                ));
        log.trace("updateTicket - method finished: ticketDto={}", ticketDto);

        return ticketDto;
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        log.trace("deleteTicket - method entered: id={}", id);
        ticketService.deleteTicket(id);
        log.trace("deleteTicket - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/filterByPrice", method = RequestMethod.POST)
    List<TicketDto> filterTicketByPrice(@RequestBody Double numberToCompareWith) {
        log.trace("filterByPrice - method entered: numberToCompareWith={}", numberToCompareWith);

        List<TicketDto> ticketsDto = ticketConverter.convertModelsToDtos(
                        ticketService.filterTicketsByPrice(numberToCompareWith));

        log.trace("filterByPrice - method finished");
        return ticketsDto;
    }
}

