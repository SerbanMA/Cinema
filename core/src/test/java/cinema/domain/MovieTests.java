//package cinema.domain;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import ro.ubb.cinema.domain.entities.Movie;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class MovieTests {
//    private static final Long IDENTIFIER = 1000L;
//    private static final String NAME = "NAME";
//    private static final Integer DURATION = 100;
//    private static final String GENRE = "GENRE";
//
//    private static final Long NEW_IDENTIFIER = 2000L;
//    private static final String NEW_NAME = "NEW_NAME";
//    private static final Integer NEW_DURATION = 200;
//    private static final String NEW_GENRE = "NEW_GENRE";
//
//    private Movie movie;
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        movie = new Movie(IDENTIFIER, NAME, DURATION, GENRE);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        movie = null;
//    }
//
//    @Test
//    public void Movie_getName_shouldReturnNameOfTheMovie() {
//        assertEquals(NAME, movie.getName());
//    }
//
//    @Test
//    public void Movie_getIdentifier_shouldReturnTheIdentifier() {
//        assertEquals(IDENTIFIER, movie.getId());
//    }
//
//    @Test
//    public void Movie_getDuration_shouldReturnTheDurationOfTheMovie() {
//        assertEquals(DURATION, movie.getDuration());
//    }
//
//    @Test
//    public void Movie_getGenre_shouldReturnTheGenreOfTheMovie() {
//        assertEquals(GENRE, movie.getGenre());
//    }
//
//    @Test
//    public void Movie_setName_shouldChangeTheNameOfTheMovie() {
//        //WHEN
//        movie.setName(NEW_NAME);
//
//        //THEN
//        assertEquals(NEW_NAME, movie.getName());
//    }
//
//    @Test
//    public void Movie_setIdentifier_shouldChangeTheIdentifierOfTheMovie() {
//        //WHEN
//        movie.setId(NEW_IDENTIFIER);
//
//        //THEN
//        assertEquals(NEW_IDENTIFIER, movie.getId());
//    }
//
//    @Test
//    public void Movie_setDuration_shouldChangeTheDurationOfTheMovie() {
//        //WHEN
//        movie.setDuration(NEW_DURATION);
//
//        //THEN
//        assertEquals(NEW_DURATION, movie.getDuration());
//    }
//
//    @Test
//    public void Movie_setName_shouldChangeTheGenreOfTheMovie() {
//        //WHEN
//        movie.setGenre(NEW_GENRE);
//
//        //THEN
//        assertEquals(NEW_GENRE, movie.getGenre());
//    }
//
//
//    @Test
//    public void Movie_toString_shouldReturnGivenFormatOfMovie() {
//        assertEquals("Movie{identifier=1000, name='NAME', duration=100, genre='GENRE'}", movie.toString());
//    }
//
//    @Test
//    public void Movie_constructorByID_shouldReturnEntityWithGivenIdAndNullFields() {
//        //WHEN
//        Movie movie2 = new Movie(NEW_IDENTIFIER);
//
//        //THEN
//        assertEquals(NEW_IDENTIFIER, movie2.getId());
//        assertNull(movie2.getName());
//        assertNull(movie2.getDuration());
//        assertNull(movie2.getGenre());
//    }
//
//    @Test
//    public void Movie_equals_shouldReturnIfEntitiesAreEqualOrNot()
//    {
//        //WHEN
//        Movie movie2 = new Movie(IDENTIFIER,NAME,DURATION,GENRE);
//
//        //THEN
//        assertEquals(movie2, movie);
//        assertNotEquals(movie2,null);
//    }
//
//    @Test
//    public void Movie_emptyConstructor_shouldReturnEntityWithEmptyFields()
//    {
//        //WHEN
//        Movie MOVIE= new Movie();
//
//        //THEN
//        assertNull(MOVIE.getId());
//        assertNull(MOVIE.getGenre());
//        assertNull(MOVIE.getDuration());
//        assertNull(MOVIE.getName());
//    }
//}

//    @Test
//    public void Movie_WriteToFileFormat_shouldReturnGivenFormatOfMovie() {
//        assertEquals("1000,NAME,100,GENRE",movie.writeToFileFormat());
//    }
//
//    @Test
//    public void Movie_loadEntity_shouldReturnAMovieEntityBasedOnGivenListOfStrings(){
//        //WHEN
//        List<String> FIELDS = new ArrayList<>();
//        FIELDS.add("1000");
//        FIELDS.add("NAME");
//        FIELDS.add("100");
//        FIELDS.add("GENRE");
//
//        //THEN
//        Movie movie2 = new Movie(IDENTIFIER,NAME,DURATION,GENRE);
//        assertEquals(movie2, Movie.loadEntity(FIELDS));
//    }
//
//    @Test
//    public void Movie_loadEntityXml_shouldReturnAMovieEntityBasedOnGivenElement() {
//        try {
//            //WHEN
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse("./data/XML/movies.xml");
//            Element newMovie = (Element)movie.writeToXmlFileFormat(document);
//
//            //THEN
//            assertEquals(movie, Movie.loadEntityXml(newMovie));
//        }
//        catch (ParserConfigurationException | IOException | SAXException e){ e.printStackTrace();}
//    }
//
//

//
//    @Test void Movie_getEntityName_shouldReturnTheMovieString()
//    {
//        //WHEN
//        String movieName = movie.getEntityName();
//
//        //THEN
//        assertEquals("movie", movieName);
//    }
//
//


