package ba.nwt.keycard.RequestService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private long expirationTimeMillis;
    private String username;
    private String role;
}
