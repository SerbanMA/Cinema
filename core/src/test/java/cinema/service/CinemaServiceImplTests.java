package cinema.service;//package cinema.service;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ro.ubb.cinema.domain.entities.Cinema;
//import ro.ubb.cinema.repository.CinemaJDBCRepository;
//import ro.ubb.cinema.service.CinemaServiceImpl;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class CinemaServiceImplTests {
//    private static final Long IDENTIFIER = 9821L;
//    private static final String NAME = "CINEMA_NAME";
//    private static final String ADDRESS = "CINEMA_ADDRESS";
//    private static final Cinema CINEMA1 = new Cinema(IDENTIFIER, NAME, ADDRESS);
//
//    private static final Long IDENTIFIER2 = 9823L;
//    private static final String NAME2 = "CINEMA_NAME2";
//    private static final String ADDRESS2 = "CINEMA_ADDRESS2";
//    private static final Cinema CINEMA2 = new Cinema(IDENTIFIER2, NAME2, ADDRESS2);
//
//    private static final String NEW_NAME = "NEW_C2EM1_NAME";
//    private static final String NEW_ADDRESS = "NEW_CINEMA_ADDRESS";
//    private static final Cinema CINEMA_UPDATED = new Cinema(IDENTIFIER, NEW_NAME, NEW_ADDRESS);
//
//    private static final String FILTER_STRING = "NAME2";
//
//    @InjectMocks
//    private CinemaServiceImpl cinemaServiceImpl;
//
//    @Mock
//    CinemaJDBCRepository repository;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void CinemaService_addCinema_shouldAddCinemaToRepository() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.empty()).thenReturn(Optional.of(CINEMA1));
//
//        //WHEN
//        cinemaServiceImpl.addCinema(CINEMA1);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void CinemaService_deleteCinema_shouldDeleteCinemaFromRepository() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(CINEMA1)).thenReturn(Optional.empty());
//
//        //WHEN
//        cinemaServiceImpl.deleteCinema(CINEMA1);
//
//        //THEN
//        assertFalse(repository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void CinemaService_updateCinema_shouldUpdateCinemaFromRepository() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(CINEMA_UPDATED));
//
//        //WHEN
//        cinemaServiceImpl.updateCinema(CINEMA_UPDATED);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void CinemaService_filterCinemasByName_shouldFilterCinemasBasedOnAString() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CINEMA1);
//                add(CINEMA2);
//            }
//        });
//
//        //WHEN
//        List<Cinema> filteredCinemas = cinemaServiceImpl.filterCinemaByName(FILTER_STRING);
//
//        //THEN
//        assertTrue(filteredCinemas.contains(CINEMA2));
//        assertFalse(filteredCinemas.contains(CINEMA1));
//    }
//
//    @Test
//    public void CinemaService_getAllCinemas_shouldReturnAllTheCinemas() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CINEMA1);
//                add(CINEMA2);
//            }
//        });
//
//        Set<Cinema> myCinemas = new HashSet<>();
//        myCinemas.add(CINEMA1);
//        myCinemas.add(CINEMA2);
//
//        //WHEN
//        Set<Cinema> allCinemas = cinemaServiceImpl.getAllCinemas();
//
//        //THEN
//        assertEquals(myCinemas,allCinemas);
//    }
//
//    @Test
//    public void CinemaService_containsOne_shouldReturnTrue() {
//        //WHEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(CINEMA1));
//
//        //THEN
//        assertTrue(cinemaServiceImpl.containsOne(CINEMA1.getId()));
//    }
//
//    @Test
//    public void CinemaService_get_shouldReturnTheCinemaWithId9821L() {
//        //WHEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(CINEMA1));
//
//        //THEN
//        assertEquals(CINEMA1, cinemaServiceImpl.get(9821L));
//    }
//
//    @Test
//    public void CinemaService_deleteCinemaByAddress_shouldDeleteCinemasWithGivenProperty() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CINEMA1);
//                add(CINEMA2);
//            }
//        });
//
//        //WHEN
//        cinemaServiceImpl.deleteCinemaByAddress(CINEMA1.getAddress());
//
//        //THEN
//        assertFalse(cinemaServiceImpl.containsOne(CINEMA1.getId()));
//    }
//
//    @Test
//    public void CinemaService_addCinema_duplicateCinemas_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(CINEMA1.getId())).thenReturn(Optional.of(CINEMA1));
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> cinemaServiceImpl.addCinema(CINEMA1));
//    }
//
//    @Test
//    public void CinemaService_deleteCinema_inexistentCinema_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(CINEMA1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> cinemaServiceImpl.deleteCinema(CINEMA1));
//    }
//
//    @Test
//    public void CinemaService_get_whenNotFound_shouldThrowArrayIndexOutOfBoundsException() {
//        //GIVEN
//        when(repository.findById(CINEMA1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> cinemaServiceImpl.get(IDENTIFIER));
//    }
//
//}
