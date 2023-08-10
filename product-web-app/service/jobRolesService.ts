import axios from 'axios';
import { JobRole } from '../model/jobRole.js';
import { AddJobRole } from '../model/addJobRole.js';

export default class JobRoleService {
  private API_URL: string;

  constructor() {
    this.API_URL = process.env.API_URL || '';
  }

  async getAllJobRoles(): Promise<JobRole[]> {
    try {
      const response = await axios.get(`${this.API_URL}/job-roles`);
      return response.data;
    } catch (e) {
      throw new Error('Error jobRoleService');
    }
  }

  async createJobRoles(addJobRole: AddJobRole): Promise<Number> {
    try {
      const response = await axios.post(`${this.API_URL}/admin/job-roles`, addJobRole);
      return response.status;

    } catch (e) {
      throw new Error('Could not create job role');
    }
  }
}
