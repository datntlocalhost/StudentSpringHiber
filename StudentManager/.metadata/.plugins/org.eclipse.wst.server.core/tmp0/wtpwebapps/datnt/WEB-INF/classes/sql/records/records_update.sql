UPDATE RECORDS SET
  records_sex = :sex,
  records_birthday = :birthday,
  records_phone = :phone,
  records_email = :email,
  records_address = :address 
WHERE
  student_id = :id