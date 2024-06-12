package ba.nwt.keycard.RequestService.models.User;

import ba.nwt.keycard.RequestService.models.*;
import ba.nwt.keycard.RequestService.models.Log.Log;
import ba.nwt.keycard.RequestService.models.Notification.Notification;
import ba.nwt.keycard.RequestService.models.Request.Request;
import ba.nwt.keycard.RequestService.models.Team.Team;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @JsonIgnore
    private String password;

    @NotNull
    private String role;

    @NotNull
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JsonIgnore
    private List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JsonIgnore
    private List<Log> logs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JsonIgnore
    private List<Notification> notifications;

    @JoinColumn(name = "keycard_id")
    private Long keycardId;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "manager")
    private List<Team> teams;

    @PreRemove
    public void preRemove() {
        for (Team team : teams) {
            team.setManager(null);
        }
    }

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public User(String username, String email, String password, String role, Boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
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
        return List.of(new SimpleGrantedAuthority(role));
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
