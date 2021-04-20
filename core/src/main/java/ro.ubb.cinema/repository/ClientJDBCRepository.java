package ro.ubb.cinema.repository;

import ro.ubb.cinema.domain.entities.Client;

import java.util.List;

public interface ClientJDBCRepository extends Repository<Long, Client> {

    List<Client> getAllByClientLastName(String name);
}
