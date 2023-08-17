import Page from './page.js';

export class RegisterPage extends Page {  

  async fillRegistrationForm(email, password, isAdmin: boolean) {
    await $('//*[@id="email"]').setValue(email);
    await $('//*[@id="password"]').setValue(password);
    if (isAdmin) {
      (await $('#adminRadio')).click();
    } else {
      (await $('#employeeRadio')).click();
    }
    
  }

  async submitRegistrationForm() {
    await $('//*[@id="form"]/div/form/div[4]/button').click();
  }

  async isErrorMessageVisible(text) {
    try {
      const errorMessage = await $('//*[@id="form"]/div/div');
      //*[@id="form"]/div/div
      console.log(await errorMessage.getText());
      console.log("kacper");
      return (await errorMessage.getText()).includes(text);
    } catch (error) {
      return false;
    }
  }

  async isLoginPage() {
    const currentUrl = await browser.getUrl();
    return currentUrl.includes('/auth/login');
  }

}
export default new RegisterPage();