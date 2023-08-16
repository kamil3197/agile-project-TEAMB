package org.kainos.ea.api;

import org.kainos.ea.cli.Capability;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.RequestCapability;
import org.kainos.ea.client.FailedToGetCapabilitiesException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.CapabilityDao;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;
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

    public int addCapability(RequestCapability capability) throws FailedToGetCapabilitiesException, SQLException{
        try {
            OptionalInt id = capabilityDao.addCapability(capability);

            return id.orElseThrow(() -> new FailedToGetCapabilitiesException());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException(e);
        }
    }
}
