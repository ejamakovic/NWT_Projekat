package ba.nwt.keycard.RequestService.models.User;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public interface UserInterface {
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
    Collection<? extends GrantedAuthority> getAuthorities();
}
