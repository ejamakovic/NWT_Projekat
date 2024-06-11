package ba.nwt.keycard.RequestService.models.Request;

import ba.nwt.keycard.RequestService.models.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="requests", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roomId", "userId"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Request must have roomId")
    private Long roomId;

    @ManyToOne
    @NotNull(message = "Request must have userId")
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
