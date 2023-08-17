import { Application, Request, Response } from 'express';
import xss from 'xss';
import { Capability } from '../model/capability.js';
import CapabilityService from '../service/capabilityService.js';

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
        if (data.capabilityName && data.leadName !== undefined) {
          const sanitizedData: Capability = {
            leadName: xss(data.leadName),
            capabilityName: xss(data.capabilityName),
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
