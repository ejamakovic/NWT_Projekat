package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank(message = "Name of the team is required")
    @Column(unique = true)
    private String name;

    @OneToOne(mappedBy = "managerTeam")
    @JsonBackReference
    private User manager;

    @PreRemove
    private void removeManagerTeam() {
        if (manager != null) {
            manager.setManagerTeam(null);
        }
    }

    @OneToMany(mappedBy = "team")
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
