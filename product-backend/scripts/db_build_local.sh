#!/bin/zsh
source ~/.zshrc

username="$DB_USERNAME"
db_name="TeamA_$username"
DB_PORT=3306

sed -e "s/{{USERNAME}}/$username/g" create_db_local.sql > create_db_local_exe.sql
sed -e "s/{{USERNAME}}/$username/g" example_data_local.sql > example_data_local_exe.sql

mysql -h ${DB_HOST} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < create_db_local_exe.sql
mysql -h ${DB_HOST} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} < create_tables.sql
mysql -h ${DB_HOST} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} ${db_name} < example_data_local_exe.sql
mysql -h ${DB_HOST} -P ${DB_PORT} -u ${DB_USERNAME} -p${DB_PASSWORD} ${db_name} < band.sql

echo "end"