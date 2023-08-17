import Page from './page.js';

export class JobSpecificationPage extends Page {  

    async isJobSpecEmpty() {
        try {
        const jobSpecBox = await $('//*[@id="jobSpec"]/ul/li/div/div[1]/p[2]');
         return ((await jobSpecBox.getText()).trim().length === 0);
        } catch (error) {
            return false;
        }
    }

    }
    export default new JobSpecificationPage();