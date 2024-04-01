package ba.nwt.keycard.RoomService.models.Floor;

import java.util.List;

import ba.nwt.keycard.RoomService.models.Building.Building;
import ba.nwt.keycard.RoomService.models.Room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "floors", uniqueConstraints = @UniqueConstraint(columnNames = { "floor_number", "building_id" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Floor number must not be null")
    @Column(name = "floor_number")
    private Integer floorNumber;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    // potreban OneToMany zbog Cascade.ALL (ne moze na ManyToOne)
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

}