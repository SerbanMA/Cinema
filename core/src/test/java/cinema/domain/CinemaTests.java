//package cinema.domain;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import ro.ubb.cinema.domain.entities.Cinema;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class CinemaTests {
//    private static final Long IDENTIFIER = 9821L;
//    private static final String NAME = "CINEMA_NAME";
//    private static final String ADDRESS = "CINEMA_ADDRESS";
//
//    private static final Long NEW_IDENTIFIER = 48121L;
//    private static final String NEW_NAME = "NEW_CINEMA_NAME";
//    private static final String NEW_ADDRESS = "NEW_CINEMA_ADDRESS";
//
//    private Cinema cinema;
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        cinema = new Cinema(IDENTIFIER, NAME, ADDRESS);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        cinema = null;
//    }
//
//    @Test
//    public void Cinema_getName_shouldReturnNameOfTheCinema() {
//        assertEquals(NAME, cinema.getName());
//    }
//
//    @Test
//    public void Cinema_getAddress_shouldReturnAddressOfTheCinema() {
//        assertEquals(ADDRESS, cinema.getAddress());
//    }
//
//    @Test
//    public void Cinema_getIdentifier_shouldReturnIdentifierOfTheCinema() {
//        assertEquals(IDENTIFIER, cinema.getId());
//    }
//
//    @Test
//    public void Cinema_setName_shouldChangeTheNameOfTheCinema() {
//        //WHEN
//        cinema.setName(NEW_NAME);
//
//        //THEN
//        assertEquals(NEW_NAME, cinema.getName());
//    }
//
//    @Test
//    public void Cinema_setAddress_shouldChangeTheNameOfTheCinema() {
//        //WHEN
//        cinema.setAddress(NEW_ADDRESS);
//
//        //THEN
//        assertEquals(NEW_ADDRESS, cinema.getAddress());
//    }
//
//    @Test
//    public void Cinema_setIdentifier_shouldChangeTheNameOfTheCinema() {
//        //WHEN
//        cinema.setId(NEW_IDENTIFIER);
//
//        //THEN
//        assertEquals(NEW_IDENTIFIER, cinema.getId());
//    }
//
//
//    @Test
//    public void Cinema_toString_shouldReturnGivenFormatOfCinema() {
//        assertEquals("Cinema{id=9821, name='CINEMA_NAME', address='CINEMA_ADDRESS'}",cinema.toString());
//    }
//
//    @Test
//    public void Cinema_constructorByID_shouldReturnEntityWithGivenIdAndNullFields() {
//        //WHEN
//        Cinema cinema2=new Cinema(NEW_IDENTIFIER);
//
//        //THEN
//        assertNull(cinema2.getAddress());
//        assertNull(cinema2.getName());
//        assertEquals(NEW_IDENTIFIER, cinema2.getId());
//    }
//
//    @Test
//    public void Cinema_equals_shouldReturnIfEntitiesAreEqualOrNot()
//    {
//        //WHEN
//        Cinema cinema2= new Cinema(IDENTIFIER, NAME, ADDRESS);
//
//        //THEN
//        assertEquals(cinema2, cinema);
//        assertNotEquals(cinema2,null);
//    }
//
//    @Test
//    public void Cinema_emptyConstructor_shouldReturnEntityWithEmptyFields()
//    {
//        //WHEN
//        Cinema CINEMA= new Cinema();
//
//        //THEN
//        assertNull(CINEMA.getId());
//        assertNull(CINEMA.getName());
//        assertNull(CINEMA.getAddress());
//    }
//}
//    @Test
//    public void Cinema_WriteToFileFormat_shouldReturnGivenFormatOfCinema() {
//        assertEquals("9821,CINEMA_NAME,CINEMA_ADDRESS",cinema.writeToFileFormat());
//    }
//
//    @Test
//    public void Cinema_loadEntity_shouldReturnACinemaEntityBasedOnGivenListOfStrings(){
//        //WHEN
//        List<String> FIELDS = new ArrayList<>();
//        FIELDS.add("9821");
//        FIELDS.add("CINEMA_NAME");
//        FIELDS.add("CINEMA_ADDRESS");
//
//        //THEN
//        Cinema cinema3 = new Cinema(IDENTIFIER,NAME,ADDRESS);
//        assertEquals(cinema3, Cinema.loadEntity(FIELDS));
//    }
//
//    @Test
//    public void Cinema_loadEntityXml_shouldReturnACinemaEntityBasedOnGivenElement() {
//        try {
//            //WHEN
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse("./data/XML/cinemas.xml");
//            Element newCinema = (Element)cinema.writeToXmlFileFormat(document);
//
//            //THEN
//            assertEquals(cinema, Cinema.loadEntityXml(newCinema));
//        }
//        catch (ParserConfigurationException | IOException | SAXException e){ e.printStackTrace();}
//    }
//
//
//
//    @Test void Cinema_getEntityName_shouldReturnTheCinemaString()
//    {
//        //WHEN
//        String cinemaName = cinema.getEntityName();
//
//        //THEN
//        assertEquals("cinema", cinemaName);
//    }
