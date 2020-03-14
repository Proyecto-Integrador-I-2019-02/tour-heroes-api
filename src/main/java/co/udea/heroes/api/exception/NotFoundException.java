package co.udea.heroes.api.exception;

/**
 * Exception for duplicated data in the application
 */
public class NotFoundException extends GeneralRuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String translationKey) {
        super(message, translationKey);
    }

}
