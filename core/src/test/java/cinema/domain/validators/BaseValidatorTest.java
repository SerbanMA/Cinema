package cinema.domain.validators;//package cinema.domain.validators;
//
//import org.junit.jupiter.api.Test;
//import ro.ubb.cinema.domain.entities.*;
//import ro.ubb.cinema.domain.validators.BaseValidator;
//import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class BaseValidatorTest {
//    Cinema CINEMA = new Cinema(1L,"CINEMA_NAME","CINEMA_ADDRESS", rooms);
//    Room ROOM = new Room(1L,1L,1,"ROOM_NAME",10, tickets);
//    Movie MOVIE =new Movie(1L,"NAME",10,"GENRE", tickets);
//    Client CLIENT = new Client(1L,"FIRSTNAME","LASTNAME","EMAIL@yah.c",15, tickets);
//
//    Calendar CALENDAR= new GregorianCalendar(2021, Calendar.MARCH, 13);
//    Ticket TICKET = new Ticket(1L,1L,1L,1L,10.00,CALENDAR);
//
//    BaseValidator baseValidator = new BaseValidator();
//
//    @Test
//    void BaseValidatorTest_validate_shouldValidateGivenEntities() {
//        try{
//            baseValidator.validate(CINEMA);
//
//        }catch (ValidatorException ex)
//        {
//            assertTrue(ex.getMessage().isBlank());
//            ex.printStackTrace();
//        }
//
//
//        try{
//            baseValidator.validate(CLIENT);
//        }catch (ValidatorException ex)
//        {
//            assertTrue(ex.getMessage().isBlank());
//            ex.printStackTrace();
//        }
//
//        try{
//            baseValidator.validate(MOVIE);
//        }catch (ValidatorException ex)
//        {
//            assertTrue(ex.getMessage().isBlank());
//            ex.printStackTrace();
//        }
//
//        try{
//            baseValidator.validate(ROOM);
//
//        }catch (ValidatorException ex)
//        {
//            assertTrue(ex.getMessage().isBlank());
//            ex.printStackTrace();
//        }
//
//        try{
//            baseValidator.validate(TICKET);
//
//        }catch (ValidatorException ex)
//        {
//            assertTrue(ex.getMessage().isBlank());
//            ex.printStackTrace();
//        }
//    }
//}