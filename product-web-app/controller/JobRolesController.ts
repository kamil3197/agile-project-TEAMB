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
        res.render('job-roles', { jobRoles });
      } catch (e) {
        res.status(500).send('Error JobRolesController');
      }
    });

    app.get('/admin/job-roles', async (req: Request, res: Response) => {
      res.render('add-job-roles');
    });

    app.post('/admin/job-roles', async (req: Request, res: Response) => {
      const data: AddJobRole = req.body;
      const jobRoleService = this.jobRoleServiceClass;

      try {
        if (data.title && data.summary && data.sharepoint_link !== undefined) {
          const sanitizedData: AddJobRole = {
            title: xss(data.title),
            summary: xss(data.summary),
            sharepoint_link: xss(data.sharepoint_link),
          };
          await jobRoleService.createJobRoles(sanitizedData);
          res.locals.successmessage = 'Success to add job role';
        }
      } catch (e) {
        res.locals.errormessage = 'Failed to add job role';
      }
      res.render('add-job-roles');
    });
  }
}
