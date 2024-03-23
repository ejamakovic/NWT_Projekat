package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long roomId;

    @ManyToOne
    private Team team;

    @ManyToOne
    private User user;

    public Request(User user1) {
        user = user1;
    }

    public Request(User user, Long roomId) {
        this.user = user;
        this.roomId = roomId;
    }

    public Request(User user, Long roomId, Team team) {
        this.user = user;
        this.roomId = roomId;
        this.team = team;
    }
}
