package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.CinemasDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class CinemaConsole implements EntityConsole{
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://localhost:8080/api/cinemas";
    private static final Logger log = LoggerFactory.getLogger(CinemaConsole.class);

    public CinemaConsole() {}

    @Override
    @SuppressWarnings("Duplicates")
    public void showOptions() {
        log.trace("showOptions - method entered");
        Map<Integer, Runnable> functionsForCinemas = new HashMap<>();

        functionsForCinemas.put(1,this::add);
        functionsForCinemas.put(2,this::delete);
        functionsForCinemas.put(3,this::update);
        functionsForCinemas.put(4,this::filter);
        functionsForCinemas.put(5,this::printAll);
        functionsForCinemas.put(0,this::back);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {
            System.out.println("\nCINEMA: Choose between: ");
            System.out.println("1. Add cinema");
            System.out.println("2. Delete cinema");
            System.out.println("3. Update cinema");
            System.out.println("4. Filter cinemas by name");
            System.out.println("5. Print all the cinemas");
            System.out.println("0. Back to main menu\n");

            System.out.println("I want to: ");
            BufferedReader bufferReadForMovie = new BufferedReader(new InputStreamReader(System.in));
            try {
                log.trace("showOptions - entered try");
                int optionChosenFromMenu = Integer.parseInt(bufferReadForMovie.readLine());
                functionsForCinemas.getOrDefault(optionChosenFromMenu, this::emptyConsole).run();

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
     * Add a cinema
     */
    @Override
    public void add() {
        log.trace("add - method entered");
        try {
            CinemaDto cinemaDto = readCinemaDto();
            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(url, cinemaDto, CinemaDto.class);
                System.out.println("Cinema added successfully!");
            }).exceptionally(ex -> { System.err.println("Cinema not added"); return null; });
            log.trace("add - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Delete a cinema
     */
    @Override
    public void delete() {
        log.trace("delete - method entered");
        try {
            Long id = readCinemaId();
            CompletableFuture.runAsync(() -> {
                restTemplate.delete(url + "/{id}", id);
                System.out.println("Cinema deleted successfully!");
            }).exceptionally(ex -> {System.err.println("Cinema not deleted"); return null; });
            log.trace("delete - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("delete - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Update a cinema
     */
    @Override
    public void update() {
        log.trace("update - method entered");
        try {
            Long id = readCinemaId();
            CinemaDto cinemaDto = readCinemaDto();
            cinemaDto.setId(id);
            CompletableFuture.runAsync(() -> {
                restTemplate.put(url + "/{id}", cinemaDto, id);
                System.out.println("Cinema updated successfully!");
            }).exceptionally(ex -> {System.err.println("Cinema not updated"); return null; });
            log.trace("update - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void printAll() {
        log.trace("printAll - method entered");
        CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, CinemasDto.class)).thenAccept(System.out::println);
        log.trace("printAll - method finished");
    }

    @Override
    public void filter() {
        log.trace("filter - method entered");
        System.out.print("Filter cinemas by name.\nName: ");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String cinemaName = bufferReader.readLine();
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByName", cinemaName, CinemasDto.class)).thenAccept(System.out::println);
            log.trace("filter - method finished");
        } catch(IOException ex) {
            log.trace("filter - exception found");
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void back(){}

    private void emptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }

    private CinemaDto readCinemaDto() throws IOException {
        System.out.println("Read cinema {Name, Address}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        CinemaDto cinema;

        log.trace("readCinema - method entered");
        String name = bufferReader.readLine();
        String address = bufferReader.readLine();

        cinema = new CinemaDto(name, address);

        log.trace("readCinema - method finished: cinema={}", cinema);

        return cinema;
    }

    private Long readCinemaId() throws IOException {
        log.trace("readCinemaId - method entered");
        System.out.println("Read cinema {Identifier}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        Long id  = Long.valueOf(bufferReader.readLine());

        log.trace("readCinemaId - method finished: id={}", id);

        return id;
    }
}
