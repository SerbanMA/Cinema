package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class Console {
    private final CinemaConsole cinemaConsole;
    private final ClientConsole clientConsole;
    private final MovieConsole movieConsole;
    private final RoomConsole roomConsole;
    private final TicketConsole ticketConsole;
    private static final Logger log = LoggerFactory.getLogger(Console.class);


    public Console(CinemaConsole cinemaConsole, ClientConsole clientConsole, MovieConsole movieConsole, RoomConsole roomConsole, TicketConsole ticketConsole) {
        this.cinemaConsole = cinemaConsole;
        this.clientConsole = clientConsole;
        this.movieConsole = movieConsole;
        this.roomConsole = roomConsole;
        this.ticketConsole = ticketConsole;
    }

    /**
     * Runs the console
     * shows the main menu of the application and calls for the other methods
     * when demanded
     */
    @SuppressWarnings("Duplicates")
    public void runConsole() {
        log.trace("runConsole - method entered");
        Map<Integer, Runnable> functionsToCall = new HashMap<>();
        functionsToCall.put(1, this.cinemaConsole::showOptions);
        functionsToCall.put(2, this.clientConsole::showOptions);
        functionsToCall.put(3, this.movieConsole::showOptions);
        functionsToCall.put(4, this.roomConsole::showOptions);
        functionsToCall.put(5, this.ticketConsole::showOptions);
        functionsToCall.put(0, this::ExitConsole);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {

            System.out.println("\nMAIN: Please select what would you like to do:\n");
            System.out.println("1. Manage cinemas");
            System.out.println("2. Manage clients");
            System.out.println("3. Manage movies");
            System.out.println("4. Manage rooms");
            System.out.println("5. Manage tickets");
            System.out.println("0. Exit\n");

            System.out.println("I choose option: ");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                int optionChosenFromMenu = Integer.parseInt(bufferRead.readLine());
                functionsToCall.getOrDefault(optionChosenFromMenu, this::EmptyConsole).run();

                //EXIT
                if (optionChosenFromMenu == 0)
                    step.set(false);

            } catch (IOException | RuntimeException ex) {
                System.out.println("Oh, dear! Your input was wrong. Please try again!");
                System.out.println(ex.getMessage());
            }
        });
        log.trace("runConsole - method finished");
    }

    /**
     * Shows an error message on the console
     */
    public void EmptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }


    public void ExitConsole() {
        System.out.println("Good bye! See you soon!");
    }

}