package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.validators.ClientValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.ClientJDBCRepository;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Service for managing Clients
 *
 * @author fiamardar
 */

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientJDBCRepository repository;

    private final ClientValidator clientValidator = new ClientValidator();

    @Override
    public Client saveClient(Client client) throws ValidatorException {
        log.trace("addClient - method entered: client={}", client);
        clientValidator.validate(client);
        Client returnedClient = repository.save(client);
        log.trace("addClient - method finished");
        return returnedClient;
    }

    @Override
    public void deleteClient(Long id) {
        log.trace("deleteClient - method entered: clientId={}", id);

        repository.deleteById(id);
        log.trace("deleteClient - method finished");
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        log.trace("updateClient - method entered: client={}", client);
        clientValidator.validate(client);
        Client updateClient = repository.findById(client.getId()).orElseThrow();
        updateClient.setClientFirstName(client.getClientFirstName());
        updateClient.setClientLastName(client.getClientLastName());
        updateClient.setClientEmail(client.getClientEmail());
        updateClient.setClientAge(client.getClientAge());
        log.trace("updateClient - method finished");

        return client;
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");

        List<Client> clients = repository.findAll();

        log.trace("getAllClients - method finished: clients={}", clients);

        return clients;
    }

    @Override
    public List<Client> filterClientsByLastName(String lastNameToFilter) {
        log.trace("filterClientsByLastName - method entered: lastNameToFilter={}", lastNameToFilter);

        List<Client> filteredClients = repository.getAllByClientLastName(lastNameToFilter);

        log.trace("filterClientsByLastName - method finished: filteredClients={}", filteredClients);

        return filteredClients;
    }
//
//    @Override
//    @Transactional
//    public void increaseClientsAgeWithGivenInteger(Integer increasingValue) {
//        log.trace("increaseClientsAgeWithGivenInteger - method entered: increasingValue={}", increasingValue);
//
//        Iterable<Client> allClients = repository.findAll();
//        log.trace("increaseClientsAgeWithGivenInteger - allClients={}", allClients);
//
//        allClients.forEach(client -> repository.findById(client.getId())
//                .ifPresentOrElse(s -> s.setClientAge(client.getClientAge()+increasingValue), () -> { throw new CinemaBaseException("Non-existent client with given ID!");}));
//
//        log.trace("increaseClientsAgeWithGivenInteger - method finished");
//    }
//
//    @Override
//    public Client getTheAgeOfOldestClient() {
//        log.trace("getTheAgeOfOldestClient - method entered");
//
//        Iterable<Client> allClients = repository.findAll();
//        Set<Client> clients = new HashSet<>();
//        allClients.forEach(clients::add);
//        Optional<Client> result =  clients.stream().max(Comparator.comparing(Client::getClientAge));
//
//        log.trace("getTheAgeOfOldestClient - method finished");
//
//        return result.orElse(null);
//    }
//
//    @Override
//    public Boolean containsOne(Long identifier)
//    {
//        log.trace("containsOne - method entered");
//
//        Boolean result = this.repository.findById(identifier).isPresent();
//
//        log.trace("containsOne - method finished: result={}", result);
//
//        return result;
//    }
//
//    @Override
//    public Client get(Long identifier){
//        log.trace("get - method entered: identifier={}", identifier);
//
//        Optional<Client> client = this.repository.findById(identifier);
//        if (client.isPresent())
//        {
//            log.trace("get - method finished");
//            return client.get();
//        }
//        else
//        {
//            log.trace("get - exception found");
//            throw new ArrayIndexOutOfBoundsException("Client not found");
//        }
//
//    }
}
