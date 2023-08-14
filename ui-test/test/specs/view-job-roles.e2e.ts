import viewJobRolesPage from "../pageobjects/view-job-roles.page.js";
import {browser, expect} from '@wdio/globals';

describe('Get a view of job-roles page', async () => {
    it('View-job-roles and return to main page', async () => {

        await browser.url("https://wsbz8mje8f.eu-west-1.awsapprunner.com/job-roles");
        await viewJobRolesPage.isTitleCorrect();
        await viewJobRolesPage.engineeringPage();

    });

    
});

