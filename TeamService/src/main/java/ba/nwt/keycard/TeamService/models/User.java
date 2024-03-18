package ba.nwt.keycard.TeamService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String passwordHash;
    private String role;
    private Long teamId;
    private Long keyId;
    @OneToOne(mappedBy = "user")
    private Keycard team;
    @OneToOne(mappedBy = "user")
    private Keycard keycard;
}
