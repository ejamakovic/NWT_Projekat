package ba.nwt.keycard.PermissionService.controllers.ErrorHandler.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}