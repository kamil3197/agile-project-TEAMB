package org.kainos.ea.db;

import org.kainos.ea.cli.JobSpecification;
import org.kainos.ea.exception.RoleNotExistException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class JobSpecificationDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public Optional<JobSpecification> getJobSpecification(int role_id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT role_id, summary, sharepoint_link, job_role_title FROM Specifications INNER JOIN JobRoles ON role_id = job_role_id  Where id =" + role_id);


        if (rs.next()) {
            return Optional.of(new JobSpecification(
                    rs.getInt("role_id"),
                    rs.getString("job_role_title"),
                    rs.getString("summary"),
                    rs.getString("sharepoint_link")));
        }
        return Optional.empty();
    }
}