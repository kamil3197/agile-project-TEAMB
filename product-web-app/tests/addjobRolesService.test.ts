import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import chai, { assert } from 'chai';
import JobRoleService from '../service/jobRolesService.js';

const addJobRole = {
  title: 'Kamil',

  summary: 'something',

  sharepoint_link: 'chai',
};

const { expect } = chai;
const jobRolesService = new JobRoleService();
(jobRolesService as any).API_URL = 'http://localhost:8080/api';

describe('JobRoleService', () => {
  describe('createJobRoles', () => {
    it('should return error 500 when could not create job role', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://mock.pl/admin/job-roles', addJobRole).reply(500);

      try {
        await jobRolesService.createJobRoles(addJobRole);
      } catch (error) {
        const errorMessage: string = error instanceof Error ? error.message : String(error);

        expect(errorMessage).to.equal('Could not create job role');
      }
    });

    it('should return error 400 when pass invalid data', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://mock.pl/admin/job-roles', addJobRole).reply(400);

      try {
        await jobRolesService.createJobRoles(addJobRole);
      } catch (error) {
        const errorMessage: string = error instanceof Error ? error.message : String(error);

        expect(errorMessage).to.equal('Could not create job role');
      }
    });

    it('should return 201 when pass valid data', async () => {
      const mock = new MockAdapter(axios);

      mock.onPost('http://localhost:8080/api/admin/job-roles', addJobRole).reply(201);
      try{
        await jobRolesService.createJobRoles(addJobRole);
      } catch {
        assert.fail('exception shouldn\' be thrown')
      }
    });
  });
});
