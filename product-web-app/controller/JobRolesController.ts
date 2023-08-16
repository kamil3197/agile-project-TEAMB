import { Application, Request, Response } from 'express';
import xss from 'xss';
import { JobRole } from '../model/jobRole.js';
import JobRoleService from '../service/jobRolesService.js';
import { AddJobRole } from '../model/addJobRole.js';

export default class JobRolesController {
  private jobRoleServiceClass = new JobRoleService();

  init(app: Application): void {
    app.get('/job-roles', async (req: Request, res: Response) => {
      const jobRoleService = this.jobRoleServiceClass;
      try {
        const jobRoles: JobRole[] = await jobRoleService.getAllJobRoles();
        res.render('job-roles', { jobRoles, title: 'Job Roles' });
      } catch (e) {
        res.status(500).send('Error JobRolesController');
      }
    });

    app.get('/admin/job-roles', async (req: Request, res: Response) => {
      res.render('add-job-roles', { title: 'Add Job Role' });
    });

    app.post('/admin/job-roles', async (req: Request, res: Response) => {
      const data: AddJobRole = req.body;
      const jobRoleService = this.jobRoleServiceClass;

      try {
        if (data.roleTitle && data.summary && data.link !== undefined) {
          const sanitizedData: AddJobRole = {
            roleTitle: xss(data.roleTitle),
            summary: xss(data.summary),
            link: xss(data.link),
            band_id: data.band_id
          };
          await jobRoleService.createJobRoles(sanitizedData);
          res.locals.successMessage = 'Successfuly added job role';
        }
      } catch (e) {
        res.locals.errorMessage = e;
      }
      res.render('add-job-roles', { title: 'Add Job Role' });
    });
  }
}
