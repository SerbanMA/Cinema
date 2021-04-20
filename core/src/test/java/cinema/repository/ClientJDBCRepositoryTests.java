package cinema.repository;//package cinema.repository;
//
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.junit.jupiter.api.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import ro.ubb.cinema.domain.entities.Client;
//import ro.ubb.cinema.domain.validators.ClientValidator;
//import ro.ubb.cinema.domain.validators.Validator;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ClientJDBCRepositoryTests {
//    private static final Long ID = 1L;
//    private static final String FIRST_NAME = "FIRST_NAME";
//    private static final String LAST_NAME = "LAST_NAME";
//    private static final String EMAIL = "EMAIL@gmail.com";
//    private static final Integer AGE = 20;
//
//    private static final String UPDATED_FIRST_NAME = "UPDATED_FIRST_NAME";
//    private static final String UPDATED_LAST_NAME = "UPDATED_LAST_NAME";
//    private static final String UPDATED_EMAIL = "UPDATED_EMAIL@gmail.com";
//    private static final Integer UPDATED_AGE = 30;
//
//    private static final Client CLIENT = new Client(ID, FIRST_NAME, LAST_NAME, EMAIL, AGE, tickets);
//    private static final Client UPDATED_CLIENT = new Client(ID, UPDATED_FIRST_NAME, UPDATED_LAST_NAME, UPDATED_EMAIL, UPDATED_AGE, tickets);
//
//    private ClientJDBCRepository repository;
//    private static final Validator<Client> validator = new ClientValidator();
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
//        repository = new ClientJDBCRepository(validator);
//        repository.setJdbcOperations(initializeJDBCTemplate());
//
//        CLIENT.setId(ID);
//        UPDATED_CLIENT.setId(ID);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        repository = null;
//    }
//
//    @Test
//    public void ClientJDBCRepository_findAll_shouldReturnAllTheEntitiesFromJdbcRepository() {
//        //GIVEN
//        repository.save(CLIENT);
//        ArrayList<Client> myEntities = new ArrayList<>();
//        myEntities.add(CLIENT);
//
//        //WHEN
//        ArrayList<Client> allEntities = (ArrayList<Client>) repository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//
//        //AFTER
//        repository.delete(CLIENT.getId());
//    }
//
//    @Test
//    public void ClientJDBCRepository_findOne_shouldThrowException() {
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
//    public void ClientJDBCRepository_save_shouldThrowException() {
//        //WHEN
//        try {
//            repository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Client must not be null");
//        }
//    }
//
//    @Test
//    public void ClientJDBCRepository_save_shouldAddTheGivenEntityToTheJdbcRepository() {
//        //WHEN
//        repository.save(CLIENT);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(CLIENT.getId());
//    }
//
//    @Test
//    public void ClientJDBCRepository_delete_shouldDeleteTheEntityWithGivenIdFromJdbcRepository() {
//        //GIVEN
//        repository.save(CLIENT);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.delete(CLIENT.getId());
//
//        //THEN
//        assertFalse(repository.findOne(ID).isPresent());
//    }
//
//    @Test
//    public void ClientJDBCRepository_delete_shouldThrowException() {
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
//    public void ClientJDBCRepository_update_shouldUpdateTheGivenEntityToTheJdbcRepository() {
//        //GIVEN
//        repository.save(CLIENT);
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //WHEN
//        repository.update(UPDATED_CLIENT);
//
//        //THEN
//        assertTrue(repository.findOne(ID).isPresent());
//
//        //AFTER
//        repository.delete(CLIENT.getId());
//    }
//
//    @Test
//    public void ClientJDBCRepository_update_shouldThrowException() {
//        //WHEN
//        try {
//            repository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertEquals(ex.getMessage(), "Client must not be null");
//        }
//    }
//
//
//
//
//}
