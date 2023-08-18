import Page from './page.js';

class LoginPage extends Page {  

  async fillLoginForm(email, password) {
    await $('//*[@id="email"]').setValue(email);
    await $('//*[@id="password"]').setValue(password);
  }

  async submitLogin() {
    await $('//*[@id="form"]/div/form/div[3]/button').click();
  }

  async isErrorMessageVisible(text) {
    try {
      const errorMessage = await $('//*[@id="form"]/div/div');
      
      return (await errorMessage.getText()).includes(text);
    } catch (error) {
      console.log(error);
      return false;
    }
  }

  async isMainPage() {
    const currentUrl = await browser.getUrl();
    return currentUrl.includes('/auth/login');
  }

}
export default new LoginPage();