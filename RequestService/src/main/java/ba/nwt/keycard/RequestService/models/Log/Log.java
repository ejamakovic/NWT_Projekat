package ba.nwt.keycard.RequestService.models.Log;

import ba.nwt.keycard.RequestService.models.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "RoomId is required")
    private Long roomId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate timestamp = LocalDate.now();;

    @NotNull(message = "Entry type is required")
    private String entryType;

    @ManyToOne
    @NotNull(message = "Log must have user")
    private User user;

    @NotNull(message = "Description is required")
    private String description;

}
