package ba.nwt.keycard.PermissionService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ba.nwt.keycard.PermissionService.models.Role;

import java.util.List;

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

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "floor_id")
    private Integer floorId;

    @Column(name = "building_id")
    private Integer buildingId;

    @OneToMany(mappedBy = "permission")
    private List<KeycardPermission> keycardPermission;

}
