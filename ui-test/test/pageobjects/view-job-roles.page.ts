import Page from './page.js';
import {browser, expect, $} from '@wdio/globals';

class ViewJobRolePage extends Page {

  async isTitleCorrect() {
    const title = await browser.getTitle();
    return title === 'Job Roles'; // Update the expected title
  }

  async engineeringPage(){
    await $('//*[@id="orders"]/table/tbody/tr[1]/td/a').click();

  }




}

export default new ViewJobRolePage();

