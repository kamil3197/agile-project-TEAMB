import Page from './page.js';


class ViewJobRolesPage extends Page {

  private tableWithJobs = $$('#orders > table');
  private sectionWithJobs = $('#orders');
  
  async isJobsTableVisible() {
    try { 
        return (await this.sectionWithJobs).isDisplayed();
    } catch (error) { 
      return false;
    }
  }
  
  async viewSpecificJob(jobNumber: number) {
    let jobViewName = this.tableWithJobs[jobNumber].getText();
    await this.tableWithJobs[jobNumber].click();
  }
  
  async isTitleCorrect() {
    const title = await browser.getTitle();
    return title === 'Job Roles'; // Update the expected title
  }

  async engineeringPage(){
    await $('//*[@id="orders"]/table/tbody/tr[1]/td/a').click();
  }
}

export default new ViewJobRolesPage();