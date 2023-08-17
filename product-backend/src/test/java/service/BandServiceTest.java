package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.GetBand;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)

public class BandServiceTest {
    BandDao bandDao = mock(BandDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);
    Connection conn = mock(Connection.class);

    BandService bandService = new BandService(bandDao);

    Band band = new Band(
            "tomekk",
            2,
            "Bloggs"
    );
    GetBand getBand = new GetBand(
            10,
            "manager",
            5
    );

    @Test
    void getAllBands_shouldReturnBandsList_whenDaoReturnBandsList() throws FailedToGetBandsException, SQLException, FailedToGetJobRolesException {
        List<GetBand> expectedResult = new ArrayList<>();
        expectedResult.add(getBand);
        Mockito.when(bandDao.getAllBands()).thenReturn(expectedResult);
        List<GetBand> result = bandService.getAllBands();
        assertEquals(result, expectedResult);
    }

    @Test
    void getAllBands_shouldThrowFailedToGetJBandsException_whenDaoThrowsSqlException() throws SQLException, FailedToGetBandsException {
        Mockito.when(bandDao.getAllBands()).thenThrow(SQLException.class);
        assertThrows(FailedToGetBandsException.class, () -> bandService.getAllBands());
    }

    @Test
    void getAllBands_shouldReturnEmptyArray_whenDaoReturnEmptyArray() throws SQLException, FailedToGetBandsException {
        List<GetBand> expectedResult = new ArrayList<>();
        Mockito.when(bandDao.getAllBands()).thenReturn(expectedResult);
        List<GetBand> result = bandService.getAllBands();
        assertEquals(result, expectedResult);
    }

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




