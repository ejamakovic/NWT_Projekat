package ba.nwt.keycard.RequestService.models;

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
    private User user;
}
