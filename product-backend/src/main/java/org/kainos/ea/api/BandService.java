package org.kainos.ea.api;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.client.FailedToCreateCapabilityException;
import org.kainos.ea.db.BandDao;


import java.sql.SQLException;
import java.util.OptionalInt;


public class BandService {
    private BandDao bandDao;
    public BandService(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    public int createBand(Band band) throws FailedToCreateBandException, SQLException {
        try {
            OptionalInt id = bandDao.createBand(band);

            return id.orElseThrow(() -> new FailedToCreateBandException());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException();
        }

    }
    public int addCapability(Capability capability) throws FailedToCreateCapabilityException, SQLException{
        try {
            OptionalInt id = bandDao.addCapability(capability);

            return id.orElseThrow(() -> new FailedToCreateCapabilityException());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException(e);
        }
    }
}