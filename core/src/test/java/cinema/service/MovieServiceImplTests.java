//package cinema.service;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ro.ubb.cinema.domain._exceptions.CinemaBaseException;
//import ro.ubb.cinema.domain.entities.Movie;
//import ro.ubb.cinema.repository.MovieJDBCRepository;
//import ro.ubb.cinema.service.MovieServiceImpl;
//import ro.ubb.cinema.service._exceptions.ServiceValidatorException;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class MovieServiceImplTests {
//    private static final Long IDENTIFIER = 1000L;
//    private static final String NAME = "NAME";
//    private static final Integer DURATION = 100;
//    private static final String GENRE = "GENRE";
//    private static final Movie MOVIE = new Movie(IDENTIFIER, NAME, DURATION, GENRE);
//
//    private static final Long IDENTIFIER2 = 2000L;
//    private static final String NAME2 = "NAME_2";
//    private static final Integer DURATION2 = 200;
//    private static final String GENRE2 = "NEW_GENRE";
//    private static final Movie MOVIE2 = new Movie(IDENTIFIER2, NAME2, DURATION2, GENRE2);
//
//    private static final String NAME_UPDATED = "NAME_UPDATED";
//    private static final Integer DURATION_UPDATED = 200;
//    private static final String GENRE_UPDATED = "GENRE_UPDATED";
//    private static final Movie MOVIE_UPDATED = new Movie(IDENTIFIER, NAME_UPDATED, DURATION_UPDATED, GENRE_UPDATED);
//
//    private static final String FILTERED_STRING = "NAME_2";
//    private static final String FILTERED_GENRE = "NEW_GENRE";
//
//    @InjectMocks
//    private MovieServiceImpl movieServiceImpl;
//
//    @Mock
//    MovieJDBCRepository repository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void MovieService_addMovie_shouldAddMovieToRepository(){
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.empty()).thenReturn(Optional.of(MOVIE));
//
//        //WHEN
//        movieServiceImpl.addMovie(MOVIE);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER).isPresent());
//
//    }
//
//    @Test
//    public void MovieService_deleteMovie_shouldDeleteMovieFromRepository() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(MOVIE)).thenReturn(Optional.empty());
//
//        //WHEN
//        movieServiceImpl.deleteMovie(MOVIE);
//
//        //THEN
//        assertFalse(repository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void MovieService_updateMovie_shouldDeleteMovieFromRepository() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(MOVIE_UPDATED));
//
//        //WHEN
//        movieServiceImpl.updateMovie(MOVIE_UPDATED);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER).isPresent());
//        assertEquals(MOVIE_UPDATED, repository.findById(IDENTIFIER).get());
//
//    }
//
//    @Test
//    public void MovieService_filterMoviesByName_shouldFilterMoviesBasedOnAString() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(MOVIE);
//                add(MOVIE2);
//            }
//        });
//
//        //WHEN
//        Set<Movie> filteredMovies = movieServiceImpl.filterMoviesByName(FILTERED_STRING);
//
//        //THEN
//        assertTrue(filteredMovies.contains(MOVIE2));
//        assertFalse(filteredMovies.contains(MOVIE));
//    }
//
//    @Test
//    public void MovieService_filterMoviesByGenre_shouldFilterMoviesBasedOnAString() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(MOVIE);
//                add(MOVIE2);
//            }
//        });
//
//        //WHEN
//        Set<Movie> filteredMovies = movieServiceImpl.filterMoviesByGenre(FILTERED_GENRE);
//
//        //THEN
//        assertTrue(filteredMovies.contains(MOVIE2));
//        assertFalse(filteredMovies.contains(MOVIE));
//    }
//
//    @Test
//    public void MovieService_getLongestMovies_shouldReturnASetWithAllLongestMovies() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(MOVIE);
//                add(MOVIE2);
//            }
//        });
//
//        //WHEN
//        Set<Movie> longestMovies = movieServiceImpl.getLongestMovies();
//
//        //THEN
//        assertTrue(longestMovies.contains(MOVIE2));
//        assertFalse(longestMovies.contains(MOVIE));
//    }
//
//    @Test
//    public void MovieService_getAllMovies_shouldReturnAllTheMovies() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(MOVIE);
//                add(MOVIE2);
//            }
//        });
//
//        Set<Movie> myCinemas = new HashSet<>();
//        myCinemas.add(MOVIE);
//        myCinemas.add(MOVIE2);
//
//        //WHEN
//        Set<Movie> allMovies = movieServiceImpl.getAllMovies();
//
//        //THEN
//        assertEquals(myCinemas,allMovies);
//    }
//
//    @Test
//    public void MovieService_containsOne_shouldReturnTrue() {
//        //GIVEN
//            when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(MOVIE));
//
//        //WHEN + THEN
//        assertTrue(movieServiceImpl.containsOne(IDENTIFIER));
//    }
//
//    @Test
//    public void MovieService_get_whenFound_shouldReturnTheMovieWithGivenIdSentAsParameter() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.of(MOVIE));
//
//        //WHEN + THEN
//        assertEquals(MOVIE, movieServiceImpl.get(IDENTIFIER));
//    }
//
//    @Test
//    public void MovieService_get_whenNotFound_shouldThrowArrayIndexOutOfBoundsException() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER)).thenReturn(Optional.empty());
//
//        //WHEN
//        Exception thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> movieServiceImpl.get(IDENTIFIER));
//
//        String expectedMessage = "Movie not found";
//        String actualMessage = thrownException.getMessage();
//
//        //THEN
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    public void MovieService_addMovie_duplicateMovie_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(MOVIE.getId())).thenReturn(Optional.of(MOVIE));
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> movieServiceImpl.addMovie(MOVIE));
//    }
//
//    @Test
//    public void MovieService_updateMovie_inexistentMovie_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(MOVIE.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> movieServiceImpl.updateMovie(MOVIE));
//    }
//
//    @Test
//    public void MovieService_deleteMovie_inexistentMovie_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(MOVIE.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> movieServiceImpl.deleteMovie(MOVIE));
//    }
//
//}
