package ro.ubb.cinema.core.domain.exceptions;

public class CinemaBaseException extends RuntimeException {
    public CinemaBaseException(String message) {
        super(message);
    }

    public CinemaBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CinemaBaseException(Throwable cause) {
        super(cause);
    }
}
