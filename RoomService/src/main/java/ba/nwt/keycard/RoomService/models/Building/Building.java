package ba.nwt.keycard.RoomService.models.Building;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buildings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name must not be null")
    @Column(name = "name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // nema potrebe za ovim za sad...
    // mozda kasnije, ako bude potrebno dohvatati sve spratove u zgradi
    /*
     * @OneToMany(mappedBy = "building")
     * private List<Floor> floors;
     */

}