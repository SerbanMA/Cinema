package cinema.domain;//package cinema.domain;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import ro.ubb.cinema.domain.entities.Cinema;
//import ro.ubb.cinema.domain.entities.Room;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class RoomTests {
//    private final Long ROOM_ID = 1L;
//    private final Cinema CINEMA = new Cinema(1L, "Cinema", "Address");
//    private final Integer FLOOR = 1;
//    private final String ROOM_NAME = "NAME";
//    private final Integer NUMBER_OF_SEATS = 10;
//
//    private final Long ANOTHER_ROOM_ID = 2L;
//    private final Long ANOTHER_CINEMA_ID = 2L;
//    private final Integer ANOTHER_FLOOR = 2;
//    private final String ANOTHER_ROOM_NAME = "ANOTHER_NAME";
//    private final Integer ANOTHER_NUMBER_OF_SEATS = 20;
//
//    private Room room;
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        room = new Room(ROOM_ID, FLOOR, ROOM_NAME, NUMBER_OF_SEATS, CINEMA);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        room = null;
//    }
//
//    @Test
//    public void Room_getRoomID_returnsRoomID() {
//        assertEquals(ROOM_ID, room.getId());
//    }
//
//    @Test
//    public void Room_getCinemaID_returnsCinemaID() {
//        assertEquals(CINEMA, room.getCinema());
//    }
//
//    @Test
//    public void Room_getFloorNumber_returnsFloorNo() {
//        assertEquals(FLOOR, room.getFloorNumber());
//    }
//
//    @Test
//    public void Room_getRoomName_returnsRoomName() {
//        assertEquals(ROOM_NAME, room.getRoomName());
//    }
//
//    @Test
//    public void Room_getNumberOfSeats_returnsNumberOfSeats() {
//        assertEquals(NUMBER_OF_SEATS, room.getNumberOfSeats());
//    }
//
//    @Test
//    public void Room_setRoomID_changesRoomID() {
//        //WHEN
//        room.setId(ANOTHER_ROOM_ID);
//
//        //THEN
//        assertEquals(ANOTHER_ROOM_ID, room.getId());
//    }
//
//    @Test
//    public void Room_setCinemaID_changesCinemaID() {
//        //WHEN
//        room.setCinema(new Cinema(ANOTHER_CINEMA_ID));
//
//        //THEN
//        assertEquals(ANOTHER_CINEMA_ID, room.getCinema().getId());
//    }
//
//    @Test
//    public void Room_setFloorNumber_changesFloorNumber() {
//        //WHEN
//        room.setFloorNo(ANOTHER_FLOOR);
//
//        //THEN
//        assertEquals(ANOTHER_FLOOR, room.getFloorNumber());
//    }
//
//    @Test
//    public void Room_setRoomName_changesRoomName() {
//        //WHEN
//        room.setRoomName(ANOTHER_ROOM_NAME);
//
//        //THEN
//        assertEquals(ANOTHER_ROOM_NAME, room.getRoomName());
//    }
//
//    @Test
//    public void Room_setNumberOfSeats_changesNumberOfSeats() {
//        //WHEN
//        room.setNumberOfSeats(ANOTHER_NUMBER_OF_SEATS);
//
//        //THEN
//        assertEquals(ANOTHER_NUMBER_OF_SEATS, room.getNumberOfSeats());
//    }
//
//
//    @Test
//    public void Room_toString_shouldReturnGivenFormatOfRoom() {
//        assertEquals("Room{roomID=1, floorNumber=1, roomName='NAME', numberOfSeats=10, cinema=Cinema{id=1, name='Cinema', address='Address'}}", room.toString());
//    }
//
//    @Test
//    public void Room_equals_shouldReturnIfEntitiesAreEqualOrNot()
//    {
//        //WHEN
//        Room room2 = new Room(ROOM_ID, FLOOR,ROOM_NAME,NUMBER_OF_SEATS, CINEMA);
//
//        //THEN
//        assertEquals(room2, room);
//        assertNotEquals(room2,null);
//    }
//
//    @Test
//    public void Room_constructorByID_shouldReturnEntityWithGivenIdAndNullFields() {
//        //WHEN
//        Room room2 = new Room(ANOTHER_ROOM_ID);
//
//        //THEN
//        assertEquals(ANOTHER_ROOM_ID, room2.getId());
//        assertNull(room2.getRoomName());
//        assertNull(room2.getCinema());
//        assertNull(room2.getFloorNumber());
//        assertNull(room2.getNumberOfSeats());
//    }
//
//    @Test
//    public void Room_emptyConstructor_shouldReturnEntityWithEmptyFields()
//    {
//        //WHEN
//        Room ROOM= new Room();
//
//        //THEN
//        assertNull(ROOM.getId());
//        assertNull(ROOM.getRoomName());
//        assertNull(ROOM.getCinema());
//        assertNull(ROOM.getFloorNumber());
//        assertNull(ROOM.getNumberOfSeats());
//    }
//}


//    @Test
//    public void Room_WriteToFileFormat_shouldReturnGivenFormatOfRoom() {
//        assertEquals("1,1,1,NAME,10",room.writeToFileFormat());
//    }
//
//    @Test
//    public void Room_loadEntity_shouldReturnARoomEntityBasedOnGivenListOfStrings(){
//        //WHEN
//        List<String> FIELDS = new ArrayList<>();
//        FIELDS.add("1");
//        FIELDS.add("1");
//        FIELDS.add("1");
//        FIELDS.add("NAME");
//        FIELDS.add("10");
//
//        //THEN
//        Room room2 = new Room(ROOM_ID,CINEMA_ID,FLOOR,ROOM_NAME,NUMBER_OF_SEATS);
//        assertEquals(room2, Room.loadEntity(FIELDS));
//    }
//
//    @Test
//    public void Room_loadEntityXml_shouldReturnARoomEntityBasedOnGivenElement() {
//        try {
//            //WHEN
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse("./data/XML/rooms.xml");
//            Element newRoom = (Element)room.writeToXmlFileFormat(document);
//
//            //THEN
//            assertEquals(room, Room.loadEntityXml(newRoom));
//        }
//        catch (ParserConfigurationException | IOException | SAXException e){ e.printStackTrace();}
//    }
//
//

//
//    @Test void Room_getEntityName_shouldReturnTheTicketString()
//    {
//        //WHEN
//        String roomName = room.getEntityName();
//
//        //THEN
//        assertEquals("room", roomName);
//    }
//