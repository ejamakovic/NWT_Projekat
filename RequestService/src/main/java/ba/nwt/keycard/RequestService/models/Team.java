package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @NotBlank(message = "Name of team is  required")
    private String name;

    private Long managerId;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<User> users;

    public Team(String name) {
        this.name = name;
    }

}
