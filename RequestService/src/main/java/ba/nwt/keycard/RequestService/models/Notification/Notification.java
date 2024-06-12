package ba.nwt.keycard.RequestService.models.Notification;

import ba.nwt.keycard.RequestService.models.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Notification must have userId")
    private User user;

    @NotNull(message = "Message in notification is required")
    @NotBlank(message = "Message in notification can't be empty")
    private String message;

}