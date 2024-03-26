package ba.nwt.keycard.RoomService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "floors")
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

    // nema potrebe za ovim za sad...
    // mozda kasnije, ako bude potrebno dohvatati sve sobe na spratu
    /*
     * @OneToMany(mappedBy = "room")
     * private List<Room> rooms;
     */
}