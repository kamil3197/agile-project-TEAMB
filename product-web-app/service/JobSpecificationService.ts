import axios from 'axios';
import JobSpecification from '../model/JobSpecification.js';

export default class JobSpecificationService {
  private API_URL: string;

  constructor() {
    this.API_URL = process.env.API_URL || '';
  }

  async getJobSpecification(id: number): Promise<JobSpecification> {
    try {
      const response = await axios.get(`${this.API_URL}/job-specification/${id}`);
      return response.data;
    } catch (e) {
      throw new Error('cant get job specification');
    }
  }
}
