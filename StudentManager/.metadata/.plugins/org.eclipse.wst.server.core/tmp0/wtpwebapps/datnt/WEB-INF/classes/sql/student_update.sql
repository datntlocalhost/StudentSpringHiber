 UPDATE
   STUDENT
 SET
   student_code = :code,
   student_name = :name,
   student_startyear = :schoolyear,
   school_id = :schoolid 
WHERE
  student_id = :id