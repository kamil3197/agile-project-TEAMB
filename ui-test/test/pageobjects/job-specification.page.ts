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

    async clickOnSharepointLink() {
        await $('//*[@id="jobSpec"]/ul/li/div/div[2]/p[1]/a').click();
    }

    async getHrefDomain() {
        const buttonElement = await $('//*[@id="jobSpec"]/ul/li/div/div[2]/p[1]/a');
        const hrefDomain = await buttonElement.getProperty('href');
        console.log('lolxd')
        console.log(typeof(hrefDomain));
        return  hrefDomain
    }
    }
    export default new JobSpecificationPage();