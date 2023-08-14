package org.kainos.ea.validator;

import org.kainos.ea.cli.RequestUser;
import org.kainos.ea.client.FaliedToCreateUserWrongInputException;

public class RegisterValidator {
        // Minimum password length (you can adjust this as needed)
        private static final int minLength = 8;

        public boolean validate (RequestUser input) throws FaliedToCreateUserWrongInputException {
            String email = input.getEmail();
            String password = input.getPassword();
            String role = input.getRole();

            if (!role.equals("Admin") && !role.equals("Employee")) {
                throw new FaliedToCreateUserWrongInputException("Correct role should be specified.");
            }

            if (email.trim().length() == 0 ) {
                throw new FaliedToCreateUserWrongInputException("Email must be provided.");
            }

            if (email.contains(" ")) {
                throw new FaliedToCreateUserWrongInputException("Email cannot contain whitespace.");
            }

            if (!email.endsWith("@kainos.com")) {
                throw new FaliedToCreateUserWrongInputException("Email has wrong domain.");
            }

            if (password.length() < minLength) {
                throw new FaliedToCreateUserWrongInputException("Password must be at least " + minLength + " characters long.");
            }

            if (!password.matches(".*[a-z].*")) {
                throw new FaliedToCreateUserWrongInputException("Password must contain at least one lowercase letter.");
            }

            if (!password.matches(".*[A-Z].*")) {
                throw new FaliedToCreateUserWrongInputException("Password must contain at least one uppercase letter.");
            }

            if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                throw new FaliedToCreateUserWrongInputException("Password must contain at least one special character.");
            }

            if (password.contains(" ")) {
                throw new FaliedToCreateUserWrongInputException("Password cannot contain whitespace.");
            }

            return true;
        }
    }

