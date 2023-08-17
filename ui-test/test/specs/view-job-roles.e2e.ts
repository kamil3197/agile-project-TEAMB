import viewJobRolesPage from '../pageobjects/view-job-roles.page.js';
import jobSpecificationPage from '../pageobjects/job-specification.page.js';
import {expect } from '@wdio/globals';

describe('View job-roles page and test its content', async () => {
   
   
    it('should be able to see job roles list || Happy Path', async () => {

        await viewJobRolesPage.goToViewJobRolesPage();
        expect(await viewJobRolesPage.isJobsTableVisible()).toBe(true);
    });

    it('should job specification not be empty || Happy Path', async () => {

        await viewJobRolesPage.viewSpecificJob(1);
        expect(await jobSpecificationPage.isJobSpecEmpty()).toBe(false);
    });

    it.only('should be able to follow link to sharepoint || Happy Path', async () => {

        await viewJobRolesPage.viewSpecificJob(1);
        expect(jobSpecificationPage.getHrefDomain()).toMatch(/\*\.kainossoftwareltd.sharepoint.com\/\.\*/)
    });

});