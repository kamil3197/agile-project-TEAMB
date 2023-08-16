package org.kainos.ea.db;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.GetBand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class BandDao {
    private static DatabaseConnector databaseConnector = new DatabaseConnector();

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

    public static List<GetBand> getAllBands() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, name, level FROM Band;");
        List<GetBand> getBandList = new ArrayList<>();

        while (rs.next()) {
            GetBand getBand = new GetBand(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("level")
            );
            getBandList.add(getBand);
        }

        return getBandList;
    }

}
