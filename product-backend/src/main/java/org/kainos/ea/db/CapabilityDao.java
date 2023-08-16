package org.kainos.ea.db;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.cli.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Capability> getAllCapabilities() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, capability_name, lead_name, lead_photo, lead_message FROM " +
                "Capability;");
        List<Capability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getInt("id"),
                    rs.getString("capability_name"),
                    rs.getString("lead_name"),
                    rs.getBlob("lead_photo"),
                    rs.getString("lead_message")
            );
            capabilityList.add(capability);
        }

        return capabilityList;
    }
}
