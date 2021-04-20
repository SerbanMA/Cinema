package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.service.ClientService;
import ro.ubb.cinema.web.converter.ClientConverter;
import ro.ubb.cinema.web.dto.CinemasDto;
import ro.ubb.cinema.web.dto.ClientDto;
import ro.ubb.cinema.web.dto.ClientsDto;

@RestController
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients")
    ClientsDto getAllClients() {
        log.trace("getAllClients - method entered");

        ClientsDto clientsDto = new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.getAllClients()));

        log.trace("getAllClients - method finished: clientsDto={}", clientsDto);

        return clientsDto;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto clientDto){
        log.trace("addClient - method entered: clientDto={}", clientDto);
        var client = clientConverter.convertDtoToModel(clientDto);

        var result = clientService.saveClient(client);

        var resultModel = clientConverter.convertModelToDto(result);

        log.trace("addClient - method finished: resultModel={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id,
                           @RequestBody ClientDto dto) {
        log.trace("updateClient - method entered: clientDto={}", dto);

        ClientDto clientDto = clientConverter.convertModelToDto(
                clientService.updateClient(
                        clientConverter.convertDtoToModel(dto)
                ));

        log.trace("updateClient - method finished: clientDto={}", clientDto);

        return clientDto;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.trace("deleteClient - method entered: id={}", id);
        clientService.deleteClient(id);
        log.trace("deleteClient - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/filterByLastName", method = RequestMethod.POST)
    ClientsDto filterCinemaByName(@RequestBody String name) {
        log.trace("filterClientByLastName - method entered: name={}", name);

        ClientsDto clientsDto = new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByLastName(name)));

        log.trace("filterByLastName - method finished");
        return clientsDto;
    }

}
