import axios from 'axios';
import Band from '../model/band.js';
import { GetBand } from '../model/getBand.js';

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

  async getAllBands(): Promise<GetBand[]> {
    try {
      const response = await axios.get(`${this.BASE_URL}/admin/getBand`);
      return response.data;
    } catch (e) {
      throw new Error('Error Band Service');
    }
  }
}
