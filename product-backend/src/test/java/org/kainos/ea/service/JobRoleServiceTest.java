package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    JobRole jobRole = new JobRole(1, "Tester and Quality Assurance");

    @Test
    void getAllJobRoles_shouldReturnJobRolesList_whenDaoReturnJobRolesList() throws FailedToGetJobRolesException, SQLException {
        List<JobRole> expectedResult = new ArrayList<>();
        expectedResult.add(jobRole);
        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(expectedResult);
        List<JobRole> result = jobRoleService.getAllJobRoles();
        assertEquals(result, expectedResult);
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToGetJobRolesException_whenDaoThrowsSqlException() throws SQLException, FailedToGetJobRolesException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);
        assertThrows(FailedToGetJobRolesException.class, () -> jobRoleService.getAllJobRoles());
    }

    @Test
    void getAllJobRoles_shouldReturnEmptyArray_whenDaoReturnEmptyArray() throws SQLException, FailedToGetJobRolesException {
        List<JobRole> expectedResult = new ArrayList<>();
        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(expectedResult);
        List<JobRole> result = jobRoleService.getAllJobRoles();
        assertEquals(result, expectedResult);
    }


}
