package ba.nwt.keycard.RequestService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
    private Keycard keycard;

    @ManyToOne
    @JsonBackReference
    private Team team;

    public User(String username, String email, String password, String role, Boolean active) {
        this.username = username;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.active = active;
    }

    public User(String username1, String mail, String password1, String role1, Boolean b, Keycard keycard1, Team team1) {
        username = username1;
        email = mail;
        passwordHash = hashPassword(password1);
        role = role1;
        active = b;
        keycard = keycard1;
        team = team1;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public void setPasswordHash(String password) {
        this.passwordHash = hashPassword(password);
    }
}
