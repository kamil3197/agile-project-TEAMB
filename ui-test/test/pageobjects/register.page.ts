import Page from './page.js';

class RegisterPage extends Page {  

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

}
export default new RegisterPage();