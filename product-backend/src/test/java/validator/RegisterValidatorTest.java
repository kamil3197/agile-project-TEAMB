package validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.RequestUser;
import org.kainos.ea.client.FaliedToCreateUserWrongInputException;
import org.kainos.ea.validator.RegisterValidator;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterValidatorTest {

    RegisterValidator registerValidator = new RegisterValidator();

    @Test
    public void isValidUser_shouldReturnTrue_whenValidDataProvided() throws FaliedToCreateUserWrongInputException {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "Test1234!",
                "Admin"
        );

        assertTrue(registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenPasswordHasNoUppercase() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "test1234!",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenPasswordHasNoLowercase() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "TEST1234!",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenPasswordHasNoSpecialCharacter() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "Test1234",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenPasswordTooShort() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "Test",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenPasswordContainsWhitespace() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "Test 1234!",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenNotCorrectRoleProvided() {
        RequestUser user = new RequestUser(
                "test@kainos.com",
                "Test1234!",
                "Hacker"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }

    @Test
    public void isValidUser_shouldThrowException_whenEmailNameContainsWhitespace() {
        RequestUser user = new RequestUser(
                "te st@kainos.com",
                "Test1234!",
                "Admin"
        );

        assertThrows(FaliedToCreateUserWrongInputException.class,
                () -> registerValidator.validate(user));
    }
}
