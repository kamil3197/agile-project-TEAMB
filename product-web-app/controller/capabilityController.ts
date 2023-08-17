import { Application, Request, Response } from 'express';
import CapabilityService from '../service/capabilityService.js';
import Capability from '../model/capability.js';

export default class CapabilityController {
  private capabilityServiceClass = new CapabilityService();

  init(app: Application): void {
    app.get('/capability-leads', async (req: Request, res: Response) => {
      const capabilityService = this.capabilityServiceClass;
      try {
        const capabilities: Capability[] = await capabilityService.getAllCapabilities();
        res.render('capability-leads', { capabilities, title: 'View capabilities' });
      } catch (e) {
        res.status(500).send('Error - failed to get capabilities');
      }
    });
  }
}
