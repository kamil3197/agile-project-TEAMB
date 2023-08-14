package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.AddJobRole;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class AddJobRoleServiceTest {

    JobRoleDao jobRoleDao = mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = mock(DatabaseConnector.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);
    AddJobRole addJobRole = new AddJobRole(
            "tomekk",
            "test",
            "Bloggs"
    );

    @Test
    void createJobRole_shouldReturnId_whenDaoReturnsId(int roleID) throws SQLException, FailedToCreateJobRoleException {
        OptionalInt expectedResult = OptionalInt.of(1);
        Mockito.when(jobRoleDao.createRole(addJobRole)).thenReturn(expectedResult);
        Mockito.when(jobRoleDao.createSpec(addJobRole, OptionalInt.of(roleID))).thenReturn(expectedResult);

        OptionalInt result = jobRoleService.createJobRole(addJobRole);
        assertEquals(result, expectedResult);
    }

    @Test
    void createJobRole_shouldReturnError_whenDaoReturnsFail() throws SQLException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleDao.createRole(addJobRole)).thenReturn(OptionalInt.empty());

        assertThrows(FailedToCreateJobRoleException.class,
                () -> jobRoleService.createJobRole(addJobRole));
    }

    @Test
    void createJobRole_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(jobRoleDao.createRole(addJobRole)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> jobRoleService.createJobRole(addJobRole));
    }
}




