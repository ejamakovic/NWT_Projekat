package ba.nwt.keycard.RoomService.controllers.ErrorHandler.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}