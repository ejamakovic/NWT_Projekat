package ba.nwt.keycard.RoomService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "floors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Integer floorNumber;
    @ManyToOne
    private Building building;
    @OneToMany(mappedBy = "floor")
    private List<Room> rooms;



}