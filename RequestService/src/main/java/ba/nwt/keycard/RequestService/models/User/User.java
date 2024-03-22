package ba.nwt.keycard.RequestService.models.User;

import ba.nwt.keycard.RequestService.models.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String role;

    private Boolean active;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Request> requests;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Log> logs;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notifications;

    @OneToOne
    @JsonBackReference
    private Keycard keycard;

    @ManyToOne
    @JsonBackReference
    private Team team;

    public User(String username, String email, String password, String role, Boolean active) {
        this.username = username;
        this.email = email;
        passwordHash = password;
        this.role = role;
        this.active = active;
    }

    public User(String username, String email, String password, String role, Boolean active, Keycard keycard, Team team) {
        this.username = username;
        this.email = email;
        passwordHash = password;
        this.role = role;
        this.active = active;
        this.keycard = keycard;
        this.team = team;
    }
}
