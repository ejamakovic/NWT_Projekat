package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Entity
@Table(name="logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Value("${my.property:default}")
    private LocalDate timestamp;

    @NotNull
    @NotBlank(message = "Entry type is required")
    private String entryType;

    @ManyToOne
    private User user;

    @NotNull
    @NotBlank(message = "Description is required")
    private String description;

    public Log(LocalDate now, String entryType1, User user1, String description1) {
        timestamp = now;
        entryType = entryType1;
        user = user1;
        description = description1;
    }
}
