import { Application, Request, Response } from 'express';
import { JobRole } from '../model/jobRole.js';
import JobRoleService from '../service/jobRolesService.js';

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
  }
}
