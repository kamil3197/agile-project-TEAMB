import chai from 'chai';
import BandValidator from '../validator/bandValidator.js';

const { expect } = chai;
const validator = new BandValidator();

describe('bandValidator', () => {
  describe('validateBand', () => {
    it('should return null when no errors', () => {
      const band = {
        name: 'tomek',
        level: 1,
        responsibilities: 'Chai',
      };

      expect(validator.validateBand(band)).to.be.null;
    });

    it('should return error when name is less than 1 char', () => {
      const band = {
        name: '',
        level: 1,
        responsibilities: 'Chai',
      };

      expect(validator.validateBand(band)).to.equal('Name must be at least 1 characters');
    });

    it('should return error when level is not in range 0-9', () => {
      const band = {
        name: 'tomek',
        level: 10,
        responsibilities: 'Chai',
      };

      expect(validator.validateBand(band)).to.equal('Level can be only 0-9');
    });
  });
});
