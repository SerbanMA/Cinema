//package cinema.domain;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import ro.ubb.cinema.domain.entities.*;
//
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TicketTests {
//
//    public static final Long IDENTIFIER = 1L;
//    private static final Cinema CINEMA = new Cinema(1L, "Cinema", "Here");
//    public static final Room ROOM = new Room(11L, 1, "House", 100, CINEMA);
//    public static final Movie MOVIE = new Movie(12L, "Ana", 30, "Action");
//    public static final Client CLIENT = new Client(13L, "Ma", "Ta", "ma-ta@yahoo.com", 40);
//    public static final Double PRICE = 99.99;
//    public static final LocalDate DATE = LocalDate.MIN;
//    public static final LocalTime TIME = LocalTime.MIN;
//
//    public static final Long NEW_IDENTIFIER = 2L;
//    private static final Cinema NEW_CINEMA = new Cinema(2L, "Cinema", "Here");
//    public static final Room NEW_ROOM = new Room(21L, 1, "House", 100, NEW_CINEMA);
//    public static final Movie NEW_MOVIE = new Movie(22L, "Ana", 30, "Action");
//    public static final Client NEW_CLIENT = new Client(23L, "Ma", "Ta", "ma-ta@yahoo.com", 40);
//
//    public static final Double NEW_PRICE = 199.99;
//    public static final LocalDate NEW_DATE = LocalDate.MAX;
//    public static final LocalTime NEW_TIME = LocalTime.MAX;
//
//    private Ticket ticket;
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        ticket = new Ticket(IDENTIFIER, MOVIE, ROOM, CLIENT, PRICE, DATE, TIME);
//    }
//
//    @Test
//    public void Ticket_emptyConstructor_shouldReturnEntityWithEmptyFields()
//    {
//        //WHEN
//        Ticket TICKET= new Ticket();
//
//        //THEN
//        assertNull(TICKET.getId());
//        assertNull(TICKET.getMovie());
//        assertNull(TICKET.getRoom());
//        assertNull(TICKET.getClient());
//        assertNull(TICKET.getPrice());
//        assertNull(TICKET.getDate());
//        assertNull(TICKET.getTime());
//    }
//
//    @Test
//    public void Ticket_constructorByID_shouldReturnEntityWithGivenIdAndNullFields() {
//        //WHEN
//        Ticket TICKET = new Ticket(NEW_IDENTIFIER);
//
//        //THEN
//        assertEquals(NEW_IDENTIFIER, TICKET.getId());
//        assertNull(TICKET.getMovie());
//        assertNull(TICKET.getRoom());
//        assertNull(TICKET.getClient());
//        assertNull(TICKET.getPrice());
//        assertNull(TICKET.getDate());
//        assertNull(TICKET.getTime());
//    }
//
//    @Test
//    public void Ticket_getIdentifier_shouldReturnTheIdentifier() {
//        assertEquals(IDENTIFIER, ticket.getId());
//    }
//
//    @Test
//    public void Ticket_getMovie_shouldReturnTheMovie() {
//        assertEquals(MOVIE, ticket.getMovie());
//    }
//
//    @Test
//    public void Ticket_getRoom_shouldReturnTheRoom() {
//        assertEquals(ROOM, ticket.getRoom());
//    }
//
//    @Test
//    public void Ticket_getClient_shouldReturnTheClient() {
//        assertEquals(CLIENT, ticket.getClient());
//    }
//
//    @Test
//    public void Ticket_getPrice_shouldReturnThePrice() {
//        assertEquals(PRICE, ticket.getPrice());
//    }
//
//    @Test
//    public void Ticket_getDate_shouldReturnTheDate() {
//        assertEquals(DATE, ticket.getDate());
//    }
//
//    @Test
//    public void Ticket_getTime_shouldReturnTheTime() {
//        assertEquals(TIME, ticket.getTime());
//    }
//
//    @Test
//    public void Ticket_setIdentifier_shouldChangeTheIdentifier() {
//        //WHEN
//        ticket.setId(NEW_IDENTIFIER);
//
//        //THEN
//        assertEquals(NEW_IDENTIFIER, ticket.getId());
//    }
//
//    @Test
//    public void Ticket_setMovie_shouldChangeTheMovie() {
//        //WHEN
//        ticket.setMovie(NEW_MOVIE);
//
//        //THEN
//        assertEquals(NEW_MOVIE, ticket.getMovie());
//    }
//
//    @Test
//    public void Ticket_setRoom_shouldChangeTheRoom() {
//        //WHEN
//        ticket.setRoom(NEW_ROOM);
//
//        //THEN
//        assertEquals(NEW_ROOM, ticket.getRoom());
//    }
//
//    @Test
//    public void Ticket_setClient_shouldChangeTheClient() {
//        //WHEN
//        ticket.setClient(NEW_CLIENT);
//
//        //THEN
//        assertEquals(NEW_CLIENT, ticket.getClient());
//    }
//
//    @Test
//    public void Ticket_setPrice_shouldChangeThePrice() {
//        //WHEN
//        ticket.setPrice(NEW_PRICE);
//
//        //THEN
//        assertEquals(NEW_PRICE, ticket.getPrice());
//    }
//
//    @Test
//    public void Ticket_setDate_shouldChangeTheDate() {
//        //WHEN
//        ticket.setDate(NEW_DATE);
//
//        //THEN
//        assertEquals(NEW_DATE, ticket.getDate());
//    }
//
//    @Test
//    public void Ticket_setTime_shouldChangeTheTime() {
//        //WHEN
//        ticket.setTime(NEW_TIME);
//
//        //THEN
//        assertEquals(NEW_TIME, ticket.getTime());
//    }
//
//
//    @Test
//    public void Ticket_toString_shouldReturnGivenFormatOfTicket() {
//        assertEquals("Ticket{movie=Movie{identifier=12, name='Ana', duration=30, genre='Action'}, " +
//            "room=Room{roomID=11, floorNumber=1, roomName='House', numberOfSeats=100, cinema=Cinema{id=1, " +
//                        "name='Cinema', address='Here'}}, client=Client{clientId=13, clientFirstName='Ma', " +
//                        "clientLastName='Ta', clientEmail='ma-ta@yahoo.com', clientAge=40}, price=99.99, " +
//                        "date=-999999999-01-01, time=00:00}"
//                , ticket.toString());
//    }
//
//    @Test
//    public void Ticket_equals_shouldReturnIfEntitiesAreEqualOrNot()
//    {
//        //WHEN
//        Ticket ticket2 = new Ticket(IDENTIFIER, MOVIE, ROOM, CLIENT, PRICE, DATE, TIME);
//
//        //THEN
//        assertEquals(ticket2, ticket);
//        assertNotEquals(ticket2,null);
//    }
//
//
//
//}


