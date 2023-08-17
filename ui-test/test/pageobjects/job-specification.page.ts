import Page from './page.js';

class JobSpecificationPage extends Page {  

    private pdfTitle = $('//*[@id="id__3"]').getText();

    async isJobSpecNotEmpty() {
        const jobSpecBox = await $('//*[@id="jobSpec"]/div[1]/div[2]/p');
        
          if ((await jobSpecBox.getText()).trim().length === 0) {
            return false;
        } else {
            return true;
        }
    }

    async clickOnSharepointLink() {
        await $('//*[@id="jobSpec"]/div[1]/div[1]/div/p/a').click();
    }
    }
    export default new JobSpecificationPage();