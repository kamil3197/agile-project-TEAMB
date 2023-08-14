import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import chai from 'chai';
import BandService from '../service/bandService.js';

const band = {
  name: 'Kamil',

  level: 1,

  responsibilities: 'Chai',
};

const { expect } = chai;
const bandService = new BandService();
(bandService as any).BASE_URL = 'http://mock.pl';

describe('bandService', () => {
  describe('addBand', () => {
    it('should return error 500 when could not create band', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://mock.pl/admin/band', band).reply(500);

      try {
        await bandService.addBand(band);
      } catch (error) {
        const errorMessage: string = error instanceof Error ? error.message : String(error);

        expect(errorMessage).to.equal('Could not create band');
      }
    });

    it('should return error 400 when pass invalid data', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://mock.pl/admin/band', band).reply(400);

      try {
        await bandService.addBand(band);
      } catch (error) {
        const errorMessage: string = error instanceof Error ? error.message : String(error);

        expect(errorMessage).to.equal('Could not create band');
      }
    });

    it('should return 201 when pass valid data', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://mock.pl/admin/band', band).reply(201);

      const response = await bandService.addBand(band);
      expect(response).to.equal(201);
    });
  });
});
