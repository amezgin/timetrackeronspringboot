package ru.mezgin.tracker.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mezgin.tracker.model.User;

/**
 * The class UserUtil.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 18.04.2018
 */
public class UserUtil {

    /**
     * Prepare user to save into repository. Encoded the password.
     *
     * @param user            user.
     * @param passwordEncoder encoder.
     * @return user with encoded password.
     */
    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
