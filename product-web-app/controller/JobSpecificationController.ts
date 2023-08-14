import { Application, Request, Response } from 'express';
import JobSpecificationService from '../service/JobSpecificationService.js';
import JobSpecification from '../model/JobSpecification.js';

export default class JobSpecificationController {
  init(app: Application) {
    const jobSpecificationService = new JobSpecificationService();
    app.get('/job-specification/:id', async (req: Request, res: Response) => {
      let data: JobSpecification | undefined;
      try {
        data = await jobSpecificationService.getJobSpecification(parseInt(req.params.id, 10));
      } catch (e) {
        res.redirect('/job-roles');
      }
      res.render('job-specification', { jobSpecification: data });
    });
  }
}
