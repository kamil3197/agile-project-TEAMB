import axios from 'axios';
import { NextFunction, Request, Response } from 'express';
import jwt, { JwtPayload } from 'jsonwebtoken';

export default class AuthMiddleware {
  async authorisation(req: Request, res: Response, next: NextFunction) {
    // console.log("***********************************************")
    // console.log(req.path)
    // console.log(req.path == '/auth/login')
    if (req.path === '/auth/login' || req.path === '/auth/register') {
      return next();
    }

    const secret = process.env.jwt_secret;
    const { JWT } = req.cookies;
    // console.log("***********************************************")
    // console.log(JWT)
    if (JWT === undefined || secret === undefined) {
      res.locals.errormessage = 'Token issue, please login.';
      return res.redirect('/auth/login');
    }

    const decoded: JwtPayload | string = jwt.verify(JWT, secret);
    // console.log("***********************************************")
    // console.log(decoded)
    if (typeof decoded !== 'object' || !decoded.user_role) {
      res.locals.errormessage = 'Decode issue, please login.';
      return res.redirect('/auth/login');
    }

    // console.log("***********************************************")
    // console.log(decoded.user_role)
    // console.log(req.path.split('/')[0])
    // console.log(req.path.split('/')[1])
    // console.log(req.path.split('/')[2])
    if (req.path.split('/')[1] === 'admin') {
      if (decoded.user_role !== 'Admin') {
        res.locals.errormessage = 'Admin privilege required';
        return res.redirect('/home');
      }
      axios.defaults.headers.common.requireAdmin = true;
    }

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
      const secret = process.env.jwt_secret;
      if (JWT !== undefined && secret !== undefined) {
        decoded = jwt.verify(JWT, secret);
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
