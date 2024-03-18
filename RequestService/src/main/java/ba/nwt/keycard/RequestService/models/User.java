package ba.nwt.keycard.RequestService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.intellij.lang.annotations.Pattern;

import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String passwordHash;
    @NotNull
    private String role;
    private Long teamId;
    private Long keyId;
    @OneToMany(mappedBy = "user")
    private List<Request> requests;
    @OneToMany(mappedBy = "user")
    private List<Log> logs;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;




}
