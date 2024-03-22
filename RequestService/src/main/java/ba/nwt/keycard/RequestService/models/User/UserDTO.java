package ba.nwt.keycard.RequestService.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Email
    @Pattern(regexp = "^.*@.*\\..*", message = "Email must be in xxxx@xxxx.xx format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9@#$%^&+=!]).{8,}$", message = "Password must contain one upper case letter, one lower case letter, one special character, one number, and must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    @Value("${my.property:default}")
    private Boolean active;

}

