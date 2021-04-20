//package cinema.repository;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ro.ubb.cinema.domain.entities.Cinema;
//import ro.ubb.cinema.domain.entities.Room;
//import ro.ubb.cinema.domain.validators.CinemaValidator;
//import ro.ubb.cinema.domain.validators.RoomValidator;
//import ro.ubb.cinema.domain.validators.Validator;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class RoomJDBCRepositoryTests {
//    private static final Long ID = 1L;
//    private static final Long CINEMA_ID = 1L;
//    private static final Integer FLOOR_NUMBER = 2;
//    private static final String NAME = "NAME";
//    private static final Integer NUMBER_SEATS = 50;
//    private static final String UPDATED_NAME = "UPDATED_NAME";
//
//    private static final String CINEMA_NAME = "NAME";
//    private static final String CINEMA_ADDRESS = "ADDRESS";
//
//    private static final Cinema CINEMA = new Cinema(CINEMA_ID, CINEMA_NAME, CINEMA_ADDRESS, rooms);
//    private static final Room ROOM = new Room(ID, CINEMA_ID, FLOOR_NUMBER, NAME, NUMBER_SEATS, tickets);
//    private static final Room UPDATED_ROOM = new Room(ID, CINEMA_ID, FLOOR_NUMBER, UPDATED_NAME, NUMBER_SEATS, tickets);
//
//    private RoomJDBCRepository repository;
//    private CinemaJDBCRepository cinemaRepository;
//    private static final Validator<Room> validator = new RoomValidator();
//    private static final Validator<Cinema> cinemaValidator = new CinemaValidator();
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
//        repository = new RoomJDBCRepository(validator);
//        repository.setJdbcOperations(initializeJDBCTemplate());
//
//        cinemaRepository = new CinemaJDBCRepository(cinemaValidator);
//        cinemaRepository.setJdbcOperations(initializeJDBCTemplate());
//
//        ROOM.setId(ID);
//        UPDATED_ROOM.setId(ID);
//        CINEMA.setId(CINEMA_ID);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        repository = null;
//    }
//
//    @Test
//    public void RoomJDBCRepository_findAll_shouldReturnAllTheEntitiesFromJdbcRepository() {
//        //GIVEN
//        cinemaRepository.save(CINEMA);
//        repository.save(ROOM);
//        ArrayList<Room> myEntities = new ArrayList<>();
//        myEntities.add(ROOM);
//
//        //WHEN
//        ArrayList<Room> allEntities = (ArrayList<Room>) repository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//
//        //AFTER
//        repository.delete(ROOM.getId());
//        cinemaRepository.delete(CINEMA_ID);
//    }
//
//    @Test
//    public void RoomJDBCRepository_findOne_shouldThrowException() {
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
//    public void RoomJDBCRepository_save_shouldThrowException() {
//        //WHEN
//        try {
//            repository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Room must not be null");
//        }
//    }
//
//    @Test
//    public void RoomJDBCRepository_save_shouldAddTheGivenEntityToTheJdbcRepository() {
//        //WHEN
//        cinemaRepository.save(CINEMA);
//        repository.save(ROOM);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(ROOM.getId());
//        cinemaRepository.delete(CINEMA_ID);
//    }
//
//    @Test
//    public void RoomJDBCRepository_delete_shouldDeleteTheEntityWithGivenIdFromJdbcRepository() {
//        //GIVEN
//        cinemaRepository.save(CINEMA);
//        repository.save(ROOM);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.delete(ROOM.getId());
//
//        //THEN
//        assertFalse(repository.findOne(ID).isPresent());
//
//        //AFTER
//        cinemaRepository.delete(CINEMA_ID);
//    }
//
//    @Test
//    public void RoomJDBCRepository_delete_shouldThrowException() {
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
//    public void RoomJDBCRepository_update_shouldUpdateTheGivenEntityToTheJdbcRepository() {
//        //GIVEN
//        cinemaRepository.save(CINEMA);
//        repository.save(ROOM);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.update(UPDATED_ROOM);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(ROOM.getId());
//        cinemaRepository.delete(CINEMA_ID);
//    }
//
//    @Test
//    public void RoomJDBCRepository_update_shouldThrowException() {
//        //WHEN
//        try {
//            repository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Room must not be null");
//        }
//    }
//
//}
