package org.kainos.ea.api;

import org.kainos.ea.cli.AddJobRole;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;

public class JobRoleService {
    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRolesException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();
            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRolesException();
        }
    }

    public OptionalInt createJobRole(AddJobRole addJobRole) throws FailedToCreateJobRoleException, SQLException {
        try {

            OptionalInt roleID = jobRoleDao.createRole(addJobRole);
            if (!roleID.isPresent()) {
                throw new FailedToCreateJobRoleException();
            }
            OptionalInt specID = jobRoleDao.createSpec(addJobRole, roleID.getAsInt());
            if (specID.isPresent()) {
                return roleID;
            } else {
                throw new FailedToCreateJobRoleException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException();
        }
    }
}
