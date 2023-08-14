package org.kainos.ea.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private static final int BCRYPT_WORKLOAD = 12; // You can adjust the workload factor as needed (e.g., 10 to 31)

    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
        return BCrypt.hashpw(plainPassword, salt);
    }

    public boolean authenticateUser(String providedPassword, String hashedPasswordFromDB) {
        return BCrypt.checkpw(providedPassword, hashedPasswordFromDB);
    }
}
