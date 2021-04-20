package ro.ubb.cinema.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.cinema.domain.entities.BaseEntity;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.CinemasDto;
import ro.ubb.cinema.web.dto.MovieDto;
import ro.ubb.cinema.web.dto.MoviesDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Component
public class MovieConsole implements EntityConsole {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/movies";
    private static final Logger log = LoggerFactory.getLogger(MovieConsole.class);


    public MovieConsole() {
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void showOptions() {
        log.trace("showOptions - method entered");
        Map<Integer, Runnable> functionsForMovies = new HashMap<>();
        functionsForMovies.put(1,this::add);
        functionsForMovies.put(2,this::delete);
        functionsForMovies.put(3,this::update);
        functionsForMovies.put(4,this::filter);
        functionsForMovies.put(5,this::printAll);
        functionsForMovies.put(6,this::filterByGenre);
        functionsForMovies.put(0,this::back);

        AtomicBoolean step = new AtomicBoolean(true);
        IntStream.iterate(1, bool -> step.get(), i->1).forEach(i -> {
            System.out.println("\nMOVIE: Choose between: ");
            System.out.println("1. Add movie");
            System.out.println("2. Delete movie");
            System.out.println("3. Update movie");
            System.out.println("4. Filter movie by name");
            System.out.println("5. Print all the movies");
            System.out.println("6. Filter movie by genre");
            System.out.println("0. Back to main menu\n");

            System.out.println("I want to: ");
            BufferedReader bufferReadForMovie = new BufferedReader(new InputStreamReader(System.in));
            try {
                log.trace("showOptions - entered try");
                int optionChosenFromMenu = Integer.parseInt(bufferReadForMovie.readLine());
                functionsForMovies.getOrDefault(optionChosenFromMenu, this::emptyConsole).run();

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
     * Add a movie
     */
    @Override
    public void add() {
        log.trace("add - method entered");
        try {
            MovieDto movieDto = readMovie();
            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(url, movieDto, CinemaDto.class);
                System.out.println("Movie added successfully!");
            }).exceptionally(ex -> { System.err.println("Movie not added"); return null; });
            log.trace("add - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Delete a movie
     */
    @Override
    public void delete() {
        log.trace("delete - method entered");
        try {
            Long id = readMovieId();
            CompletableFuture.runAsync(() -> {
                restTemplate.delete(url + "/{id}", id);
                System.out.println("Movie deleted successfully!");
            }).exceptionally(ex -> {System.err.println("Movie not deleted"); return null; });
            log.trace("delete - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Update a movie
     */
    @Override
    public void update() {
        log.trace("update - method entered");
        try {
            Long id = readMovieId();
            MovieDto movieDto = readMovie();
            movieDto.setId(id);
            CompletableFuture.runAsync(() -> {
                restTemplate.put(url + "/{id}", movieDto, id);
                System.out.println("Movie updated successfully!");
            }).exceptionally(ex -> {System.err.println("Movie not updated"); return null; });
            log.trace("update - method finished");
        } catch(IOException | NumberFormatException ex) {
            log.trace("add - exception found");
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void printAll() {
        log.trace("printAll - method entered");
        CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url, MoviesDto.class)).thenAccept(System.out::println);
        log.trace("printAll - method finished");
    }

    @Override
    public void filter() {
        log.trace("filter - method entered");
        System.out.print("Filter movies based on their name \nName: ");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String movieName = bufferReader.readLine();
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByName", movieName, MoviesDto.class)).thenAccept(System.out::println);
            log.trace("filter - method finished");
        }catch(IOException e) {
            log.trace("filter - exception found");
            System.out.println(e.getMessage());
        }
    }

    public void filterByGenre() {
        log.trace("filterByGenre - method entered");
        System.out.print("Filter movies based on their genre \ngenre: ");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            log.trace("filterByGenre - entered try");

            String movieGenre = bufferReader.readLine();
            CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url + "/filterByGenre", movieGenre, MoviesDto.class)).thenAccept(System.out::println);

            log.trace("filterByGenre - method finished");
        }catch(IOException e) {
            log.trace("filterByGenre - exception found");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void back(){}

    private void emptyConsole() {
        System.out.println("Oh,dear! You got the wrong number!");
    }

    private MovieDto readMovie() throws IOException {
        log.trace("readMovie - method entered");
        System.out.println("Read movie {MovieName, Duration, Genre}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        MovieDto movie;

        log.trace("readMovie - entered try");
        String movieName = bufferReader.readLine();
        Integer duration = Integer.parseInt(bufferReader.readLine());
        String genre = bufferReader.readLine();

        movie = new MovieDto(movieName, duration, genre);

        log.trace("readMovie - method finished");

        return movie;
    }

    private Long readMovieId() throws IOException {
        log.trace("readMovieForDelete - method entered");
        System.out.println("Read movie {Identifier}");
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        Long id = Long.valueOf(bufferReader.readLine());

        log.trace("readMovieForDelete - method finished");

        return id;
    }
}
