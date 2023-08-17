import AddBand from '../pageobjects/add_band.js'; 
import { browser, expect } from '@wdio/globals';

describe('Adding a new band', () => {
    it('Create new band || HappyPath', async () => {

      const band = Math.random().toString(36).substring(4,20);
      await AddBand.goToAddBand();
      await AddBand.fillAddBandForm(band, '1', 'testing');
      await browser.pause(2000);
      await AddBand.submitAddBand(); 
      await browser.pause(2000);
      expect(await AddBand.successfulMessageAddBand('You added band!')).toBe(true);
    });

    it('Create new band || UnPappyPath', async () => {

        const band = Math.random().toString(36).substring(4,20);
        await AddBand.goToAddBand();
        await AddBand.fillAddBandForm(band, '1', 'testing');
        await browser.pause(2000);
        await AddBand.submitAddBand(); 
        await browser.pause(2000);
        expect(await AddBand.successfulMessageAddBand('You added band!')).toBe(true);

        await AddBand.goToAddBand();
        await AddBand.fillAddBandForm(band, '1', 'testing');
        await browser.pause(2000);
        await AddBand.submitAddBand(); 
        await browser.pause(2000);
        expect(await AddBand.isErrorMessage('Failed to add band')).toBe(true);
      });


});