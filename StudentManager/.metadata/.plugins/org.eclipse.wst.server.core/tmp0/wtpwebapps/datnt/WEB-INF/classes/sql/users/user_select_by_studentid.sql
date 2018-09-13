SELECT
 u.user_id as userId,
 u.user_username as username,
 u.user_password as password 
FROM
 USER u 
WHERE
 u.student_id = :id