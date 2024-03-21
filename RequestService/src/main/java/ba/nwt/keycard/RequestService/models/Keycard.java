package ba.nwt.keycard.RequestService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.intellij.lang.annotations.Pattern;

@Entity
@Table(name = "keycards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keycard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    @JsonProperty("active")
    private Boolean isActive;

    @OneToOne(mappedBy = "keycard")
    @JsonBackReference
    private User user;

    public Keycard(Boolean b) {
        isActive = b;
    }

    public Keycard(Boolean b, User user1) {
        isActive = b;
        user = user1;
    }
}
