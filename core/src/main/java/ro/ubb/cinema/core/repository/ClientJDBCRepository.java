package ro.ubb.cinema.core.repository;

import ro.ubb.cinema.core.domain.entities.Client;

import java.util.List;

public interface ClientJDBCRepository extends Repository<Long, Client> {

    List<Client> getAllByLastName(String name);
}
