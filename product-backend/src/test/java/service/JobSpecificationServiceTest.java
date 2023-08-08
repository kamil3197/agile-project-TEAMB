package service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobSpecificationService;
import org.kainos.ea.cli.JobSpecification;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToJobSpecificationException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecificationDao;
import org.kainos.ea.exception.RoleNotExistException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JobSpecificationServiceTest {
    JobSpecificationDao jobSpecificationDao = Mockito.mock(JobSpecificationDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobSpecificationService jobSpecificationService = new JobSpecificationService(jobSpecificationDao,databaseConnector);
    Connection conn;


    @Test
    void getJobSpecification_shouldReturnSpecification_whenDaoReturnsSpecification() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int roleId = 1;
        JobSpecification specification_list = new JobSpecification(1,"test","test","test");

        Mockito.when(jobSpecificationDao.getJobSpecification(conn, roleId)).thenReturn(specification_list);
        JobSpecification specification = jobSpecificationService.getJobSpecification(roleId);
        assertEquals(specification, specification_list);

    }
    @Test
    void getJobSpecification_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int roleId = 1;
        Mockito.when(jobSpecificationDao.getJobSpecification(conn, roleId)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> jobSpecificationService.getJobSpecification(roleId));

    }

    @Test
    void getJobSpecification_shouldThrowUserDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int roleId = -1;
        Mockito.when(jobSpecificationDao.getJobSpecification(conn, roleId)).thenThrow(RoleNotExistException.class);
        assertThrows(RoleNotExistException.class, () -> jobSpecificationService.getJobSpecification(roleId));

    }

}
