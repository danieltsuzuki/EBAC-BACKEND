package danieltsuzuki.com.github.apirestfulparte2.service.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
