import axios, { AxiosError } from 'axios';
import chai from 'chai';
import MockAdapter from 'axios-mock-adapter';
import JobSpecificationService from '../service/JobSpecificationService.js';

const { expect } = chai;
const jobSpecificationService = new JobSpecificationService();
(jobSpecificationService as any).API_URL = 'http://localhost:8080/api';

describe('JobSpecificationService', () => {
  describe('getJobSpecification', () => {
    it('should return the job spec  from response', async () => {
      const mock = new MockAdapter(axios);
      const roleId = 1;
      mock.onGet(`http://localhost:8080/api/job-specification/${roleId}`).reply(200, roleId);
      const results = await jobSpecificationService.getJobSpecification(1);
      expect(results).to.deep.equal(roleId);
    });

    it('should throw exception when 404 error returned from axios', async () => {
      const mock = new MockAdapter(axios);
      const roleId = 200;
      mock.onGet(`/api/job-specification/${roleId}`).reply(404);
      let error: any;
      try {
        await jobSpecificationService.getJobSpecification(200);
      } catch (e: unknown) {
        error = e as AxiosError;
      }
      expect(error.message).to.equal('cant get job specification');
    });
  });
});
