package ro.ubb.cinema.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.cinema.domain.entities.Cinema;
import java.util.List;

public interface CinemaJDBCRepository extends Repository<Long, Cinema> {

    List<Cinema> getAllByNameContaining(String name);

//    @Query("select c from Cinema c where c.name = ?1")
//    List<Cinema> findByName(String name);
}
