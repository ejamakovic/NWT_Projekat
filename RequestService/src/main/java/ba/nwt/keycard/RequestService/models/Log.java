package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long roomId;

    @Value("${my.property:default}")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate timestamp;

    @NotNull
    @NotBlank(message = "Entry type is required")
    private String entryType;

    @ManyToOne
    @NotNull(message = "Log must have userId")
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

    public Log(LocalDate now, String entryType1, User user1, String description1, Long l) {
        timestamp = now;
        entryType = entryType1;
        user = user1;
        description = description1;
        roomId = l;
    }
}
