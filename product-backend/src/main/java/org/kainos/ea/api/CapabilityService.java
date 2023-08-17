package org.kainos.ea.api;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToGetCapabilitiesException;
import org.kainos.ea.db.CapabilityDao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class CapabilityService {
    private CapabilityDao capabilityDao;

    public CapabilityService(CapabilityDao capabilityDao) {
        this.capabilityDao = capabilityDao;
    }

    public List<Capability> getAllCapabilities() throws FailedToGetCapabilitiesException {
        Logger logger = Logger.getLogger(this.getClass().getName());

        try {
            List<Capability> capabilityList = capabilityDao.getAllCapabilities();
            return capabilityList;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new FailedToGetCapabilitiesException();
        }
    }
}
