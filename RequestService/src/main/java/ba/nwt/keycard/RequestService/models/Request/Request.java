package ba.nwt.keycard.RequestService.models.Request;

import ba.nwt.keycard.RequestService.models.Team.Team;
import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @NotNull(message = "Request must have roomId")
    private Long roomId;

    @ManyToOne
    @NotNull(message = "Request must have userId")
    @JsonBackReference
    private User user;

    public Request(Long roomId, User user) {
        this.user = user;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", user=" + (user != null ? user.getUsername() : null) +
                '}';
    }
}
