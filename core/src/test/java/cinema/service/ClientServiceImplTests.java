package cinema.service;//package cinema.service;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ro.ubb.cinema.domain._exceptions.CinemaBaseException;
//import ro.ubb.cinema.domain.entities.Client;
//import ro.ubb.cinema.repository.ClientJDBCRepository;
//import ro.ubb.cinema.service.ClientServiceImpl;
//import ro.ubb.cinema.service._exceptions.ServiceValidatorException;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//public class ClientServiceImplTests {
//
//    private static final Long ID1 = 10L;
//    private static final String  FIRSTNAME = "FIRSTNAME";
//    private static final String LASTNAME = "LASTNAME";
//    private static final String EMAIL = "EMAIL@gmail.com";
//    private static final Integer AGE = 25;
//
//    private static final Long ID2 = 1500L;
//    private static final String  NEW_FIRSTNAME = "NEW_FIRSTNAME";
//    private static final String NEW_LASTNAME = "NEW_LASTNAME";
//    private static final String NEW_EMAIL = "NEWEMAIL@gmail.com";
//    private static final Integer NEW_AGE = 30;
//
//    private static final Client CLIENT_1 = new Client(ID1, FIRSTNAME, LASTNAME, EMAIL, AGE);
//    private static final Client CLIENT_UPDATED = new Client(ID1, NEW_FIRSTNAME, NEW_LASTNAME, NEW_EMAIL, NEW_AGE);
//    private static final Client CLIENT_2 = new Client(ID2, NEW_FIRSTNAME, NEW_LASTNAME, NEW_EMAIL, NEW_AGE);
//
//    @InjectMocks
//    private ClientServiceImpl clientServiceImpl;
//
//    @Mock
//    ClientJDBCRepository repository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void ClientService_addClient_shouldAddClientToRepository() {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.empty()).thenReturn(Optional.of(CLIENT_1));
//
//        //WHEN
//        clientServiceImpl.addClient(CLIENT_1);
//
//        //THEN
//        assertTrue(repository.findById(ID1).isPresent());
//    }
//
//    @Test
//    public void ClientService_deleteClient_shouldDeleteClientFromRepository() {
//        //GIVEN
//        when(repository.findById(ID1)).thenReturn(Optional.of(CLIENT_1)).thenReturn(Optional.empty());
//
//        //WHEN
//        clientServiceImpl.deleteClient(CLIENT_1);
//
//        //THEN
//        assertFalse(clientServiceImpl.containsOne(ID1));
//    }
//
//    @Test
//    public void ClientService_updateClient_shouldUpdateClientFromRepository()  {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.of(CLIENT_UPDATED));
//
//        //WHEN
//        clientServiceImpl.updateClient(CLIENT_UPDATED);
//
//        //THEN
//        assertTrue(repository.findById(ID1).isPresent());
//        assertEquals(CLIENT_UPDATED, repository.findById(ID1).get());
//    }
//
//    @Test
//    public void ClientService_getAllClients_shouldReturnAllClientsFromRepository() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CLIENT_1);
//                add(CLIENT_2);
//            }
//        });
//
//        Set<Client> myClients = new HashSet<>();
//        myClients.add(CLIENT_1);
//        myClients.add(CLIENT_2);
//
//
//        //WHEN
//        Set<Client> clients = clientServiceImpl.getAllClients();
//
//        //THEN
//        assertEquals(myClients, clients);
//    }
//
//    @Test
//    public void ClientService_filterClientsByLastName_shouldReturnAllClientsWithGivenLastNameFromInMemoryRepository() {
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CLIENT_1);
//                add(CLIENT_2);
//            }
//        });
//
//        //WHEN
//        Set<Client> filteredClients = clientServiceImpl.filterClientsByLastName(NEW_LASTNAME);
//
//        //THEN
//        assertTrue(filteredClients.contains(CLIENT_2));
//        assertFalse(filteredClients.contains(CLIENT_1));
//    }
//
//    @Test
//    public void ClientService_containsOne_shouldReturnTrue() {
//        //GIVEN
//        when(repository.findById(ID1)).thenReturn(Optional.of(CLIENT_1));
//
//        //WHEN + THEN
//        assertTrue(clientServiceImpl.containsOne(CLIENT_1.getId()));
//    }
//
//    @Test
//    public void ClientService_get_shouldReturnTheClientWithIdSentAsParameter() {
//        //GIVEN
//        when(repository.findById(ID1)).thenReturn(Optional.of(CLIENT_1));
//
//        //WHEN + THEN
//        assertEquals(CLIENT_1, clientServiceImpl.get(ID1));
//    }
//
//    @Test
//    public void ClientService_increaseClientsAgeWithGivenInteger_shouldUpdateAgesOfClients(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CLIENT_1);
//                add(CLIENT_2);
//            }
//        });
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.of(CLIENT_1));
//        when(repository.findById(CLIENT_2.getId())).thenReturn(Optional.of(CLIENT_2));
//
//        Integer AGE1 = CLIENT_1.getClientAge() + 5;
//        Integer AGE2 = CLIENT_2.getClientAge() + 5;
//
//        //WHEN
//        clientServiceImpl.increaseClientsAgeWithGivenInteger(5);
//
//        //THEN
//        assertEquals(AGE1 ,CLIENT_1.getClientAge());
//        assertEquals(AGE2 ,CLIENT_2.getClientAge());
//    }
//
//
//    @Test
//    public void ClientService_getTheAgeOfOldestClient_shouldReturnTheMaximumAge(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(new ArrayList<>() {
//            {
//                add(CLIENT_1);
//                add(CLIENT_2);
//            }
//        });
//
//        //WHEN
//        Integer maximumAge = clientServiceImpl.getTheAgeOfOldestClient().getClientAge();
//
//        //THEN
//        assertEquals(NEW_AGE, maximumAge);
//    }
//
//    @Test
//    public void ClientService_addClient_duplicateClients_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.of(CLIENT_1));
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> clientServiceImpl.addClient(CLIENT_1));
//    }
//
//    @Test
//    public void ClientService_updateClient_inexistentClient_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(CinemaBaseException.class, () -> clientServiceImpl.updateClient(CLIENT_1));
//    }
//
//    @Test
//    public void ClientService_deleteClient_inexistentClient_shouldThrowError() {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ServiceValidatorException.class, () -> clientServiceImpl.deleteClient(CLIENT_1));
//    }
//
//    @Test
//    public void ClientService_get_whenNotFound_shouldThrowArrayIndexOutOfBoundsException() {
//        //GIVEN
//        when(repository.findById(CLIENT_1.getId())).thenReturn(Optional.empty());
//
//        //THEN
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> clientServiceImpl.get(ID1));
//    }
//}
