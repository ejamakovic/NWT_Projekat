package ba.nwt.keycard.RequestService.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtil {

    // password hash function
    public static String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
