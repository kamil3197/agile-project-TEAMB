package org.kainos.ea.api;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToCreateCapabilityException;
import org.kainos.ea.db.CapabilityDao;

import java.sql.SQLException;
import java.util.OptionalInt;

public class CapabilityService {
    private CapabilityDao capabilityDao;

    public CapabilityService(CapabilityDao capabilityDao) {
        this.capabilityDao = capabilityDao;
    }

    public int addCapability(Capability capability) throws FailedToCreateCapabilityException, SQLException {
        try {
            OptionalInt id = capabilityDao.addCapability(capability);

            return id.orElseThrow(() -> new FailedToCreateCapabilityException());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException(e);
        }
    }
}
