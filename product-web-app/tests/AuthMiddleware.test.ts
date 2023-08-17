import chai, { expect } from 'chai';
import chaiHttp from 'chai-http';
import app from '../app.js';

chai.use(chaiHttp);

describe('AuthMiddleware', () => {
  it('should redirect to /auth/login if no token', async () => {
    const response = await chai.request(app).get('/some/path');

    expect(response).to.redirect;
    expect(response.redirects[0].slice(-11)).to.be.equal('/auth/login');
  });

  //   it('should pass through if token is valid', async () => {
  //     const response = await chai
  //         .request(app)
  //         .get('/')
  //         .set('Cookie', 'token=myAuthToken');

  //         expect(response).to.have.status(200);
  //     expect(response.text).to.equal('Authenticated Route');
  //   });
});
