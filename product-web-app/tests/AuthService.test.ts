import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import chai from 'chai';
import * as dotenv from 'dotenv';
import AuthService from '../service/authService.js';
import User from '../model/register.js';

dotenv.config();

const { expect } = chai;
const BASE_URL = process.env.API_URL;

const authService = new AuthService();

const user: User = {
  email: 'test@kainos.com',
  password: 'Test123!',
  role: 'Employee',
};

describe('AuthService', () => {
  describe('registerUser', () => {
    it('should return id when passing correct input data', async () => {
      const mock = new MockAdapter(axios);
      const userId = 1;

      mock.onPost(`${BASE_URL}/auth/register`, user).reply(200, userId);

      const response = await authService.register(user);
      expect(response).to.equal(userId);
    });

    it('should return error 400 when pass invalid data', async () => {
      const mock = new MockAdapter(axios);
      mock.onPost(`${BASE_URL}/auth/register`, user).reply(400);

      try {
        await authService.register(user);
      } catch (error) {
        const errorMessage: string = error instanceof Error ? error.message : String(error);
        expect(errorMessage).to.equal('Registration failed! Please try again.');
      }
    });
  });
});
