import axios from 'axios';
import Band from '../model/band.js';

export default class BandService {
  private BASE_URL: String;

  constructor() {
    this.BASE_URL = process.env.API_URL || '';
  }

  async addBand(band: Band): Promise<Number> {
    try {
      const response = await axios.post(`${this.BASE_URL}/admin/band`, band);
      return response.status;
    } catch (e) {
      throw new Error('Could not create band');
    }
  }
}
