package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.cinema.domain.entities.*;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.web.dto.TicketsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class TicketConsole implements EntityConsole{
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://localhost:8080/api/tickets";
    private static final Logger log = LoggerFactory.getLogger(TicketConsole.class);

    public TicketConsole() {
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void showOptions() {
        log.trace("showOptions - method entered");

        Map<Integer, Runnable> functionsForTicket= new HashMap<>();
        functionsForTicket.put(1,this::add);
        functionsForTicket.put(2,this::delete);
        functionsForTicket.put(3,this::update);
        functionsForTicket.put(4,this::filter);
        functionsForTicket.put(5,this::printAll);
        functionsForTicket.put(0,this::back);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {
            System.out.println("\nTICKET: Choose between: ");
            System.out.println("1. Add ticket");
            System.out.println("2. Delete ticket");
            System.out.println("3. Update ticket");
            System.out.println("4. Filter ticket by price");
            System.out.println("5. Print all the tickets");
            System.out.println("0. Back to main menu\n");


            System.out.println("I want to: ");
            BufferedReader bufferReadForMovie = new BufferedReader(new InputStreamReader(System.in));
            try {
                log.trace("showOptions - entered try");

                int optionChosenFromMenu = Integer.parseInt(bufferReadForMovie.readLine());
                functionsForTicket.getOrDefault(optionChosenFromMenu, this::emptyConsole).run();

                //BACK
                if (optionChosenFromMenu == 0)
                    step.set(false);
                log.trace("showOptions - method finished");

            } catch (IOException | RuntimeException ex) {
                log.trace("showOptions - found exception");
                System.out.println(ex.getMessage());
            }
        });
    }

    /**
     * Add a ticket
     */
    @Override
    public void add() {
        log.trace("add - method entered");
        try {
            TicketDto ticketDto = readTicket();
            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(url, ticketDto, CinemaDto.class);
                System.out.println("Ticket added successfully!");
            }).exceptionally(ex -> { System.err.println("Ticket not added"); return null; });
            log.trace("add - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Delete a ticket
     */
    @Override
    public void delete() {
        log.trace("delete - method entered");
        try {
            Long id = readTicketId();
            CompletableFuture.runAsync(() -> {
                restTemplate.delete(url + "/{id}", id);
                System.out.println("Ticket deleted successfully!");
            }).exceptionally(ex -> {System.err.println("Ticket not deleted"); return null; });
            log.trace("delete - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Update a ticket
     */
    @Override
    public void update() {
        log.trace("update - method entered");
        try {
            Long id = readTicketId();
            TicketDto readTicket = readTicket();
            readTicket.setId(id);
            CompletableFuture.runAsync(() -> {
                restTemplate.put(url + "/{id}", readTicket, id);
                System.out.println("Ticket updated successfully!");
            }).exceptionally(ex -> {System.err.println("Ticket not updated"); return null; });
            log.trace("update - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Print all tickets
     */
    @Override
    public void printAll() {
        log.trace("printAll - method entered");
        CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, TicketsDto.class)).thenAccept(System.out::println);
        log.trace("printAll - method finished");
    }

    /**
     * Filter tickets where price < given number
     */
    @Override
    public void filter() {
        log.trace("filter - method entered");

        System.out.print("Filter tickets where price < given price\nPrice: ");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Double numberToCompareWith = Double.parseDouble(bufferReader.readLine());
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByPrice", numberToCompareWith, TicketsDto.class)).thenAccept(System.out::println);
            log.trace("filter - method finished");

        }catch(IOException e) {
            log.trace("filter - exception found");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Back to main menu
     */
    @Override
    public void back(){}

    /**
     * Message for empty console
     */
    private void emptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }

    /**
     * Read attributes of a ticket
     *
     * @return ticket
     */
    private TicketDto readTicket() throws IOException {
        log.trace("readTicket - method entered");
        System.out.println("Read ticket {MovieIdentifier, RoomIdentifier, ClientIdentifier, Price, Date/Time (dd-mm-yyyy/hh:mm)}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        TicketDto ticket;

        Long movieIdentifier = Long.valueOf(bufferReader.readLine());
        Long roomIdentifier = Long.valueOf(bufferReader.readLine());
        Long clientIdentifier = Long.valueOf(bufferReader.readLine());
        Double price = Double.valueOf(bufferReader.readLine());
        String string = bufferReader.readLine();

        Movie movie =  new Movie(movieIdentifier);
        Room room = new Room(roomIdentifier);
        Client client = new Client(clientIdentifier);

        String[] parts = string.split("/");
        String date = parts[0];
        String time = parts[1];

        parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.of(hour, minute);

        ticket = new TicketDto(price, localDate, localTime, movie, room, client);

        log.trace("readTicket - method finished: ticket={}", ticket);

        return ticket;
    }

    /**
     * Read id of a ticket
     *
     * @return ticket
     */
    private Long readTicketId() throws IOException {
        log.trace("readTicketForDelete - method entered");
        System.out.println("Read ticket {Identifier}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        Long id = Long.valueOf(bufferReader.readLine());

        log.trace("readTicketForDelete - method finished: ticket={}", id);

        return id;
    }
}
