package ba.nwt.keycard.RequestService.models;

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
    @NotNull
    private String name;
    @OneToOne
    private User user;
}
