package ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions;

public class UnathorizedAccessException extends RuntimeException {
    public UnathorizedAccessException(String message) {
        super(message);
    }
}