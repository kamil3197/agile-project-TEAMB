package org.kainos.ea.db;

import org.kainos.ea.cli.Capability;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Capability> getAllCapabilities() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT capability_id, capability_name, lead_name, " +
                "lead_photo, lead_message FROM " +
                "Capability;");
        List<Capability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getInt("capability_id"),
                    rs.getString("capability_name"),
                    rs.getString("lead_name"),
                    rs.getString("lead_photo"),
                    rs.getString("lead_message")
            );
            capabilityList.add(capability);
        }

        return capabilityList;
    }
}
