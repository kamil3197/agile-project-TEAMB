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

    public Optional<JobSpecification> getJobSpecification(int role_id) throws SQLException, RoleNotExistException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT S.role_id, S.summary, S.sharepoint_link, JR.job_role_title, B.level, B.name\n" +
                "        FROM Specifications S\n" +
                "        INNER JOIN JobRoles JR ON S.role_id = JR.job_role_id\n" +
                "        INNER JOIN Band B ON JR.band_id = B.id\n" +
                "        WHERE S.id = " + role_id);


        if (rs.next()) {
            return Optional.of(new JobSpecification(
                    rs.getInt("role_id"),
                    rs.getString("job_role_title"),
                    rs.getString("summary"),
                    rs.getString("sharepoint_link"),
                    rs.getInt("B.level"),
                    rs.getString("B.name")));
        }
        return Optional.empty();
    }
}