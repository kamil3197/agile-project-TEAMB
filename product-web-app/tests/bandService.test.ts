import axios, { AxiosError } from 'axios';
import MockAdapter from 'axios-mock-adapter';
import chai from 'chai';
import BandService from '../service/bandService.js';

const band = {
  name: 'Kamil',

  level: 1,

  responsibilities: 'Chai',
};

const getBand = {
  id: 10,
  name: 'manager',
  level: 5,
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

  describe('getAllBands', () => {
    it('should return all bands from response', async () => {
      const mock = new MockAdapter(axios);
      const data = [getBand];
      mock.onGet('http://mock.pl/admin/getBand').reply(200, data);
      const results = await bandService.getAllBands();
      expect(results[0]).to.deep.equal(getBand);
    });

    it('should throw exception when 500 error returned from axios', async () => {
      const mock = new MockAdapter(axios);
      mock.onGet('http://mock.pl/admin/getBand').reply(500);
      let error: any;
      try {
        await bandService.getAllBands();
      } catch (e: unknown) {
        error = e as AxiosError;
      }
      expect(error.message).to.equal('Error Band Service');
    });

    it('should return empty list/array when such is received', async () => {
      const mock = new MockAdapter(axios);
      const data: any[] = [];
      mock.onGet('http://mock.pl/admin/getBand').reply(200, data);
      const results = await bandService.getAllBands();
      expect(results).to.deep.equal([]);
    });
  });
});
