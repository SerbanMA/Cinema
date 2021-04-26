package ro.ubb.cinema.core.repository;

import ro.ubb.cinema.core.domain.entities.Cinema;

import java.util.List;

public interface CinemaJDBCRepository extends Repository<Long, Cinema> {

    List<Cinema> getAllByNameContaining(String name);

    List<Cinema> getAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);

//    @Query("select c from Cinema c where c.name = ?1")
//    List<Cinema> findByName(String name);
}
