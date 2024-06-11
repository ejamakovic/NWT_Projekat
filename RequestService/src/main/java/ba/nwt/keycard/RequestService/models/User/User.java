package ba.nwt.keycard.RequestService.models.User;

import ba.nwt.keycard.RequestService.models.*;
import ba.nwt.keycard.RequestService.models.Team.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserInterface, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String role;

    @NotNull
    private Boolean active;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Request> requests;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Log> logs;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Notification> notifications;

    //@OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "keycard_id")
    private Keycard keycard;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
    @JsonManagedReference
    private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "team_id")
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                // Include other relevant fields but avoid cyclic references
                '}';
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the role into a collection of GrantedAuthority objects
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
