package org.kainos.ea.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.RequestUser;
import org.kainos.ea.cli.Token;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.*;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.utils.PasswordHasher;
import org.kainos.ea.validator.RegisterValidator;

import java.util.Date;
import java.util.logging.Logger;
import java.sql.SQLException;

public class AuthService {

    public AuthService(AuthDao authDao, RegisterValidator registerValidator, DateService dateService) {
        this.authDao = authDao;
        this.registerValidator = registerValidator;
        this.dateService = dateService;
    }

    private final AuthDao authDao;
    private RegisterValidator registerValidator;
    private DateService dateService;

    public int createNewUser(RequestUser input) throws FailedToCreateNewUserException,
            FaliedToCreateUserWrongInputException {
        Logger logger = Logger.getLogger(this.getClass().getName());

        try {
            registerValidator.validate(input);
            return authDao.createNewUser(input);
        } catch (FaliedToCreateUserWrongInputException e) {
            logger.severe(e.getMessage());
            throw new FaliedToCreateUserWrongInputException("Failed to create user: " + e.getMessage());
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new FailedToCreateNewUserException();
        }
    }

    public String login(Login ClientCredentials)
            throws  FailedToGetUserException,
                    FailedToInsertTokenException,
                    WrongPasswordException,
                    WrongEmailException {
        
        User userDb = authDao.getUser(ClientCredentials.getEmail());
        if (userDb == null) {
            throw new WrongEmailException();
        }
        String providedPassword = ClientCredentials.getPassword();
        String hashedPasswordFromDB = userDb.getPassword();
        PasswordHasher passwordHasher = new PasswordHasher();
        boolean authResult = passwordHasher.authenticateUser(providedPassword, hashedPasswordFromDB);

        if (!authResult) {
            throw new WrongPasswordException();
        }
        Algorithm algorithm = Algorithm.HMAC256("NOT_HARDCODED_SECRET"); // TODO: change to asynchronous algorithm
        long now = dateService.getCurrentTime();
        long expiry = now + 3_600_000;
        String jwtToken = JWT.create()
                .withSubject(userDb.getEmail())
                .withClaim("user_id", userDb.getId())
                .withClaim("user_email", userDb.getEmail())
                .withClaim("user_role", userDb.getRole())
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(expiry))
                .sign(algorithm);
        return jwtToken;
    }
}