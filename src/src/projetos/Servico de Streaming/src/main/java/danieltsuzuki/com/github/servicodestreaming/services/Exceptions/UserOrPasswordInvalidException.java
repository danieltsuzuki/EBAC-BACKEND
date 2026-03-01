package danieltsuzuki.com.github.servicodestreaming.services.Exceptions;

public class UserOrPasswordInvalidException extends RuntimeException {
    public UserOrPasswordInvalidException(String message) {
        super(message);
    }
}
