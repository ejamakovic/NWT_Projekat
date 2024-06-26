package ba.nwt.keycard.RoomService.models.Room;

import ba.nwt.keycard.RoomService.models.Floor.Floor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "floor_id" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name must not be null")
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    public void setName(String name) {
        this.name = name;
    }
}