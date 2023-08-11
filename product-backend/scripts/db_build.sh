#!/bin/zsh
#source ~/.zshrc

db_name="$DB_NAME"
DB_PORT=3306

mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < create_db.sql
mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < band.sql

# Uncomment if you need example data
#  mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < example_data.sql


echo "end"
