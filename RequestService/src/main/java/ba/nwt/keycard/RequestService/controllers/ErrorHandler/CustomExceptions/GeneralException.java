package ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
}