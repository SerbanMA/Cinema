package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.RoomDto;
import ro.ubb.cinema.web.dto.RoomsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class RoomConsole implements EntityConsole{
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://localhost:8080/api/rooms";
    private static final Logger log = LoggerFactory.getLogger(RoomConsole.class);

    public RoomConsole() {}


    @Override
    @SuppressWarnings("Duplicates")
    public void showOptions() {
        log.trace("showOptions - method entered");

        Map<Integer, Runnable> functionsForRoom= new HashMap<>();
        functionsForRoom.put(1,this::add);
        functionsForRoom.put(2,this::delete);
        functionsForRoom.put(3,this::update);
        functionsForRoom.put(4,this::filter);
        functionsForRoom.put(5,this::printAll);
        functionsForRoom.put(0,this::back);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {
            System.out.println("\nROOM: Choose between: ");
            System.out.println("1. Add room");
            System.out.println("2. Delete room");
            System.out.println("3. Update room");
            System.out.println("4. Filter room by number of seats");
            System.out.println("5. Print all the rooms");
            System.out.println("0. Back to main menu\n");

            System.out.println("I want to: ");
            BufferedReader bufferReadForRoom = new BufferedReader(new InputStreamReader(System.in));
            try {
                log.trace("showOptions - entered try");

                int optionChosenFromMenu = Integer.parseInt(bufferReadForRoom.readLine());
                functionsForRoom.getOrDefault(optionChosenFromMenu, this::emptyConsole).run();

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
     * Add a room
     */
    @Override
    public void add() {
        log.trace("add - method entered");
        try {
            RoomDto roomDto = readRoom();
            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(url, roomDto, CinemaDto.class);
                System.out.println("Room added successfully!");
            }).exceptionally(ex -> { System.err.println("Room not added"); return null; });
            log.trace("add - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Delete a room
     */
    @Override
    public void delete() {
        log.trace("delete - method entered");
        try {
            Long id = readRoomId();
            CompletableFuture.runAsync(() -> {
                restTemplate.delete(url + "/{id}", id);
                System.out.println("Room deleted successfully!");
            }).exceptionally(ex -> {System.err.println("Room not deleted"); return null; });
            log.trace("delete - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Update a room
     */
    @Override
    public void update() {
        log.trace("update - method entered");
        try {
            Long id = readRoomId();
            RoomDto roomDto = readRoom();
            roomDto.setId(id);
            CompletableFuture.runAsync(() -> {
                restTemplate.put(url + "/{id}", roomDto, id);
                System.out.println("Room updated successfully!");
            }).exceptionally(ex -> {System.err.println("Room not updated"); return null; });
            log.trace("update - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void printAll() {
        log.trace("printAll - method entered");
        CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, RoomsDto.class))
                .thenAccept(System.out::println);
        log.trace("printAll - method finished");
    }

    @Override
    public void filter() {
        log.trace("filter - method entered");
        System.out.println("Filter rooms by number of seats >= given number");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer numberToCompareWith = Integer.parseInt(bufferReader.readLine());
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByNumberOfSeats", numberToCompareWith, RoomsDto.class)).thenAccept(System.out::println);
            log.trace("filter - method finished");
        } catch (IOException ex) {
            log.trace("filter - exception found");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void back(){}

    private void emptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }

    private RoomDto readRoom() throws IOException {
        log.trace("readRoom - method entered");
        System.out.println("Read room {CinemaID, floorNo, RoomName, NoOfSeats}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        RoomDto room;

        Long cinemaID = Long.valueOf(bufferReader.readLine());
        Integer floorNumber = Integer.parseInt(bufferReader.readLine());
        String rName  = bufferReader.readLine();
        Integer numberOfSeats = Integer.parseInt(bufferReader.readLine());
        Cinema cinema = new Cinema(cinemaID);

        room = new RoomDto(floorNumber, rName, numberOfSeats, cinema);

        log.trace("readRoom - method finished: room={}", room);

        return room;
    }

    private Long readRoomId() throws IOException {
        log.trace("readRoomId - method entered");
        System.out.println("Read room {Identifier}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        Long id = Long.valueOf(bufferReader.readLine());

        log.trace("readRoomId - method finished: id={}", id);

        return id;
    }
}
