import axios, { AxiosError } from 'axios';
import chai from 'chai';
import MockAdapter from 'axios-mock-adapter';
import CapabilityService from '../service/capabilityService.js';

const { expect } = chai;
const capabilityService = new CapabilityService();
(capabilityService as any).API_URL = 'http://localhost:8080/api';

const capability = {
  capabilityId: 1,
  capabilityName: 'New one',
  leadName: 'Tom',
  leadPhoto: 'Photo here',
  leadMessage: 'Hello',
};

describe('CapabilityService', () => {
  describe('getAllCapabilities', () => {
    it('should return all capabilities from response', async () => {
      const mock = new MockAdapter(axios);
      const data = [capability];
      mock.onGet('http://localhost:8080/api/capability-leads').reply(200, data);
      const results = await capabilityService.getAllCapabilities();
      expect(results[0]).to.deep.equal(capability);
    });

    it('should throw exception when 500 error returned from axios', async () => {
      const mock = new MockAdapter(axios);
      mock.onGet('http://localhost:8080/api/capability-leads').reply(500);
      let error: any;
      try {
        await capabilityService.getAllCapabilities();
      } catch (e: unknown) {
        error = e as AxiosError;
      }
      expect(error.message).to.equal('Error - failed to get capabilities');
    });

    it('should return empty list/array when such is received', async () => {
      const mock = new MockAdapter(axios);
      const data: any[] = [];
      mock.onGet('http://localhost:8080/api/capability-leads').reply(200, data);
      const results = await capabilityService.getAllCapabilities();
      expect(results).to.deep.equal([]);
    });
  });
});
