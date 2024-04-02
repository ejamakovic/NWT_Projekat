package ba.nwt.keycard.RequestService.models;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @NotNull(message = "Request must have teamId")
    private Team team;

    @ManyToOne
    @NotNull(message = "Request must have userId")
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

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", team=" + (team != null ? team.getName() : null) +
                ", user=" + (user != null ? user.getUsername() : null) +
                '}';
    }
}
