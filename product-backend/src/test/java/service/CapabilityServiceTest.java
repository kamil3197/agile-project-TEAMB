package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToCreateCapabilityException;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.db.CapabilityDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDao = mock(CapabilityDao.class);
    CapabilityService capabilityService = new CapabilityService(capabilityDao);

    Capability capability = new Capability("d", "New capability", "Diego", "photo" +
            "test");
    @Test
    void createCapability_shouldReturnId_whenDaoReturnsId() throws SQLException, FailedToCreateCapabilityException {
        OptionalInt expectedResult = OptionalInt.of(1);
        Mockito.when(capabilityDao.addCapability(capability)).thenReturn(expectedResult);
        Mockito.when(capabilityDao.addCapability(capability)).thenReturn(expectedResult);

        OptionalInt result = OptionalInt.of(capabilityService.addCapability(capability));
        assertEquals(result, expectedResult);
    }

    @Test
    void createJobRole_shouldReturnError_whenDaoReturnsFail() throws SQLException, FailedToCreateCapabilityException {
        Mockito.when(capabilityDao.addCapability(capability)).thenReturn(OptionalInt.empty());

        assertThrows(FailedToCreateCapabilityException.class,
                () -> capabilityService.addCapability(capability));
    }

}
