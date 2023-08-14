import { Application, Request, Response } from 'express';
import Band from '../model/band.js';
import BandService from '../service/bandService.js';

export default class BandController {
  initializeRoutes(app: Application) {
    app.get('/admin/add-band', this.renderAddBandPage);
    app.post('/admin/add-band', this.addBand);
  }

  async renderAddBandPage(req: Request, res: Response) {
    res.render('add-band', { title: 'Add band' });
  }

  async addBand(req: Request, res: Response) {
    const data: Band = req.body;
    const bandService = new BandService();

    try {
      if (data.level !== undefined && data.level >= 1 && data.level <= 9) {
        await bandService.addBand(data);
        res.locals.successmessage = 'You added band!';
      } else {
        res.locals.errormessage = 'failed to add band, level should be between 1 and 9';
      }
    } catch (e) {
      res.locals.errormessage = 'Failed to add band';
    }

    res.render('add-band', { title: 'Add band' });
  }
}
