package ro.ubb.cinema.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.core.repository.ClientJDBCRepository;
import ro.ubb.cinema.core.domain.entities.Client;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

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

    @Override
    public Client saveClient(Client client) {
        log.trace("addClient - method entered: client={}", client);
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
        Client updateClient = repository.findById(client.getId()).orElseThrow();
        updateClient.setFirstName(client.getFirstName());
        updateClient.setLastName(client.getLastName());
        updateClient.setEmail(client.getEmail());
        updateClient.setIdCard(client.getIdCard());
        log.trace("updateClient - method finished");

        return client;
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");

        List<Client> clients = repository.findAll();
        clients.sort(Comparator.comparing(Client::getId));

        log.trace("getAllClients - method finished: clients={}", clients);

        return clients;
    }

    @Override
    public List<Client> filterClientsByLastName(String nameToFilter) {
        log.trace("filterClientsByLastName - method entered: lastNameToFilter={}", nameToFilter);

        List<Client> filteredClients = repository.getAllByLastName(nameToFilter);

        log.trace("filterClientsByLastName - method finished: filteredClients={}", filteredClients);

        return filteredClients;
    }
}
