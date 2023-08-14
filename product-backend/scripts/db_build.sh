#!/bin/bash

db_name="$DB_NAME"
DB_PORT=3306

mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < create_tables.sql
mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < band.sql

echo "end"
