package ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}