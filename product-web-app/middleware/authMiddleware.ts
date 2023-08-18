import axios from 'axios';
import { NextFunction, Request, Response } from 'express';
import jwt, { JwtPayload } from 'jsonwebtoken';

export default class AuthMiddleware {
  private secret: string;

  constructor() {
    this.secret = process.env.JWT_SECRET || '';
  }

  async authorisation(req: Request, res: Response, next: NextFunction) {
    console.log('jeden')
    if (req.path === '/auth/login' || req.path === '/auth/register') {
      return next();
    }
    console.log('dwa')
    console.log(req.cookies)
    const { JWT } = req.cookies;
    if (JWT === undefined || JWT === null) {
      console.log('dwaaaaa')
      // res.locals.errormessage = 'Token issue, please login.';
      return res.redirect('/auth/login');
    }
    console.log('trzy')
    const decoded: JwtPayload | string = jwt.verify(JWT, this.secret);
    if (typeof decoded !== 'object' || !decoded.user_role) {
      // res.locals.errormessage = 'Decode issue, please login.';
      return res.redirect('/auth/login');
    }
    console.log('cztery')
    if (req.path.split('/')[1] === 'admin') {
      if (decoded.user_role !== 'Admin') {
        // res.locals.errormessage = 'Admin privilege required';
        return res.redirect('/');
      }
      axios.defaults.headers.common.requireAdmin = true;
    }
    console.log('koniec')
    return next();
  }

  async addToken(req: Request, res: Response, next: NextFunction) {
    try {
      axios.defaults.headers.common.Authorization = req.cookies.JWT;
      return next();
    } catch {
      axios.defaults.headers.common.Authorization = null;
      return next();
    }
  }

  async navbarSetup(req: Request, res: Response, next: NextFunction) {
    try {
      const { JWT } = req.cookies;
      let decoded: JwtPayload | string = '';
      if (JWT !== undefined && this.secret !== undefined) {
        decoded = jwt.verify(JWT, this.secret);
      }

      if (typeof decoded !== 'object' || !decoded.user_role) {
        res.locals.isAdmin = false;
        return next();
      }

      let isAdmin: boolean;
      if (decoded.user_role === 'Admin') {
        isAdmin = true;
      } else {
        isAdmin = false;
      }

      res.locals.isAdmin = isAdmin;
      return next();
    } catch {
      res.locals.isAdmin = false;
      return next();
    }
  }
}
