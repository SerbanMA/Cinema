package cinema.repository;//package cinema.repository;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.junit.jupiter.api.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ro.ubb.cinema.domain.entities.Cinema;
//import ro.ubb.cinema.domain.validators.CinemaValidator;
//import ro.ubb.cinema.domain.validators.Validator;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class CinemaJDBCRepositoryTests {
//    private static final Long ID1 = 10L;
//    private static final String NAME = "NAME";
//    private static final String ADDRESS = "ADDRESS";
//
//    private static final String UPDATED_NAME = "UPDATED_NAME";
//    private static final String UPDATED_ADDRESS = "UPDATED_ADDRESS";
//
//    private static Cinema CINEMA;
//    private static final Cinema UPDATED_CINEMA = new Cinema(ID1, UPDATED_NAME, UPDATED_ADDRESS, rooms);
//
//    private CinemaJDBCRepository repository;
//    private static final Validator<Cinema> validator = new CinemaValidator();
//
//
//    public JdbcTemplate initializeJDBCTemplate()
//    {
//        BasicDataSource dataSource=new BasicDataSource();
//        dataSource.setDriver(new org.postgresql.Driver());
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("powerpuffGirls");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/cinemaTests");
//        dataSource.setInitialSize(2);
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource);
//        return jdbcTemplate;
//    }
//
//    @BeforeAll
//    public void initialize() {
//        repository = new CinemaJDBCRepository(validator);
//        repository.setJdbcOperations(initializeJDBCTemplate());
//
//        CINEMA = new Cinema(ID1, NAME, ADDRESS, rooms);
//        CINEMA.setId(ID1);
//        UPDATED_CINEMA.setId(ID1);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        repository = null;
//    }
//
//    @Test
//    public void CinemaJDBCRepository_findAll_shouldReturnAllTheEntitiesFromJdbcRepository() {
//        //GIVEN
//        repository.save(CINEMA);
//        ArrayList<Cinema> myEntities = new ArrayList<>();
//        myEntities.add(CINEMA);
//
//        //WHEN
//        ArrayList<Cinema> allEntities = (ArrayList<Cinema>) repository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//
//        //AFTER
//        repository.delete(CINEMA.getId());
//    }
//
//    @Test
//    public void CinemaJDBCRepository_findOne_shouldThrowException() {
//        //WHEN
//        try {
//            repository.findOne(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "id must not be null");
//        }
//    }
//
//    @Test
//    public void CinemaJDBCRepository_save_shouldThrowException() {
//        //WHEN
//        try {
//            repository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Cinema must not be null");
//        }
//    }
//
//    @Test
//    public void CinemaJDBCRepository_save_shouldAddTheGivenEntityToTheJdbcRepository() {
//        //WHEN
//        repository.save(CINEMA);
//
//        //THEN
//        assertTrue(repository.findOne(ID1).isPresent());
//
//        //AFTER
//        repository.delete(CINEMA.getId());
//    }
//
//    @Test
//    public void CinemaJDBCRepository_delete_shouldDeleteTheEntityWithGivenIdFromJdbcRepository() {
//        //GIVEN
//        repository.save(CINEMA);
//        assertTrue(repository.findOne(ID1).isPresent());
//
//        //WHEN
//        repository.delete(CINEMA.getId());
//
//        //THEN
//        assertFalse(repository.findOne(ID1).isPresent());
//    }
//
//    @Test
//    public void CinemaJDBCRepository_delete_shouldThrowException() {
//        //WHEN
//        try {
//            repository.delete(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "id must not be null");
//        }
//    }
//
//    @Test
//    public void CinemaJDBCRepository_update_shouldUpdateTheGivenEntityToTheJdbcRepository() {
//        //GIVEN
//        repository.save(CINEMA);
//        assertTrue(repository.findOne(ID1).isPresent());
//
//        //WHEN
//        repository.update(UPDATED_CINEMA);
//
//        //THEN
//        assertTrue(repository.findOne(ID1).isPresent());
//
//        //AFTER
//        repository.delete(CINEMA.getId());
//    }
//
//    @Test
//    public void CinemaJDBCRepository_update_shouldThrowException() {
//        //WHEN
//        try {
//            repository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Cinema must not be null");
//        }
//    }
//
//
//}
