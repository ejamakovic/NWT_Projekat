package ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
}
