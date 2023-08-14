import Page from './page.js';

class LoginPage extends Page {  

async fillLoginForm(email, password) {
    await $('//*[@id="email"]').setValue(email);
    await $('//*[@id="password"]').setValue(password);

  }

  async submitLogin() {
    await $('//*[@id="form"]/div/form/div[3]/button').click();
  }

}
export default new LoginPage();