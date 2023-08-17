import Page from './page.js';

class AddBand extends Page {  

  async fillAddBandForm(name, level, responsibilites) {
   //console.log("1");
    await $('//*[@id="name"]').setValue(name);
    await $('//*[@id="level"]').setValue(level);
    await $('//*[@id="responsibilities"]').setValue(responsibilites)
  }

  async submitAddBand() {
   // console.log("2");
    await $('//*[@id="submitButton"]').click();
  }

  async successfulMessageAddBand(text) {
    try {
      const errorMessage = await $('//*[@id="form"]/div');
      
      return (await errorMessage.getText()).includes(text);
    } catch (error) {
      console.log(error);
      return false;
    }
  }

  async isErrorMessage(text) {
    try {
      const errorMessage = await $('//*[@id="form"]/div');
      
      return (await errorMessage.getText()).includes(text);
    } catch (error) {
      console.log(error);
      return false;
    }
  }


}
export default new AddBand();