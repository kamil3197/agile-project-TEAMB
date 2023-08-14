
import ViewJobRolePage from '../pageobjects/view-job-roles.page.js'; // Import ViewJobRolePage class

  describe('Job Role Viewing Process', () => {
    it('should display job roles table on a page with job roles.', async () => {
      await ViewJobRolePage.goToViewJobRolePage();
      expect(await ViewJobRolePage.isTitleCorrect('Job Roles')).toBe(true);
      expect(await ViewJobRolePage.isJobRolesTableVisible()).toBe(true);
    });
  });
