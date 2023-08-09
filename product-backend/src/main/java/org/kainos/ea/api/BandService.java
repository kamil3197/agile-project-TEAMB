package org.kainos.ea.api;

import org.kainos.ea.cli.Admin;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.db.BandDao;


import java.sql.SQLException;
import java.util.OptionalInt;


public class BandService {
    private BandDao bandDao;
    public BandService(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    public int createBand(Admin admin) throws FailedToCreateBandException, SQLException {
        try {
            OptionalInt id = bandDao.createBand(admin);

            return id.orElseThrow(() -> new FailedToCreateBandException());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException();
        }

    }
}