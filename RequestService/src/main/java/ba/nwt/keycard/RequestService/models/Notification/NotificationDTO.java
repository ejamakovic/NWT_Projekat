package ba.nwt.keycard.RequestService.models.Notification;

import ba.nwt.keycard.RequestService.models.User.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    @NotNull(message = "Notification must have userId")
    private Long userId;

    @NotNull(message = "Message in notification is required")
    @NotBlank(message = "Message in notification can't be empty")
    private String message;
}
