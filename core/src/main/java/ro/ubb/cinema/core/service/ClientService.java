package ro.ubb.cinema.core.service;

import ro.ubb.cinema.core.domain.entities.Client;


import java.util.List;

public interface ClientService {
    /**
     * Returns all the clients.
     *
     * @return - List<Client>
     *         - the list with all the clients
     */
    List<Client> getAllClients();

    /**
     * Adds the given client to the list of clients.
     *
     * @param client: - Client
     *                - the client we want to add
     */
    Client saveClient(Client client);

    /**
     * Delete the given client from the list of clients.
     *
     * @param id: - integerr
     *                - the client's id we want to delete
     */
    void deleteClient(Long id);

    /**
     * Update the information of the given client in the list of clients.
     *
     * @param client: - Client
     *                - the client we want to update.
     */
    Client updateClient(Client client);


    /**
     * Returns all clients whose last name contain the given string.
     *
     * @param s - String
     *          - the string containing the Last Name we want to filter
     * @return filteredClients: - Set<Client>
     *                          - the list with filtered clients
     */

    List<Client> filterClientsByLastName(String s);
//
//    /**
//     * Increases the all ages with given integer
//     *
//     * @param increasingValue - Integer, the value we want to increase the ages with
//     */
//    void increaseClientsAgeWithGivenInteger(Integer increasingValue);
//
//    /**
//     * Returns the Client with the oldest age
//     *
//     * @return - Optional<Client>
//     */
//     Client getTheAgeOfOldestClient();
//
//    /**
//     * Check if the repository contains the entity with the given identifier
//     *
//     * @param identifier - long, the identifier of the client searched for
//     *
//     * @return - boolean
//     */
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the client that have the given identifier
//     *
//     * @param identifier - long, the identifier of the client searched for
//     *
//     * @return - client object
//     *
//     * @throws ArrayIndexOutOfBoundsException
//     *          if client not found
//     */
//    Client get(Long identifier) throws ArrayIndexOutOfBoundsException;
}
