package ba.nwt.keycard.PermissionService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull(message = "RoomId must not be null")
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "building_id")
    private Integer buildingId;
}
