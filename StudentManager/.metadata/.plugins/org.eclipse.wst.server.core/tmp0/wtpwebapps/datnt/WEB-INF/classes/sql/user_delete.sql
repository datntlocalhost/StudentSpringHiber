DELETE u, ur 
FROM
 USER u,
 USER_ROLE ur 
WHERE
 u.user_id = ur.user_id and
 u.student_id = :id