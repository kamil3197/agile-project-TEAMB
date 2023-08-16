import { Application, Request, Response } from 'express';
import {Capability}  from '../model/capability.js';
import CapabilityService  from '../service/capabilityService.js';
import xss from 'xss';

export default class CapabilityController {
  private capabilityServiceClass = new CapabilityService();

  init(app: Application): void {
    app.get('/admin/capabilities', async (req: Request, res: Response) => {
      res.render('add-capability', { title: 'Add Capability' });
    });

    app.post('/admin/capabilities', async (req: Request, res: Response) => {
      const data: Capability = req.body;
      const capabilityService = this.capabilityServiceClass;

      try {
        if (data.capabilityName && data.capabilityName !== undefined) {
          const sanitizedData: Capability = {
            capabilityName: xss(data.capabilityName),
            leadName: xss(data.leadName),
            leadPhoto: xss(data.leadPhoto),
            leadMessage: xss(data.leadMessage),
          };
          await capabilityService.addCapability(sanitizedData);
          res.locals.successMessage = 'Successfully added capability';
        }
      } catch (e) {
        res.locals.errorMessage = e;
      }
      res.render('add-capability', { title: 'Add Capability' });
    });
  }
}
