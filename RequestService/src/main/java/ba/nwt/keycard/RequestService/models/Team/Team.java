package ba.nwt.keycard.RequestService.models.Team;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name of the team is required")
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<User> users;

    @PreRemove
    public void preRemove() {
        for (User user : users) {
            user.setTeam(null);
        }
    }
}
