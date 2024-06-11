package ba.nwt.keycard.RequestService.models.Team;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<User> users;

    public Team(String name) {
        this.name = name;
    }

    public Team(String s, User user1) {
        name = s;
        manager = user1;
    }
}
