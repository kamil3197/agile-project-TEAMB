package org.kainos.ea.cli.validator;

import org.kainos.ea.cli.Band;
import org.kainos.ea.client.FailedToInsertTokenException;

public class BandValidator {
    public boolean isValidBand(Band band) throws FailedToInsertTokenException.NameTooShortException {
        if (band.getName().length() > 1) {
            throw new FailedToInsertTokenException.NameTooShortException();
        }

        return true;
    }
}
