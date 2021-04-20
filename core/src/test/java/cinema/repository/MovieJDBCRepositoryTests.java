package cinema.repository;//package cinema.repository;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ro.ubb.cinema.domain.entities.Movie;
//import ro.ubb.cinema.domain.validators.MovieValidator;
//import ro.ubb.cinema.domain.validators.Validator;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class MovieJDBCRepositoryTests {
//    private static final Long ID = 1L;
//    private static final String NAME = "NAME";
//    private static final String GENRE = "GENRE";
//    private static final Integer DURATION = 120;
//
//    private static final String UPDATED_NAME = "UPDATED_NAME";
//    private static final String UPDATED_GENRE = "UPDATED_GENRE";
//    private static final Integer UPDATED_DURATION = 130;
//
//    private static final Movie MOVIE = new Movie(ID, NAME, DURATION, GENRE, tickets);
//    private static final Movie UPDATED_MOVIE = new Movie(ID, UPDATED_NAME, UPDATED_DURATION, UPDATED_GENRE, tickets);
//
//    private MovieJDBCRepository repository;
//    private static final Validator<Movie> validator = new MovieValidator();
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
//        repository = new MovieJDBCRepository(validator);
//        repository.setJdbcOperations(initializeJDBCTemplate());
//
//        MOVIE.setId(ID);
//        UPDATED_MOVIE.setId(ID);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        repository = null;
//    }
//
//    @Test
//    public void MovieJDBCRepository_findAll_shouldReturnAllTheEntitiesFromJdbcRepository() {
//        //GIVEN
//        repository.save(MOVIE);
//        ArrayList<Movie> myEntities = new ArrayList<>();
//        myEntities.add(MOVIE);
//
//        //WHEN
//        ArrayList<Movie> allEntities = (ArrayList<Movie>) repository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//
//        //AFTER
//        repository.delete(MOVIE.getId());
//    }
//
//    @Test
//    public void MovieJDBCRepository_findOne_shouldThrowException() {
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
//    public void MovieJDBCRepository_save_shouldThrowException() {
//        //WHEN
//        try {
//            repository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Movie must not be null");
//        }
//    }
//
//    @Test
//    public void MovieJDBCRepository_save_shouldAddTheGivenEntityToTheJdbcRepository() {
//        //WHEN
//        repository.save(MOVIE);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(MOVIE.getId());
//    }
//
//    @Test
//    public void MovieJDBCRepository_delete_shouldDeleteTheEntityWithGivenIdFromJdbcRepository() {
//        //GIVEN
//        repository.save(MOVIE);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.delete(MOVIE.getId());
//
//        //THEN
//        assertFalse(repository.findOne(ID).isPresent());
//    }
//
//    @Test
//    public void MovieJDBCRepository_delete_shouldThrowException() {
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
//    public void MovieJDBCRepository_update_shouldUpdateTheGivenEntityToTheJdbcRepository() {
//        //GIVEN
//        repository.save(MOVIE);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.update(UPDATED_MOVIE);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(MOVIE.getId());
//    }
//
//    @Test
//    public void MovieJDBCRepository_update_shouldThrowException() {
//        //WHEN
//        try {
//            repository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Movie must not be null");
//        }
//    }
//
//}
