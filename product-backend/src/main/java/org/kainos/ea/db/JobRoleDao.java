package org.kainos.ea.db;

import org.kainos.ea.cli.AddJobRole;
import org.kainos.ea.cli.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class JobRoleDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<JobRole> getAllJobRoles() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT job_role_id, job_role_title FROM JobRoles;");
        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole(
                    rs.getInt("job_role_id"),
                    rs.getString("job_role_title")
            );
            jobRoleList.add(jobRole);
        }

        return jobRoleList;
    }

    public OptionalInt createRole(AddJobRole addJobRole) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO JobRoles (job_role_title) VALUES (?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, addJobRole.getRoleTitle());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            OptionalInt roleID = OptionalInt.of(rs.getInt(1));
            return roleID;
        }
        return OptionalInt.empty();
    }

    public OptionalInt createSpec(AddJobRole addJobRole, int roleID) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Specifications (role_id, summary, sharepoint_link) VALUES (?, ?, ?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, roleID);
        st.setString(2, addJobRole.getSummary());
        st.setString(3, addJobRole.getLink());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return OptionalInt.of(rs.getInt(1));
        }
        return OptionalInt.empty();
    }


}
