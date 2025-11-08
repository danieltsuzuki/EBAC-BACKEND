package danieltsuzuki.com.github.apirestfulparte2.service.exception;

public class SessionExpiredException extends RuntimeException {
    public SessionExpiredException(String message) {
        super(message);
    }
}
