//package cinema.repository;
//
//import org.junit.jupiter.api.*;
//import ro.ubb.cinema.domain.entities.BaseEntity;
//import ro.ubb.cinema.domain.validators.BaseValidator;
//import ro.ubb.cinema.domain.validators.Validator;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class InMemoryRepositoryTests {
//    private static final Long ID1 = 10L;
//    private static final Long ID2 = 20L;
//
//    private static final BaseEntity<Long> ENTITY1 = new BaseEntity<>();
//    private static final BaseEntity<Long> ENTITY2 = new BaseEntity<>();
//
//    private InMemoryRepository<Long, BaseEntity<Long>> inMemoryRepository;
//    private static final Validator<BaseEntity<Long>> validator = new BaseValidator<>();
//
//    @BeforeAll
//    public void initialize() {
//        ENTITY1.setId(ID1);
//        ENTITY2.setId(ID2);
//    }
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        inMemoryRepository = new InMemoryRepository<>(validator);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        inMemoryRepository = null;
//    }
//
//    @Test
//    public void InMemoryRepository_findOne_shouldReturnTheEntityWithGivenIdFromInMemoryRepository() {
//        //GIVEN
//        inMemoryRepository.delete(ENTITY1.getId());
//
//        //WHEN
//        inMemoryRepository.save(ENTITY1);
//
//        //THEN
//        assertTrue(inMemoryRepository.findOne(ID1).isPresent());
//    }
//
//    @Test
//    public void InMemoryRepository_findOne_shouldThrowException() {
//        //GIVEN
//        inMemoryRepository.delete(ENTITY1.getId());
//
//        //WHEN
//        inMemoryRepository.save(ENTITY1);
//
//        //THEN
//        try {
//            inMemoryRepository.findOne(null);
//        }catch (IllegalArgumentException ex)
//        {
//            assertFalse(ex.getMessage().isBlank());
//        }
//    }
//
//    @Test
//    public void InMemoryRepository_findAll_shouldReturnAllTheEntitiesFromInMemoryRepository() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//        inMemoryRepository.save(ENTITY1);
//        inMemoryRepository.save(ENTITY2);
//
//        Set<BaseEntity<Long>> myEntities = new HashSet<>();
//        myEntities.add(ENTITY1);
//        myEntities.add(ENTITY2);
//
//        //WHEN
//        Set<BaseEntity<Long>> allEntities = (Set<BaseEntity<Long>>) inMemoryRepository.findAll();
//
//        //THEN
//        assertEquals(myEntities, allEntities);
//    }
//
//    @Test
//    public void InMemoryRepository_save_shouldAddGivenEntityToTheInMemoryRepository() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//
//        //WHEN
//        inMemoryRepository.save(ENTITY1);
//
//        //THEN
//        assertTrue(inMemoryRepository.findOne(ID1).isPresent());
//    }
//
//    @Test
//    public void InMemoryRepository_save_shouldThrowException() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//
//        //WHEN
//        try {
//            inMemoryRepository.save(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex) {
//            assertFalse(ex.getMessage().isBlank());
//        }
//    }
//
//    @Test
//    public void InMemoryRepository_delete_shouldThrowException() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//        inMemoryRepository.save(ENTITY1);
//
//        //WHEN
//        try {
//            inMemoryRepository.delete(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex)
//        {
//            assertFalse(ex.getMessage().isBlank());
//        }
//    }
//
//    @Test
//    public void InMemoryRepository_delete_shouldDeleteGivenEntityFromTheInMemoryRepository() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//        inMemoryRepository.save(ENTITY1);
//
//        //WHEN
//        inMemoryRepository.delete(ENTITY1.getId());
//
//        //THEN
//        assertFalse(inMemoryRepository.findOne(ID1).isPresent());
//    }
//
//    @Test
//    public void InMemoryRepository_update_shouldUpdateGivenEntityInTheInMemoryRepository() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//        inMemoryRepository.save(ENTITY1);
//
//        //WHEN
//        inMemoryRepository.update(ENTITY1);
//
//        //THEN
//        assertTrue(inMemoryRepository.findOne(ID1).isPresent());
//    }
//
//    @Test
//    public void InMemoryRepository_update_shouldThrowException() {
//        //GIVEN
//        inMemoryRepository = new InMemoryRepository<>(validator);
//        inMemoryRepository.save(ENTITY1);
//
//        //WHEN
//        try {
//            inMemoryRepository.update(null);
//        }
//        //THEN
//        catch (IllegalArgumentException ex)
//        {
//            assertFalse(ex.getMessage().isBlank());
//        }
//    }
//
//}
