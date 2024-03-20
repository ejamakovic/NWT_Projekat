package ba.nwt.keycard.PermissionService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "keycards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keycard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_active")
    @JsonProperty("active")
    private Boolean isActive;


    @OneToMany(mappedBy = "keycard")
    private List<KeycardPermission> keycardPermission;

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
