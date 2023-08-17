package org.kainos.ea.db;

import org.kainos.ea.cli.RequestCapability;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<RequestCapability> getAllCapabilities() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT capability_id, capability_name, lead_name, " +
                "lead_photo, lead_message FROM " +
                "Capability;");
        List<RequestCapability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            RequestCapability capability = new RequestCapability(
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
