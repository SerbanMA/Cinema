//package cinema.service;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ro.ubb.cinema.domain._exceptions.CinemaBaseException;
//import ro.ubb.cinema.domain.entities.Cinema;
//import ro.ubb.cinema.domain.entities.Room;
//import ro.ubb.cinema.repository.CinemaJDBCRepository;
//import ro.ubb.cinema.repository.RoomJDBCRepository;
//import ro.ubb.cinema.service.RoomServiceImpl;
//import ro.ubb.cinema.service._exceptions.ServiceValidatorException;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class RoomServiceImplTests {
//    private static final Long IDENTIFIER = 35L;
//    private static final Integer FLOOR = 3;
//    private static final String ROOM_NAME= "Room Name";
//    private static final Integer NUMBER_OF_SEATS =50;
//    private static final Cinema CINEMA_1 = new Cinema(8L, "Cinema", "Here");
//    private static final Room ROOM1= new Room(IDENTIFIER, FLOOR, ROOM_NAME, NUMBER_OF_SEATS, CINEMA_1);
//
//    private static final Long ANOTHER_IDENTIFIER = 35L;
//    private static final Integer ANOTHER_FLOOR = 3;
//    private static final String ANOTHER_ROOM_NAME= "Another Room Name";
//    private static final Integer ANOTHER_NUMBER_OF_SEATS = 50;
//    private static final Cinema CINEMA_2 = new Cinema(9L, "Cinema", "There");
//    private static final Room ANOTHER_ROOM = new Room(ANOTHER_IDENTIFIER, ANOTHER_FLOOR, ANOTHER_ROOM_NAME, ANOTHER_NUMBER_OF_SEATS, CINEMA_2);
//
//    private static final Long YET_ANOTHER_IDENTIFIER = 5L;
//    private static final Integer YET_ANOTHER_FLOOR = 3;
//    private static final String YET_ANOTHER_ROOM_NAME= "Another Room Name";
//    private static final Integer YET_ANOTHER_NUMBER_OF_SEATS = 20;
//    private static final Cinema CINEMA_3 = new Cinema(10L, "Cinema", "Somewhere else");
//    private static final Room YET_ANOTHER_ROOM = new Room(YET_ANOTHER_IDENTIFIER, YET_ANOTHER_FLOOR, YET_ANOTHER_ROOM_NAME, YET_ANOTHER_NUMBER_OF_SEATS, CINEMA_3);
//
//    @InjectMocks
//    private RoomServiceImpl roomServiceImpl;
//
//    @Mock
//    RoomJDBCRepository roomRepository;
//
//    @Mock
//    CinemaJDBCRepository cinemaRepository;
//
//    @BeforeEach
//    public void setUp()
//    {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void RoomService_addRoom_shouldAddRoomToRepository() {
//        //GIVEN
//        when(cinemaRepository.findById(CINEMA_1.getId())).thenReturn(Optional.of(CINEMA_1));
//        when(roomRepository.findById(IDENTIFIER)).thenReturn(Optional.empty()).thenReturn(Optional.of(ROOM1));
//
//        //WHEN
//        roomServiceImpl.addRoom(ROOM1);
//
//        //THEN
//        assertTrue(roomRepository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void RoomService_deleteRoom_shouldDeleteRoomFromRepository(){
//        //GIVEN
//        when(roomRepository.findById(IDENTIFIER)).thenReturn(Optional.of(ROOM1)).thenReturn(Optional.empty());
//
//        //WHEN
//        roomServiceImpl.deleteRoom(ROOM1);
//
//        //THEN
//        assertFalse(roomRepository.findById(IDENTIFIER).isPresent());
//    }
//
//    @Test
//    public void RoomService_updateRoom_shouldUpdateRoomToRepository() {
//        //GIVEN
//        when(cinemaRepository.findById(CINEMA_2.getId())).thenReturn(Optional.of(CINEMA_2));
//        when(roomRepository.findById(IDENTIFIER)).thenReturn(Optional.of(ANOTHER_ROOM));
//
//        //WHEN
//        roomServiceImpl.updateRoom(ANOTHER_ROOM);
//
//        //THEN
//        assertTrue(roomRepository.findById(ANOTHER_IDENTIFIER).isPresent());
//        assertEquals(ANOTHER_ROOM, roomRepository.findById(ANOTHER_IDENTIFIER).get());
//    }
//
//    @Test
//    public void RoomService_filterRoom_shouldFilterRoomToRepository(){
//        //GIVEN
//        when(roomRepository.findAll()).thenReturn(new ArrayList<>(){
//            {
//                add(ROOM1);
//                add(YET_ANOTHER_ROOM);
//            }
//        });
//
//        //WHEN
//        Set<Room> filteredRooms = roomServiceImpl.filterRoomByNoOfSeats(30);
//
//        //THEN
//        assertTrue(filteredRooms.contains(ROOM1));
//        assertFalse(filteredRooms.contains(YET_ANOTHER_ROOM));
//    }
//
//    @Test
//    public void RoomService_getAllRooms_shouldGetAllRoomsFromMemoryRepository() {
//        //GIVEN
//        when(roomRepository.findAll()).thenReturn(new ArrayList<>(){
//            {
//                add(ROOM1);
//                add(YET_ANOTHER_ROOM);
//            }
//        });
//
//        Set<Room> myRooms = new HashSet<>();
//        myRooms.add(ROOM1);
//        myRooms.add(YET_ANOTHER_ROOM);
//
//        //WHEN
//        Set<Room> allRooms = roomServiceImpl.getAllRooms();
//
//        //THEN
//        assertEquals(myRooms, allRooms);
//    }
//
//    @Test
//    public void RoomService_containsOne_shouldReturnTrue() {
//        //WHEN
//        when(roomRepository.findById(YET_ANOTHER_IDENTIFIER)).thenReturn(Optional.of(YET_ANOTHER_ROOM));
//        //THEN
//        assertTrue(roomServiceImpl.containsOne(YET_ANOTHER_IDENTIFIER));
//    }
//
//    @Test
//    public void RoomService_get_shouldReturnTheRoomWithId35L() {
//        //WHEN
//        when(roomRepository.findById(IDENTIFIER)).thenReturn(Optional.of(ROOM1));
//
//        //THEN
//        assertEquals(ROOM1, roomServiceImpl.get(IDENTIFIER));
//    }
//
//    @Test
//    public void RoomService_deleteRoomWithNoOfSeatsSmallerThanGiven_shouldDeleteRoomsWithGivenProperty() {
//        //GIVEN
//        when(roomRepository.findAll()).thenReturn(new ArrayList<>(){
//            {
//                add(ROOM1);
//                add(YET_ANOTHER_ROOM);
//            }
//        });
//
//        //WHEN
//        roomServiceImpl.deleteRoomWithNoOfSeatsSmallerThanGiven(ROOM1.getNumberOfSeats());
//
//        //THEN
//        assertFalse(roomServiceImpl.containsOne(YET_ANOTHER_ROOM.getId()));
//    }
//
//    @Test
//    public void RoomService_getRoomsWithLowestFloorNumberAndHighestNumberOfSeats_shouldReturnROOM1()
//    {
//        //GIVEN
//        Room TESTING_ROOM1= new Room(1L,1,"ROOM1",50, CINEMA_1);
//        Room TESTING_ROOM2= new Room(2L,0,"ROOM2",60, CINEMA_1);
//        Room TESTING_ROOM3= new Room(3L,0,"ROOM3",40, CINEMA_1);
//        Room TESTING_ROOM4= new Room(4L,0,"ROOM4",60, CINEMA_1);
//
//        when(roomRepository.findAll()).thenReturn(new ArrayList<>(){
//                                                                {
//                                                                    add(TESTING_ROOM1);
//                                                                    add(TESTING_ROOM2);
//                                                                    add(TESTING_ROOM3);
//                                                                    add(TESTING_ROOM4);
//                                                                }
//        });
//
//        //WHEN
//        Set<Room> filteredRooms = roomServiceImpl.getRoomsWithLowestFloorNumberAndHighestNumberOfSeats();
//
//        //THEN
//        assertTrue(filteredRooms.contains(TESTING_ROOM2));
//        assertTrue(filteredRooms.contains(TESTING_ROOM4));
//    }
//
//    @Test
//    public void RoomService_addRoom_duplicateRooms_shouldThrowError() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.of(ROOM1));
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> roomServiceImpl.addRoom(ROOM1));
//    }
//
//    @Test
//    public void RoomService_updateRoom_inexistentRoom_shouldThrowError() {
//        //GIVEN
//        when(cinemaRepository.findById(CINEMA_1.getId())).thenReturn(Optional.of(CINEMA_1)).thenReturn(Optional.empty());
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> roomServiceImpl.updateRoom(ROOM1));
//        assertThrows(ServiceValidatorException.class, () -> roomServiceImpl.updateRoom(ROOM1));
//
//    }
//
//    @Test
//    public void RoomService_deleteRoom_inexistentRoom_shouldThrowError() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> roomServiceImpl.deleteRoom(ROOM1));
//    }
//
//    @Test
//    public void RoomService_get_whenNotFound_shouldThrowArrayIndexOutOfBoundsException() {
//        //GIVEN
//        when(roomRepository.findById(ROOM1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> roomServiceImpl.get(ROOM1.getId()));
//    }
//}
