package org.kainos.ea.api;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.GetBand;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.db.BandDao;

import java.sql.SQLException;
import java.util.List;
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

    public List<GetBand> getAllBands() throws FailedToGetBandsException, SQLException {
        try {
            List<GetBand> getBandList = bandDao.getAllBands();
            return getBandList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetBandsException();
        }
    }

}