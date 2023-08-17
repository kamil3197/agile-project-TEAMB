package org.kainos.ea.db;

import org.kainos.ea.cli.Capability;

import java.sql.*;
import java.util.Base64;
import java.util.OptionalInt;

public class CapabilityDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public OptionalInt addCapability(Capability capability) throws SQLException {
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
