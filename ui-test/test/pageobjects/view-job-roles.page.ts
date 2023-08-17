import Page from './page.js';


export class ViewJobRolesPage extends Page {

    async isJobsTableVisible() {
    try { 
        const sectionWithJobs = await $('//*[@id="orders"]/h2');
        return (await sectionWithJobs.getText()).includes('job-roles');
    } catch (error) { 
      return false;
    }
  }
  
  async viewSpecificJob(jobNumber: number) {
    return browser.url(`${this.baseUrl}/job-specification/` + jobNumber);
  }
}

export default new ViewJobRolesPage();