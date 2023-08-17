package filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGetUserException;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.utils.AuthorisationFilter;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorisationFilterTest {
    private AuthDao authDaoMock = Mockito.mock(AuthDao.class);

    private AuthorisationFilter authorisationFilter = new AuthorisationFilter(authDaoMock);

    private User userAdmin = new User(
            1,
            "test@kainos.com",
            null,
            "Admin"
    );
    private User userEmployee = new User(
            1,
            "test@kainos.com",
            null,
            "Employee"
    );

    @Test
    void getUserFromToken_returnUser_whenTokenIsValid(){
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userAdmin.getEmail())
                .withClaim("user_id", userAdmin.getId())
                .withClaim("user_role", userAdmin.getRole())
                .sign(algorithm);

        User result = authorisationFilter.getUserFromToken(jwtToken);

        assertEquals(result.getId(), userAdmin.getId());
        assertEquals(result.getRole(), userAdmin.getRole());
        assertEquals(result.getEmail(), userAdmin.getEmail());
    }

    @Test
    void getUserFromToken_returnNull_whenTokenIsInvalid(){
        String jwtToken = "WRONG_TOKEN";

        User result = authorisationFilter.getUserFromToken(jwtToken);

        assertNull(result);
    }

    @Test
    void isValidAccessToken_returnTrue_whenTokenValid() throws FailedToGetUserException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userAdmin.getEmail())
                .withClaim("user_id", userAdmin.getId())
                .withClaim("user_role", userAdmin.getRole())
                .sign(algorithm);

        when(authDaoMock.getUser(userAdmin.getEmail())).thenReturn(userAdmin);
        boolean result = authorisationFilter.isValidAccessToken(jwtToken, "/");

        assertTrue(result);
    }

    @Test
    void isValidAccessToken_returnFalse_whenTokenExpired() throws FailedToGetUserException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userAdmin.getEmail())
                .withClaim("user_id", userAdmin.getId())
                .withClaim("user_role", userAdmin.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() - 100))
                .sign(algorithm);

        when(authDaoMock.getUser(userAdmin.getEmail())).thenReturn(userAdmin);
        boolean result = authorisationFilter.isValidAccessToken(jwtToken, "/");

        assertFalse(result);
    }

    @Test
    void isValidAccessToken_returnFalse_whenUserNotInDataBase() throws FailedToGetUserException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userAdmin.getEmail())
                .withClaim("user_id", userAdmin.getId())
                .withClaim("user_role", userAdmin.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() - 100))
                .sign(algorithm);

        when(authDaoMock.getUser(userAdmin.getEmail())).thenReturn(null);
        boolean result = authorisationFilter.isValidAccessToken(jwtToken, "/");

        assertFalse(result);
    }

    @Test
    void isValidAccessToken_returnFalse_whenWrongUserRole() throws FailedToGetUserException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userEmployee.getEmail())
                .withClaim("user_id", userEmployee.getId())
                .withClaim("user_role", userEmployee.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() - 100))
                .sign(algorithm);

        when(authDaoMock.getUser(userEmployee.getEmail())).thenReturn(userEmployee);
        boolean result = authorisationFilter.isValidAccessToken(jwtToken, "/admin");

        assertFalse(result);
    }

    @Test
    void isValidAccessToken_returnFalse_whenWrongUserId() throws FailedToGetUserException {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        String jwtToken = JWT.create()
                .withSubject(userEmployee.getEmail())
                .withClaim("user_id", 15)
                .withClaim("user_role", userEmployee.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() - 100))
                .sign(algorithm);

        when(authDaoMock.getUser(userEmployee.getEmail())).thenReturn(userEmployee);
        boolean result = authorisationFilter.isValidAccessToken(jwtToken, "/");

        assertFalse(result);
    }
}
