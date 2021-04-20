//package cinema.service;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ro.ubb.cinema.domain._exceptions.CinemaBaseException;
//import ro.ubb.cinema.repository.ClientJDBCRepository;
//import ro.ubb.cinema.repository.MovieJDBCRepository;
//import ro.ubb.cinema.repository.RoomJDBCRepository;
//import ro.ubb.cinema.repository.TicketJDBCRepository;
//import ro.ubb.cinema.service.TicketServiceImpl;
//import ro.ubb.cinema.service._exceptions.ServiceValidatorException;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class TicketServiceImplTests {
//    private static final Cinema CINEMA1 = new Cinema(8L, "Cinema", "Here");
//    public static final Movie MOVIE1 = new Movie(11L, "Ana", 30, "Action");
//    public static final Room ROOM1 = new Room(12L, 1, "House", 100, CINEMA1);
//    public static final Client CLIENT1 = new Client(13L, "Ma", "Ta", "ma-ta@yahoo.com", 40);
//
//    public static final Long IDENTIFIER1 = 1L;
//    public static final Double PRICE1 = 99.99;
//    public static final LocalDate DATE1 = LocalDate.of(2020, 2, 1);
//    public static final LocalTime TIME1 = LocalTime.of(0, 0, 0);
//    public static final Ticket TICKET1 = new Ticket(IDENTIFIER1, MOVIE1, ROOM1, CLIENT1, PRICE1, DATE1, TIME1);
//
//    public static final Long IDENTIFIER2 = 2L;
//    public static final Double PRICE2 = 199.99;
//    public static final LocalDate DATE2 = LocalDate.of(2020, 3, 1);
//    public static final Ticket TICKET2 = new Ticket(IDENTIFIER2, MOVIE1, ROOM1, CLIENT1, PRICE2, DATE2, TIME1);
//
//    public static final Long IDENTIFIER3 = 1L;
//    public static final Double PRICE3 = 99.99;
//    public static final Ticket TICKET3 = new Ticket(IDENTIFIER3, MOVIE1, ROOM1, CLIENT1, PRICE3, DATE1, TIME1);
//
//    @InjectMocks
//    private TicketServiceImpl ticketServiceImpl;
//
//    @Mock
//    TicketJDBCRepository repository;
//
//    @Mock
//    RoomJDBCRepository roomRepository;
//
//    @Mock
//    ClientJDBCRepository clientRepository;
//
//    @Mock
//    MovieJDBCRepository movieRepository;
//
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void TicketService_addTicket_shouldAddTicketToInMemoryRepository(){
//        //GIVEN
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1));
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1));
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1));
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.empty()).thenReturn(Optional.of(TICKET1));
//
//        //WHEN
//        ticketServiceImpl.addTicket(TICKET1);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER1).isPresent());
//
//    }
//
//    @Test
//    public void TicketService_deleteTicket_shouldDeleteTicketToInMemoryRepository(){
//        //GIVEN
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.of(TICKET1)).thenReturn(Optional.empty());
//
//        //WHEN
//        ticketServiceImpl.deleteTicket(TICKET1);
//
//        //THEN
//        assertFalse(repository.findById(IDENTIFIER1).isPresent());
//    }
//
//    @Test
//    public void TicketService_updateTicket_shouldUpdateTicketToInMemoryRepository(){
//        //GIVEN
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1));
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1));
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1));
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.of(TICKET3));
//
//        //WHEN
//        ticketServiceImpl.updateTicket(TICKET3);
//
//        //THEN
//        assertTrue(repository.findById(IDENTIFIER1).isPresent());
//    }
//
//    @Test
//    public void TicketService_filterTicket_shouldFilterTicketToInMemoryRepository(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(TICKET1);
//                add(TICKET2);
//            }
//        });
//
//        //WHEN
//        Set<Ticket> filteredTickets = ticketServiceImpl.filterTicketsByPrice(100.0);
//
//        //THEN
//        assertTrue(filteredTickets.contains(TICKET1));
//        assertFalse(filteredTickets.contains(TICKET2));
//    }
//
//    @Test
//    public void TicketService_getAllTickets_shouldGetAllTicketsFromMemoryRepository(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(TICKET1);
//                add(TICKET2);
//            }
//        });
//
//        Set<Ticket> myTickets = new HashSet<>();
//        myTickets.add(TICKET1);
//        myTickets.add(TICKET2);
//
//        //WHEN
//        Set<Ticket> allTickets = ticketServiceImpl.getAllTickets();
//
//        //THEN
//        assertEquals(myTickets, allTickets);
//    }
//
//    @Test
//    public void TicketService_getRepository_shouldReturnRepository(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(TICKET1);
//                add(TICKET2);
//                add(TICKET3);
//            }
//        });
//
//        //THEN
//        assertEquals(repository.findAll(), ticketServiceImpl.getRepository().findAll());
//    }
//
//    @Test
//    public void TicketService_deleteTicketsByDate_shouldDeleteAllTicketsFromGivenDate(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(TICKET1);
//                add(TICKET2);
//                add(TICKET3);
//            }
//        }).thenReturn(new ArrayList<>() {
//            {
//                add(TICKET1);
//                add(TICKET3);
//            }
//        });
//
//        Set<Ticket> myTickets = new HashSet<>();
//        myTickets.add(TICKET1);
//        myTickets.add(TICKET3);
//
//        //WHEN
//        ticketServiceImpl.deleteTicketsByDate(DATE2);
//
//        //THEN
//        assertEquals(myTickets, ticketServiceImpl.getAllTickets());
//
//    }
//
//    @Test
//    public void TicketService_containsOne_shouldReturnTrue() {
//        //WHEN
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.of(TICKET1));
//
//        //THEN
//        assertTrue(ticketServiceImpl.containsOne(TICKET1.getId()));
//    }
//
//    @Test
//    public void TicketService_get_whenFound_shouldReturnTheTicketWithGivenIdSentAsParameter() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.of(TICKET1));
//
//        //WHEN + THEN
//        assertEquals(TICKET1, ticketServiceImpl.get(IDENTIFIER1));
//    }
//
//    @Test
//    public void TicketService_get_whenNOTFound_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(IDENTIFIER1)).thenReturn(Optional.empty());
//
//        //WHEN
//        Exception thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> ticketServiceImpl.get(IDENTIFIER1));
//
//        String expectedMessage = "Ticket not found";
//        String actualMessage = thrownException.getMessage();
//
//        //THEN
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    public void TicketService_addTicket_duplicateTicket_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.of(TICKET1));
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> ticketServiceImpl.addTicket(TICKET1));
//    }
//
//    @Test
//    public void TicketService_updateTicket_inexistentTicket_shouldThrowError() {
//        //GIVEN
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.of(CLIENT1));
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.of(MOVIE1));
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.of(ROOM1));
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.empty()).thenReturn(Optional.of(TICKET1)).thenReturn(Optional.of(TICKET1)).thenReturn(Optional.of(TICKET1));
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//    }
//
//    @Test
//    public void TicketService_updateTicket_inexistentRoomForTicket_shouldThrowError() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.empty());
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.of(CLIENT1));
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.of(MOVIE1));
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//        assertThrows(ServiceValidatorException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//    }
//
//    @Test
//    public void TicketService_updateTicket_inexistentClientForTicket_shouldThrowError() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.of(ROOM1));
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.empty());
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.of(MOVIE1));
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//        assertThrows(ServiceValidatorException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//    }
//
//    @Test
//    public void TicketService_updateTicket_inexistentMovieForTicket_shouldThrowError() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.of(ROOM1));
//        when(clientRepository.findById(CLIENT1.getId())).thenReturn(Optional.of(CLIENT1)).thenReturn(Optional.of(CLIENT1));
//        when(movieRepository.findById(MOVIE1.getId())).thenReturn(Optional.of(MOVIE1)).thenReturn(Optional.empty());
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//        assertThrows(ServiceValidatorException.class, () -> ticketServiceImpl.updateTicket(TICKET1));
//    }
//
//    @Test
//    public void TicketService_deleteTicket_inexistentTicket_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(TICKET1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> ticketServiceImpl.deleteTicket(TICKET1));
//    }
//
//}
