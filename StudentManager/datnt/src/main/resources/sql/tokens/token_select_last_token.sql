SELECT
 t.token_username as username,
 t.token_value as token,
 t.token_timestamp as timestamp 
FROM
 TOKEN t,
 (SELECT MAX(token_id) as maxid FROM TOKEN WHERE token_username = :username) as tk 
WHERE
 t.token_id = tk.maxid