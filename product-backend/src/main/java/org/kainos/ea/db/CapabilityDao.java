package org.kainos.ea.db;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.RequestCapability;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.OptionalInt;

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

    public OptionalInt addCapability(RequestCapability capability) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO Capability (capability_name, lead_name, lead_photo, lead_message) VALUES (?, ?, ?, ?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, capability.getCapabilityName());
        st.setString(2, capability.getLeadName());
        byte[] leadPhotoBytes = Base64.getDecoder().decode(capability.getLeadPhoto());
        st.setBytes(3, leadPhotoBytes);
        st.setString(4, capability.getLeadMessage());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return OptionalInt.of(rs.getInt(1));
        }
        return OptionalInt.empty();
    }
}
