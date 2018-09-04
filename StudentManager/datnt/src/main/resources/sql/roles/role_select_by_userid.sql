SELECT
   r.role_id as roleId,
   r.role_name as roleName 
FROM
  USER_ROLE ur,
  ROLE r 
WHERE
  ur.role_id = r.role_id and
  ur.user_id = :userid