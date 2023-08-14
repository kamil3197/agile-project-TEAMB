import RegisterPage from '../pageobjects/register.page.js'; 

describe('Registration Process', () => {
    it('Register as Admin || HappyPath', async () => {
     
      await RegisterPage.goToRegisterPage();
      await RegisterPage.fillRegistrationForm('useradmin@kainos.com', 'Test123@', true);
      await RegisterPage.submitRegistrationForm(); 
    });

    it('Register as Employee || HappyPath', async () => {
     
      await RegisterPage.goToRegisterPage();
      await RegisterPage.fillRegistrationForm('useremployee@kainos.com', 'Test123@', false);
      await RegisterPage.submitRegistrationForm(); 
    });

  });


