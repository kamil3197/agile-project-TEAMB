package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToGetCapabilitiesException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.CapabilityDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDao = mock(CapabilityDao.class);
    CapabilityService capabilityService = new CapabilityService(capabilityDao);

    Capability capability = new Capability(1, "New capability", "Diego", "photo", "Hello from " +
            "test");

    @Test
    void getAllCapabilities_shouldReturnCapabilitiesList_whenDaoReturnCapabilitiesList() throws FailedToGetCapabilitiesException, SQLException {
        List<Capability> expectedResult = new ArrayList<>();
        expectedResult.add(capability);
        Mockito.when(capabilityDao.getAllCapabilities()).thenReturn(expectedResult);
        List<Capability> result = capabilityService.getAllCapabilities();
        assertEquals(result, expectedResult);
    }

    @Test
    void getAllCapabilities_shouldThrowFailedToGetCapabilitiesException_whenDaoThrowsSqlException() throws SQLException, FailedToGetCapabilitiesException {
        Mockito.when(capabilityDao.getAllCapabilities()).thenThrow(SQLException.class);
        assertThrows(FailedToGetCapabilitiesException.class, () -> capabilityService.getAllCapabilities());
    }

    @Test
    void getAllCapabilities_shouldReturnEmptyArray_whenDaoReturnEmptyArray() throws SQLException, FailedToGetCapabilitiesException {
        List<Capability> expectedResult = new ArrayList<>();
        Mockito.when(capabilityDao.getAllCapabilities()).thenReturn(expectedResult);
        List<Capability> result = capabilityService.getAllCapabilities();
        assertEquals(result, expectedResult);
    }
}
