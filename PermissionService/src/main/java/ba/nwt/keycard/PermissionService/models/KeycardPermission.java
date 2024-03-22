package ba.nwt.keycard.PermissionService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keycards_permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeycardPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "keycard_id")
    private Keycard keycard;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
