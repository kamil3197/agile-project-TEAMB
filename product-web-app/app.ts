import * as url from 'url';
import path from 'path';
import express from 'express';
import 'dotenv/config';
import session from 'express-session';
import nunjucks from 'nunjucks';
import BandController from './controller/bandController.js';

import JobRolesController from './controller/JobRolesController.js';

import authController from './controller/authController.js';

const dirname = url.fileURLToPath(new URL('.', import.meta.url));

const app = express();

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

app.use(
  session({
    secret: 'NOT_HARDCODED_SECRET',
    cookie: { maxAge: 3_600_000 },
  }),
);

app.set('view engine', 'html');
app.use('/public', express.static(path.join(dirname, 'public')));

const bandController = new BandController();
bandController.initializeRoutes(app);

const jobRolesController = new JobRolesController();
jobRolesController.init(app);

app.listen(3000, () => {
  // eslint-disable-next-line no-console
  console.log('Server listening on port 3000');
});

// app.get('/', async (req, res) => {
//   if (!req.session.token || req.session.token.length === 0) {
//     res.redirect('auth/login');
//   } else {
//     res.render('index', { title: 'Main page' });
//   }
// });

authController(app);
