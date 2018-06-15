DO $$
DECLARE
  rec RECORD;
    cur CURSOR
  FOR SELECT
        person_id,
        count(*) cnt
      FROM skill_sum
      GROUP BY person_id;
BEGIN
  OPEN cur;
  LOOP
    FETCH cur INTO rec;
    EXIT WHEN NOT FOUND;

    EXECUTE 'UPDATE skill_sum SET total_amount=' || rec.cnt || 'WHERE person_id =' || rec.person_id;
  END LOOP;
END $$