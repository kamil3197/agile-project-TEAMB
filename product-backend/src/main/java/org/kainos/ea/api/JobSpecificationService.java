package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpecification;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecificationDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.RoleNotExistException;

import java.sql.SQLException;

public class JobSpecificationService {
    private final JobSpecificationDao jobSpecificationDao;

    public JobSpecificationService(JobSpecificationDao jobSpecificationDao) {
        this.jobSpecificationDao = jobSpecificationDao;
    }

    public JobSpecification getJobSpecification(int roleId) throws SQLException, DatabaseConnectionException, RoleNotExistException {
        return jobSpecificationDao.getJobSpecification(roleId).orElseThrow(RoleNotExistException::new);
    }
}




