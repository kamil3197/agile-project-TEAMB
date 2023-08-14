import { Application } from 'express';
import User from '../model/register.js';
import AuthService from '../service/authService.js';
import Login from '../model/login.js';

function authController(app: Application) {
  app.get('/auth/register', async (req, res) => {
    res.render('auth/register');
  });

  app.post('/auth/register', async (req, res) => {
    const data: User = req.body;
    data.email += '@kainos.com';

    try {
      const authService = new AuthService();
      await authService.register(data);
      res.redirect('auth/login');
    } catch (error) {
      res.locals.errormessage = error instanceof Error ? error.message : String(error);
      if (req.body.email.endsWith('@kainos.com')) {
        req.body.email = req.body.email.replace('@kainos.com', '');
      }
      res.render('auth/register', req.body);
    }
  });

  app.get('/auth/login', async (req, res) => {
    res.render('auth/login');
  });

  app.post('/auth/login', async (req, res) => {
    const loginData: Login = req.body;

    try {
      const authService = new AuthService();
      const token: string = await authService.login(loginData);
      res.cookie('JWT', token, {
        maxAge: 3600000,
      });

      res.redirect('/home');
    } catch (error) {
      res.locals.errormessage = error instanceof Error ? error.message : String(error);
      res.render('auth/login', req.body);
    }
  });

  app.get('/home', async (req, res) => {
    res.render('home');
  });
}

export default authController;
