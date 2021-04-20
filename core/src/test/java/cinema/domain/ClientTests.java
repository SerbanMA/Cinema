package cinema.domain;//package cinema.domain;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import ro.ubb.cinema.domain.entities.Client;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ClientTests {
//    private static final Long ID = 1234L;
//    private static final String  FIRSTNAME = "FIRSTNAME";
//    private static final String LASTNAME = "LASTNAME";
//    private static final String EMAIL = "EMAIL@gmail.com";
//    private static final Integer AGE = 25;
//
//    private static final Long NEW_ID = 1500L;
//    private static final String  NEW_FIRSTNAME = "NEW_FIRSTNAME";
//    private static final String NEW_LASTNAME = "NEW_LASTNAME";
//    private static final String NEW_EMAIL = "NEWEMAIL@gmail.com";
//    private static final Integer NEW_AGE = 30;
//
//    private Client client;
//
//    @BeforeEach
//    public void setUp() throws RuntimeException {
//        client = new Client(ID, FIRSTNAME, LASTNAME, EMAIL, AGE);
//    }
//
//    @AfterAll
//    public void tearDown() throws RuntimeException {
//        client = null;
//    }
//
//    @Test
//    public void Client_getId_shouldReturnIdOfTheClient() { assertEquals(ID, client.getId());}
//
//    @Test
//    public void Client_getFirstName_shouldReturnFirstNameOfTheClient() { assertEquals(FIRSTNAME, client.getClientFirstName());}
//
//    @Test
//    public void Client_getLastName_shouldReturnLastNameOfTheClient() { assertEquals(LASTNAME, client.getClientLastName());}
//
//    @Test
//    public void Client_getName_shouldReturnNameOfTheClient() { assertEquals(FIRSTNAME + " " + LASTNAME, client.getClientName());}
//
//
//    @Test
//    public void Client_getEmail_shouldReturnEmailOfTheClient() { assertEquals(EMAIL, client.getClientEmail());}
//
//    @Test
//    public void Client_getAge_shouldReturnAgeOfTheClient() { assertEquals(AGE, client.getClientAge());}
//
//    @Test
//    public void Client_setId_shouldChangeTheIdOfTheClient()
//    {
//        //WHEN
//        client.setId(NEW_ID);
//
//        //THEN
//        assertEquals(NEW_ID, client.getId());
//    }
//
//    @Test
//    public void Client_setFirstName_shouldChangeTheFirstNameOfTheClient()
//    {
//        //WHEN
//        client.setClientFirstName(NEW_FIRSTNAME);
//
//        //THEN
//        assertEquals(NEW_FIRSTNAME, client.getClientFirstName());
//    }
//
//    @Test
//    public void Client_setLastName_shouldChangeTheLastNameOfTheClient()
//    {
//        //WHEN
//        client.setClientLastName(NEW_LASTNAME);
//
//        //THEN
//        assertEquals(NEW_LASTNAME, client.getClientLastName());
//    }
//
//    @Test
//    public void Client_setEmail_shouldChangeTheEmailOfTheClient()
//    {
//        //WHEN
//        client.setClientEmail(NEW_EMAIL);
//
//        //THEN
//        assertEquals(NEW_EMAIL, client.getClientEmail());
//    }
//
//    @Test
//    public void Client_setAge_shouldChangeTheAgeOfTheClient()
//    {
//        //WHEN
//        client.setClientAge(NEW_AGE);
//
//        //THEN
//        assertEquals(NEW_AGE, client.getClientAge());
//    }
//
//    @Test
//    public void Client_toString_shouldReturnGivenFormatOfClient() {
//        assertEquals("Client{clientId=1234, clientFirstName='FIRSTNAME'," +
//                " clientLastName='LASTNAME', clientEmail='EMAIL@gmail.com', clientAge=25}",client.toString());
//    }
//
//    @Test
//    public void Client_constructorByID_shouldReturnEntityWithGivenIdAndNullFields() {
//        //WHEN
//        Client client2=new Client(NEW_ID);
//
//        //THEN
//        assertEquals(NEW_ID, client2.getId());
//        assertNull(client2.getClientFirstName());
//        assertNull(client2.getClientLastName());
//        assertNull(client2.getClientEmail());
//        assertNull(client2.getClientAge());
//    }
//
//
//    @Test
//    public void Client_equals_shouldReturnIfEntitiesAreEqualOrNot()
//    {
//        //WHEN
//        Client client2= new Client(ID,FIRSTNAME,LASTNAME,EMAIL,AGE);
//
//        //THEN
//        assertEquals(client2, client);
//        assertNotEquals(client2,null);
//    }
//
//    @Test
//    public void Client_emptyConstructor_shouldReturnEntityWithEmptyFields()
//    {
//        //WHEN
//        Client CLIENT= new Client();
//
//        //THEN
//        assertNull(CLIENT.getId());
//        assertNull(CLIENT.getClientAge());
//        assertNull(CLIENT.getClientEmail());
//        assertNull(CLIENT.getClientLastName());
//        assertNull(CLIENT.getClientFirstName());
//
//    }
//}
//    @Test
//    public void Client_WriteToFileFormat_shouldReturnGivenFormatOfClient() {
//        assertEquals("1234,FIRSTNAME,LASTNAME,EMAIL@gmail.com,25",client.writeToFileFormat());
//    }
//
//    @Test
//    public void Client_loadEntity_shouldReturnAClientEntityBasedOnGivenListOfStrings(){
//        //WHEN
//        List<String> FIELDS = new ArrayList<>();
//        FIELDS.add("1234");
//        FIELDS.add("FIRSTNAME");
//        FIELDS.add("LASTNAME");
//        FIELDS.add("EMAIL@gmail.com");
//        FIELDS.add("25");
//
//        //THEN
//        Client client2 = new Client(ID,FIRSTNAME,LASTNAME,EMAIL,AGE);
//        assertEquals(client2, Client.loadEntity(FIELDS));
//    }
//
//    @Test
//    public void Client_loadEntityXml_shouldReturnAClientEntityBasedOnGivenElement(){
//        try {
//            //WHEN
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse("./data/XML/clients.xml");
//            Element newClient = (Element)client.writeToXmlFileFormat(document);
//
//            //THEN
//            Client client2 = new Client(ID,FIRSTNAME,LASTNAME,EMAIL,AGE);
//            assertEquals(client2, Client.loadEntityXml(newClient));
//        }
//        catch (ParserConfigurationException | IOException | SAXException e){ e.printStackTrace();}
//    }
//
//    @Test void Client_getEntityName_shouldReturnTheClientString()
//    {
//        //WHEN
//        String clientName = client.getEntityName();
//
//        //THEN
//        assertEquals("client", clientName);
//    }
//