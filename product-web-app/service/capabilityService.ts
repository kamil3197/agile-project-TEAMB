import axios from 'axios';
import Capability from '../model/capability.js';

const CapabilityExample: Capability[] = [
  {
    capabilityId: 1,
    capabilityName: 'New capability',
    leadName: 'Sweet Kitty',
    leadPhoto:
      'https://www.shutterstock.com/shutterstock/photos/1918637450/display_1500/stock-photo-kiev-ukraine-april-hello-kitty-on-paper-hello-kitty-is-a-cartoon-character-produced-1918637450.jpg',
    leadMessage: 'Nice job!',
  },
  {
    capabilityId: 2,
    capabilityName: 'New capability',
    leadName: 'Sweet Kitty',
    leadPhoto:
      'https://www.shutterstock.com/shutterstock/photos/1918637450/display_1500/stock-photo-kiev-ukraine-april-hello-kitty-on-paper-hello-kitty-is-a-cartoon-character-produced-1918637450.jpg',
    leadMessage: 'Nice job!',
  },
];

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

  // async getAllCapabilities(): Promise<Capability[]> {
  //   return CapabilityExample;
  // }
}

export default CapabilityService;
