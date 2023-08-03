import express from 'express';
import { Application } from "express";
import * as url from 'url';
import 'dotenv/config';
import session from 'express-session';
import path from 'path';
import nunjucks from 'nunjucks';
import JobRolesController from './controller/JobRolesController.js';

const dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app = express();

const appViews = path.join(dirname, '/views/');


const nunjucksConfig = {
  autoescape: true,
  noCache: true,
  express: app,
};

nunjucks.configure(appViews, nunjucksConfig);

app.set('view engine', 'html')

app.use('/public', express.static(path.join(dirname, '/public')))

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(session({ secret: 'NOT_HARDCODED_SECRET', cookie: { maxAge: 60000 } }));

declare module 'express-session' {
  interface SessionData {
    token: string;
  }
}

app.set('view engine', 'html');
app.use('/public', express.static(path.join(dirname, 'public')));

const jobRolesController = new JobRolesController(app);

app.listen(3000, () => {
  console.log('Server listening on port 3000');
});
