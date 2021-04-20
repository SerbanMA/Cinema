package cinema.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubb.cinema.domain.entities.BaseEntity;
import ro.ubb.cinema.domain.validators.BaseValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseEntityTest {
    private BaseEntity<Long> entity;
    private final BaseValidator<Long> baseValidator = new BaseValidator<>();

    @BeforeEach
    public void setUp() throws RuntimeException {
        entity=new BaseEntity<>();
        Long IDENTIFIER = 2L;
        entity.setId(IDENTIFIER);
    }

    @Test
    void BaseEntity_ToString_shouldReturnGivenFormatOfBaseEntity() {
        assertEquals("BaseEntity{id=2}",entity.toString());
    }
}