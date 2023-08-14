# Database built

## How to build and develop the database.

1. Pre-requirements: your .zshrc file should contain:
   - export DB_HOST="_IP address of server (100.127.29.164)_"
   - export DB_USERNAME="_your username to log in to database_"
   - export DB_PASSWORD="_your password to log in to database_"
2. How to run
   - open _repository_/product-backend/scripts in your terminal
   - put command `./db_build_local.sh`
   - two new files will be made: _create_db_local_exe.sql_ and _example_data_local_exe.sql_
   - files will be automatically executed
3. How to develop
   - in script _create_tables.sql_ add elements which make new tables or alter already existing tables
   - is script _example_data_local.sql_ add elements which insert data into existing tables
   - DO NOT EDIT: _create_db_local_exe.sql_ and _example_data_local_exe.sql_
   - REMEMBER: order of commands execution is IMPORTANT; wrong order may generate issues
   - leave a comment on which ticket is referenced by the code you added
