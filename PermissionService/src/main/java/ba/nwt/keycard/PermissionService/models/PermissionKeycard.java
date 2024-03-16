import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions_keycards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionKeycard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "permissions_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "keycard_id")
    private Keycard keycard;
}
