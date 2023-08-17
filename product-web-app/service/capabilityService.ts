import axios from 'axios';
import { Capability } from '../model/capability.js';

export default class CapabilityService {
  private API_URL: string;

  constructor() {
    this.API_URL = process.env.API_URL || '';
  }

  async addCapability(capability: Capability): Promise<void> {
    try {
      await axios.post(`${this.API_URL}/admin/capabilities`, capability);
    } catch (e) {
      throw new Error('Could not create the capability.');
    }
  }
}
