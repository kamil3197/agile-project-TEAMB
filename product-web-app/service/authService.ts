import axios from 'axios';
import User from '../model/register.js';
import registerValidator from '../validator/registrationValidator.js';
import Login from '../model/login.js';

class AuthService {
  private API_URL: String;

  private validator: any;

  constructor() {
    this.API_URL = process.env.API_URL || '';
    this.validator = registerValidator;
  }

  async register(user: User): Promise<number> {
    this.validator(user);

    try {
      const response = await axios.post(`${this.API_URL}/auth/register`, user);
      return response.data;
    } catch (error) {
      throw new Error('Registration failed! Please try again.');
    }
  }

  async login(login: Login): Promise<string> {
    try {
      const response = await axios.post(`${this.API_URL}/auth/login`, login);
      return response.data;
    } catch (error) {
      throw new Error('Login failed! Please try again.');
    }
  }
}

export default AuthService;
