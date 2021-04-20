package ro.ubb.cinema;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.cinema.ui.*;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.cinema.config");

        MovieConsole movieConsole = context.getBean(MovieConsole.class);
        CinemaConsole cinemaConsole = context.getBean(CinemaConsole.class);
        RoomConsole roomConsole = context.getBean(RoomConsole.class);
        TicketConsole ticketConsole = context.getBean(TicketConsole.class);
        ClientConsole clientConsole = context.getBean(ClientConsole.class);

        Console console = new Console(cinemaConsole, clientConsole, movieConsole, roomConsole, ticketConsole);
        console.runConsole();
    }
}
