package ba.nwt.keycard.RequestService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String name;
    private Long managerId;

    @OneToMany(mappedBy = "team")
    private List<User> users;

    public Team(String s, Long managerId1) {
        name = s;
        managerId = managerId1;
    }
    public Team(String s) {
        name = s;
    }

}
