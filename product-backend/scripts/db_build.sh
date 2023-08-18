#!/bin/bash

DB_PORT=3306
echo "************* connection opened *************"

mysql -h ${DB_HOST} -D ${DB_SCHEMA} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < create_tables.sql

echo "************* connection closed *************"

