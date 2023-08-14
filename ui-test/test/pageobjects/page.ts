/**
 * main page object containing all methods, selectors and functionality
 * that is shared across all page objects
 */

export default class Page {
  protected baseUrl: string = process.env.WEB_APP_URL || 'UNDEFINED';


  public open() {
    return browser.url(this.baseUrl || 'UNDEFINED');
  }

  public goToRegisterPage() {
    return browser.url(`${this.baseUrl}/auth/register`);
  }

  public goToViewJobRolePage() {
    return browser.url(`${this.baseUrl}/job-roles`);
  }

  public goToLoginPage() {
    return browser.url(`${this.baseUrl}/auth/login`);
  }
}
