import * as url from 'url';
import path from 'path';
import express from 'express';
import 'dotenv/config';
import session from 'express-session';
import nunjucks from 'nunjucks';
import axios from 'axios';
import cookieParser from 'cookie-parser';


import JobSpecificationController from './controller/JobSpecificationController.js';
import BandController from './controller/bandController.js';
import JobRolesController from './controller/JobRolesController.js';
import AuthController from './controller/authController.js';
import AuthMiddleware from './middleware/auth.js'

const dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app = express();



axios.defaults.baseURL = process.env.API_URL;

const appViews = path.join(dirname, '/views/');

const nunjucksConfig = {
  autoescape: true,
  noCache: true,
  express: app,
};

nunjucks.configure(appViews, nunjucksConfig);

app.set('view engine', 'html');

app.use('/public', express.static(path.join(dirname, '/public')));

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(cookieParser());

app.use(
  session({
    secret: 'NOT_HARDCODED_SECRET',
    cookie: { maxAge: 3_600_000 },
  }),
);


declare module 'express-session' {
  interface SessionData {
    token: string;
    secret: string;
  }
}

app.set('view engine', 'html');
app.use('/public', express.static(path.join(dirname, 'public')));

const authMiddleware = new AuthMiddleware();
app.use(authMiddleware.authorisation)

const bandController = new BandController();
bandController.initializeRoutes(app);

const jobRolesController = new JobRolesController();
jobRolesController.init(app);

new JobSpecificationController().init(app);

app.listen(3000, () => {
  // eslint-disable-next-line no-console
  console.log('Server started on port 3000')
});

app.get('/', async (req, res) => {
    res.redirect('/home');
});

AuthController(app);
