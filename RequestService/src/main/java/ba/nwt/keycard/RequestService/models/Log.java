package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private LocalDate timestamp;

    private String entryType;

    @ManyToOne
    @JsonBackReference
    private User user;

    private String description;

    public Log(LocalDate now, String entryType1, User user1, String description1) {
        timestamp = now;
        entryType = entryType1;
        user = user1;
        description = description1;
    }
}
