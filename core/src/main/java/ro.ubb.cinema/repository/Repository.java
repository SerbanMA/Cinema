package ro.ubb.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.cinema.domain.entities.BaseEntity;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface Repository<ID extends Serializable, T extends BaseEntity<ID>> extends JpaRepository<T, ID> {
}
