package ba.nwt.keycard.RequestService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
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
    @OneToOne
    private Team team;

    public User(String username, String email, String password, String role, Boolean active) {
        this.username = username;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.active = active;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public void setPasswordHash(String password) {
        this.passwordHash = hashPassword(password);
    }
}
