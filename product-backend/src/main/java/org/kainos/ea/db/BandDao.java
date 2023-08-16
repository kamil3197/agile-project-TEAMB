package org.kainos.ea.db;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.Capability;

import java.sql.*;
import java.util.OptionalInt;

public class BandDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public OptionalInt createBand(Band band) throws SQLException {
        Connection c = databaseConnector.getConnection();


        String insertStatement = "INSERT INTO Band (name, level, responsibilities) VALUES (?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, band.getName());
        st.setInt(2, band.getLevel());
        st.setString(3, band.getResponsibilities());


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return OptionalInt.of(rs.getInt(1));
        }
        return OptionalInt.empty();
    }

    public OptionalInt addCapability(Capability capability) throws  SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO Capability (capabilityName, leadName, leadPhoto, leadMessage) VALUES (?, ?, ?, ?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1,capability.getCapabilityName());
        st.setString(2,capability.getLeadName());
        st.setBlob(3,capability.getLeadPhoto());
        st.setString(4,capability.getLeadMessage());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return OptionalInt.of(rs.getInt(1));
        }
        return OptionalInt.empty();
    }
}

