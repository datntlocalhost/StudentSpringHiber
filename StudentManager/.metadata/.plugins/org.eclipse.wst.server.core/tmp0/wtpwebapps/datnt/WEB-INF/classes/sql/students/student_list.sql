SELECT
  st.student_id as studentId,
  st.student_code as studentCode,
  st.student_name as studentName,
  st.student_startyear as schoolYear,
  st.school_id as schoolId,
  sc.school_code as schoolCode,
  r.records_sex as sex,
  r.records_birthday as birthday,
  r.records_phone as phone,
  r.records_email as email,
  r.records_address as address 
FROM
  STUDENT st,
  RECORDS r,
  SCHOOL sc 
WHERE
  st.student_id = r.student_id and
  st.school_id = sc.school_id