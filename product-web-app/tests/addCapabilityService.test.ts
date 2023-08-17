import axios, { AxiosError } from 'axios';
import chai from 'chai';
import MockAdapter from 'axios-mock-adapter';
import CapabilityService from '../service/capabilityService.js';

const { expect } = chai;
const mock = new MockAdapter(axios);
const capabilityService = new CapabilityService();

(capabilityService as any).API_URL = 'http://localhost:8080/api';

const capability = {
  capabilityName: 'test',
  leadName: 'test',
  leadPhoto: 'dwadadawdawdawdwa',
  leadMessage: 'Hello',
};

describe('CapabilityService', () => {
  describe('addCapability', () => {
    it('should add a capability and return it in the response', async () => {
        mock.onPost('http://localhost:8080/api/admin/capabilities').reply(200, capability);
        
        try {
          const result = await capabilityService.addCapability(capability);
          console.log('Result:', result); 
          expect(result).to.deep.equal(capability);
        } catch (error) {
          console.error('Error:', error);
        }
      });

      it('should throw an exception when a 500 error is returned', async () => {
        const mock = new MockAdapter(axios);
        mock.onPost('http://localhost:8080/api/admin/capabilities').reply(500);
        
        try {
          await capabilityService.addCapability(capability);
          expect.fail('Expected an error to be thrown.');
        } catch (error: unknown) {
          const axiosError = error as AxiosError; 
          expect(axiosError.message).to.equal('Could not create the capability.');
        }
      });
  });
});
