import RegisterPage from '../pageobjects/register.page.js'; 
import LoginPage from '../pageobjects/login.js'; 

describe('Registration and Login Process', () => {
    it('Register as Admin || UnhappyPath', async () => {
     
      await RegisterPage.goToRegisterPage();
      await RegisterPage.fillRegistrationForm('kacperadmin@kainos.com', 'kacper', true);
      await RegisterPage.submitRegistrationForm(); 
      expect(await RegisterPage.isErrorMessageVisible('Password must be at least 8 characters long.')).toBe(true);
    });

    it('Register as Admin || HappyPath', async () => {
     
        await RegisterPage.goToRegisterPage();
        await RegisterPage.fillRegistrationForm('kacperadmin@kainos.com', 'Kacper1213!', true);
        await RegisterPage.submitRegistrationForm(); 
        expect(await RegisterPage.isLoginPage()).toBe(true);
    });

    it('Register as Employee || UnhappyPath', async () => {
     
        await RegisterPage.goToRegisterPage();
        await RegisterPage.fillRegistrationForm('kacperemployee@kainos.com', 'kacper', false);
        await RegisterPage.submitRegistrationForm(); 
        expect(await RegisterPage.isErrorMessageVisible('Password must be at least 8 characters long.')).toBe(true);
      });
  
      it('Register as Employee || HappyPath', async () => {
       
          await RegisterPage.goToRegisterPage();
          await RegisterPage.fillRegistrationForm('kacperemployee@kainos.com', 'Kacper1213!', false);
          await RegisterPage.submitRegistrationForm(); 
          expect(await RegisterPage.isLoginPage()).toBe(true);
      });

      it('Login as Admin || UnHappyPath', async () => {

        await LoginPage.goToLoginPage();
        await LoginPage.fillLoginForm('adamadmin@kainos.com', 'admin');
        await LoginPage.submitLogin();
        expect(await LoginPage.isErrorMessageVisible('Login failed! Please try again.')).toBe(true);
      });

      it('Login as Admin || HappyPath', async () => {

        await LoginPage.goToLoginPage();
        await LoginPage.fillLoginForm('kacperadmin@kainos.com', 'Kacper1213!');
        await LoginPage.submitLogin();
        expect(await LoginPage.isMainPage()).toBe(true);
      });

      it('Login as Employee || UnHappyPath', async () => {

        await LoginPage.goToLoginPage();
        await LoginPage.fillLoginForm('adamemployee@kainos.com', 'employee');
        await LoginPage.submitLogin();
        expect(await LoginPage.isErrorMessageVisible('Login failed! Please try again.')).toBe(true);
      });

      it('Login as Edmin || HappyPath', async () => {

        await LoginPage.goToLoginPage();
        await LoginPage.fillLoginForm('kacperemployee@kainos.com', 'Kacper1213!');
        await LoginPage.submitLogin();
        expect(await LoginPage.isMainPage()).toBe(true);
      });


});