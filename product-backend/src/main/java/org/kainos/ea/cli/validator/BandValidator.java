package org.kainos.ea.cli.validator;

import org.kainos.ea.cli.Band;
import org.kainos.ea.exception.NameTooShortException;

public class BandValidator {
    public boolean isValidBand(Band band) throws NameTooShortException{
        if (band.getName().length() > 1) {
            throw new NameTooShortException();
        }

        return true;
    }
}
