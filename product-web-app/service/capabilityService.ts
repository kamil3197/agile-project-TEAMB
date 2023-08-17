import axios from 'axios';
import Capability from '../model/capability.js';

class CapabilityService {
  private API_URL: string;

  constructor() {
    this.API_URL = process.env.API_URL || '';
  }

  async getAllCapabilities(): Promise<Capability[]> {
    try {
      const response = await axios.get(`${this.API_URL}/capability-leads`);
      return response.data;
    } catch (e) {
      throw new Error('Error - failed to get capabilities');
    }
  }
}

export default CapabilityService;
