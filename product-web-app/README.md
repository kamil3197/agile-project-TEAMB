# Adding page to navbar

1. Add title property in render method. You can find it in your controller.

   Example how to add title:
   `res.render('to-my-page', { title: 'My beautiful page' })`

2. Add a link to your page in the `navbar.html` file.
   You can find the file by follow the path: `views/base/navbar.html`.
   Add your link inside `dropdown-menu` block.

   Example how the link should looks like:
   ``<a class="dropdown-item" href="to-my-page">My beautiful page</a>```
