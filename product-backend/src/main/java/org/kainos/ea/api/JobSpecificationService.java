package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpecification;
import org.kainos.ea.client.FailedToInsertTokenException;
import org.kainos.ea.client.FaliedToCreateUserWrongInputException;
import org.kainos.ea.db.JobSpecificationDao;

import java.sql.SQLException;

public class JobSpecificationService {
    private final JobSpecificationDao jobSpecificationDao;

    public JobSpecificationService(JobSpecificationDao jobSpecificationDao) {
        this.jobSpecificationDao = jobSpecificationDao;
    }

    public JobSpecification getJobSpecification(int roleId) throws SQLException, FailedToInsertTokenException.DatabaseConnectionException, FaliedToCreateUserWrongInputException.RoleNotExistException {
        return jobSpecificationDao.getJobSpecification(roleId).orElseThrow(FaliedToCreateUserWrongInputException.RoleNotExistException::new);
    }
}




