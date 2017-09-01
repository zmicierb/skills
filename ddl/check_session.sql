SELECT count(*)
FROM pg_stat_activity;

SELECT *
FROM pg_stat_activity;

SELECT pg_terminate_backend(pid)
FROM
  pg_stat_activity
WHERE
  -- don't kill my own connection!
  pid <> pg_backend_pid()
  -- don't kill the connections to other databases
  AND state = 'idle';