#!/bin/sh

host=$DOCKER_IP
db=$DB_NAME
port=$DB_PORT
su=$DB_SU_NAME
user=$DB_USERNAME

echo *:*:*:$DB_SU_NAME:$DB_SU_PASS > ~/.pgpass
chmod 0600 ~/.pgpass

until psql -h $host -p $port -U $su -l; do
     echo "Postgres is unavailable - waiting"
  sleep 1
done

echo "Postgres is up - init DB"
createdb  -h $host -p $port -U $su $db;
psql -h $host -p $port -U $su -c "GRANT ALL privileges ON DATABASE \"$db\" TO \"$user\""
echo "Init DB: OK"