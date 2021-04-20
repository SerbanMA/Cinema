package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.CinemasDto;
import ro.ubb.cinema.web.dto.ClientDto;
import ro.ubb.cinema.web.dto.ClientsDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class ClientConsole implements EntityConsole{
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://localhost:8080/api/clients";
    private static final Logger log = LoggerFactory.getLogger(ClientConsole.class);

    public ClientConsole() {
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void showOptions() {
        log.trace("showOptions - method entered");

        Map<Integer, Runnable> functionsForClients = new HashMap<>();
        functionsForClients.put(1,this::add);
        functionsForClients.put(2,this::delete);
        functionsForClients.put(3,this::update);
        functionsForClients.put(4,this::filter);
        functionsForClients.put(5,this::printAll);
        functionsForClients.put(0,this::back);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {
            System.out.println("\nCLIENT: Choose between: ");
            System.out.println("1. Add client");
            System.out.println("2. Delete client");
            System.out.println("3. Update client");
            System.out.println("4. Filter client by last name");
            System.out.println("5. Print all the clients");
            System.out.println("0. Back to main menu\n");

            System.out.println("I want to: ");
            BufferedReader bufferReadForClient = new BufferedReader(new InputStreamReader(System.in));
            try {
                log.trace("showOptions - entered try");

                int optionChosenFromMenu = Integer.parseInt(bufferReadForClient.readLine());
                functionsForClients.getOrDefault(optionChosenFromMenu, this::emptyConsole).run();

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
     * Add a new client
     */
    @Override
    public void add() {
        log.trace("add - method entered");
        try {
            ClientDto clientDto = readClient();
            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(url, clientDto, ClientDto.class);
                System.out.println("Client added successfully!");
            }).exceptionally(ex -> { System.err.println("Client not added"); return null; });
                log.trace("add - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Delete a client
     */
    @Override
    public void delete() {
        log.trace("delete - method entered");
        try {
            Long id = readClientId();
            CompletableFuture.runAsync(() -> {
                restTemplate.delete(url + "/{id}", id);
                System.out.println("Client deleted successfully!");
            }).exceptionally(ex -> {System.err.println("Client not deleted"); return null; });
            log.trace("delete - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Update a client
     */
    @Override
    public void update() {
        log.trace("update - method entered");
        try {
            Long id = readClientId();
            ClientDto clientDto = readClient();
            clientDto.setId(id);
            CompletableFuture.runAsync(() -> {
                restTemplate.put(url + "/{id}", clientDto, id);
                System.out.println("Client updated successfully!");
            }).exceptionally(ex -> {System.err.println("Client not updated"); return null; });
            log.trace("update - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Print all the clients
     */
    @Override
    public void printAll() {
        log.trace("printAll - method entered");
        CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, ClientsDto.class)).thenAccept(System.out::println);
        log.trace("printAll - method finished");
    }

    /**
     * Filter all clients by the given last name
     */
    @Override
    public void filter() {
        log.trace("filter - method entered");
        System.out.print("Filter clients by last name\nLast name: ");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String clientLastName = bufferReader.readLine();
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByLastName", clientLastName, ClientsDto.class)).thenAccept(System.out::println);
            log.trace("filter - method finished");
        }catch(IOException e) {
            log.trace("filter - exception found");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Go back to main menu
     */
    @Override
    public void back(){}

    /**
     * Display message for empty console
     */
    private void emptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }

    /**
     * Read attributes of a client
     *
     * @return client
     */

    private ClientDto readClient() throws IOException {
        log.trace("readClient - method entered");

        System.out.println("Read Client {First Name, Last Name, Email, Age}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        ClientDto client;

        String firstName = bufferReader.readLine();
        String lastName = bufferReader.readLine();
        String email = bufferReader.readLine();
        Integer age = Integer.parseInt(bufferReader.readLine());

        client = new ClientDto(firstName, lastName, email, age);

        log.trace("readClient - method finished: client={}", client);

        return client;
    }

    private Long readClientId() throws IOException {
        log.trace("readClientForDelete - method entered");

        System.out.println("Read Client {Id}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        Long id = Long.valueOf(bufferReader.readLine());

        log.trace("readClientForDelete - method finished: client={}", id);

        return id;
    }
}
