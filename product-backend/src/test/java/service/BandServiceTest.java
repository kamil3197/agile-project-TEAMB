package service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.FailedToCreateBandException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.api.BandService;
import org.kainos.ea.db.DatabaseConnector;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class BandServiceTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    Connection conn = Mockito.mock(Connection.class);

    BandService bandService = new BandService(bandDao);

    Band band = new Band(
            "tomekk",
            2,
            "Bloggs"
    );

    @Test
    void createBand_shouldReturnId_whenDaoReturnsId() throws SQLException, FailedToCreateBandException {
        int expectedResult = 1;
        Mockito.when(bandDao.createBand(band)).thenReturn(OptionalInt.of(expectedResult));

        int result = bandService.createBand(band);

        assertEquals(result, expectedResult);
    }

    @Test
    void createBand_shouldReturnError_whenDaoReturnsFail() throws SQLException, FailedToCreateBandException {
        Mockito.when(bandDao.createBand(band)).thenReturn(OptionalInt.empty());

        assertThrows(FailedToCreateBandException.class,
                () -> bandService.createBand(band));
    }
    @Test
    void createBand_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(bandDao.createBand(band)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> bandService.createBand(band));
    }
}




