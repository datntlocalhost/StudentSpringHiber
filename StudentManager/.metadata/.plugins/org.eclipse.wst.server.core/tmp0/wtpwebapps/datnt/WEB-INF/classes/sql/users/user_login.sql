SELECT
 u.user_id as userId,
 u.user_username as username,
 u.user_password as password 
FROM
 USER u 
WHERE
 u.user_username = :username and
 u.user_password = :password