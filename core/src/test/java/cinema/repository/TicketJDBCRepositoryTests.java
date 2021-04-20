package cinema.repository;//package cinema.repository;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ro.ubb.cinema.domain.entities.*;
//import ro.ubb.cinema.domain.validators.*;
//import ro.ubb.cinema.jdbcrepository.*;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TicketJDBCRepositoryTests {
//    public static final Long ID = 1L;
//    public static final Long MOVIE_ID = 1L;
//    public static final Long ROOM_ID = 12L;
//    public static final Long CLIENT_ID = 13L;
//    public static final Double PRICE = 99.99;
//    public static final Double UPDATED_PRICE = 20.00;
//    public static final Calendar CALENDAR = new GregorianCalendar(2020, Calendar.FEBRUARY, 1);
//
//    private static final String MOVIE_NAME = "NAME";
//    private static final String MOVIE_GENRE = "GENRE";
//    private static final Integer MOVIE_DURATION = 120;
//
//    private static final Long CINEMA_ID = 1L;
//    private static final String CINEMA_NAME = "NAME";
//    private static final String CINEMA_ADDRESS = "ADDRESS";
//
//    private static final Integer ROOM_FLOOR_NUMBER = 2;
//    private static final String ROOM_NAME = "NAME";
//    private static final Integer ROOM_NUMBER_SEATS = 50;
//
//    private static final String FIRST_NAME = "FIRST_NAME";
//    private static final String LAST_NAME = "LAST_NAME";
//    private static final String EMAIL = "EMAIL@gmail.com";
//    private static final Integer AGE = 20;
//
//    private static final Ticket TICKET = new Ticket(ID, MOVIE_ID, ROOM_ID, CLIENT_ID, PRICE, CALENDAR);
//    private static final Ticket UPDATED_TICKET = new Ticket(ID, MOVIE_ID, ROOM_ID, CLIENT_ID, UPDATED_PRICE, CALENDAR);
//    private static final Room ROOM = new Room(ROOM_ID, CINEMA_ID, ROOM_FLOOR_NUMBER, ROOM_NAME, ROOM_NUMBER_SEATS, tickets);
//    private static final Client CLIENT = new Client(CLIENT_ID, FIRST_NAME, LAST_NAME, EMAIL, AGE, tickets);
//    private static final Movie MOVIE = new Movie(MOVIE_ID, MOVIE_NAME, MOVIE_DURATION, MOVIE_GENRE, tickets);
//    private static final Cinema CINEMA = new Cinema(CINEMA_ID, CINEMA_NAME, CINEMA_ADDRESS, rooms);
//
//
//    private TicketJDBCRepository repository;
//    private RoomJDBCRepository roomRepository;
//    private MovieJDBCRepository movieRepository;
//    private ClientJDBCRepository clientRepository;
//    private CinemaJDBCRepository cinemaRepository;
//
//    private static final Validator<Room> roomValidator = new RoomValidator();
//    private static final Validator<Cinema> cinemaValidator = new CinemaValidator();
//    private static final Validator<Client> clientValidator = new ClientValidator();
//    private static final Validator<Movie> movieValidator = new MovieValidator();
//    private static final Validator<Ticket> ticketValidator = new TicketValidator();
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
//        repository = new TicketJDBCRepository(ticketValidator);
//        repository.setJdbcOperations(initializeJDBCTemplate());
//
//        clientRepository = new ClientJDBCRepository(clientValidator);
//        clientRepository.setJdbcOperations(initializeJDBCTemplate());
//
//        movieRepository = new MovieJDBCRepository(movieValidator);
//        movieRepository.setJdbcOperations(initializeJDBCTemplate());
//
//        cinemaRepository = new CinemaJDBCRepository(cinemaValidator);
//        cinemaRepository.setJdbcOperations(initializeJDBCTemplate());
//
//        roomRepository =  new RoomJDBCRepository(roomValidator);
//        roomRepository.setJdbcOperations(initializeJDBCTemplate());
//
//        TICKET.setId(ID);
//        UPDATED_TICKET.setId(ID);
//        CINEMA.setId(CINEMA_ID);
//        CLIENT.setId(CLIENT_ID);
//        MOVIE.setId(MOVIE_ID);
//        ROOM.setId(ROOM_ID);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        repository = null;
//    }
//
//    @Test
//    public void TicketJDBCRepository_findAll_shouldReturnAllTheEntitiesFromJdbcRepository() {
//        //GIVEN
//        clientRepository.save(CLIENT);
//        movieRepository.save(MOVIE);
//        cinemaRepository.save(CINEMA);
//        roomRepository.save(ROOM);
//        repository.save(TICKET);
//        ArrayList<Ticket> myEntities = new ArrayList<>();
//        myEntities.add(TICKET);
//
//        //WHEN
//        ArrayList<Ticket> allEntities = (ArrayList<Ticket>) repository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//
//        //AFTER
//        repository.delete(ID);
//        roomRepository.delete(ROOM_ID);
//        cinemaRepository.delete(CINEMA_ID);
//        clientRepository.delete(CLIENT_ID);
//        movieRepository.delete(MOVIE_ID);
//    }
//
//    @Test
//    public void TicketJDBCRepository_findOne_shouldThrowException() {
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
//    public void TicketJDBCRepository_save_shouldThrowException() {
//        //WHEN
//        try {
//            repository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Ticket must not be null");
//        }
//    }
//
//    @Test
//    public void TicketJDBCRepository_save_shouldAddTheGivenEntityToTheJdbcRepository() {
//        //WHEN
//        clientRepository.save(CLIENT);
//        movieRepository.save(MOVIE);
//        cinemaRepository.save(CINEMA);
//        roomRepository.save(ROOM);
//        repository.save(TICKET);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(ID);
//        roomRepository.delete(ROOM_ID);
//        cinemaRepository.delete(CINEMA_ID);
//        clientRepository.delete(CLIENT_ID);
//        movieRepository.delete(MOVIE_ID);
//    }
//
//    @Test
//    public void TicketJDBCRepository_delete_shouldDeleteTheEntityWithGivenIdFromJdbcRepository() {
//        //GIVEN
//        clientRepository.save(CLIENT);
//        movieRepository.save(MOVIE);
//        cinemaRepository.save(CINEMA);
//        roomRepository.save(ROOM);
//        repository.save(TICKET);
//
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.delete(ID);
//
//        //THEN
//        assertFalse(repository.findOne(ID).isPresent());
//
//        //AFTER
//        roomRepository.delete(ROOM_ID);
//        cinemaRepository.delete(CINEMA_ID);
//        clientRepository.delete(CLIENT_ID);
//        movieRepository.delete(MOVIE_ID);
//    }
//
//    @Test
//    public void TicketJDBCRepository_delete_shouldThrowException() {
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
//        clientRepository.save(CLIENT);
//        movieRepository.save(MOVIE);
//        cinemaRepository.save(CINEMA);
//        roomRepository.save(ROOM);
//        repository.save(TICKET);
//
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.update(UPDATED_TICKET);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(ID);
//        roomRepository.delete(ROOM_ID);
//        cinemaRepository.delete(CINEMA_ID);
//        clientRepository.delete(CLIENT_ID);
//        movieRepository.delete(MOVIE_ID);
//    }
//
//    @Test
//    public void TicketJDBCRepository_update_shouldThrowException() {
//        //WHEN
//        try {
//            repository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Ticket must not be null");
//        }
//    }
//
//}
