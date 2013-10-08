CREATE DATABASE "doanvienDB"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
\c "doanvienDB"	   
CREATE TABLE "Student"
(
  "StuID" character(20) NOT NULL,
  "F_Name" character(20),
  "L_Name" character(20),
  "Tel" character(15),
  "Mail" character varying(50),
  "Address" character(50),
  "DeptID" character(10),
  "Year" character(10),
  "ClaID" character(10),
  "Des" character varying(100),
  CONSTRAINT "Student_pkey" PRIMARY KEY ("StuID")
)	   
CREATE TABLE "Department"
(
  "DeptID" character(20) NOT NULL,
  "DeptName" character(20),
  CONSTRAINT "Dept_pkey" PRIMARY KEY ("DeptID")
)
CREATE TABLE "Class"
(
  "ClaID" character(20) NOT NULL,
  "ClassName" character(20),
  CONSTRAINT "Cla_pkey" PRIMARY KEY ("ClaID")
)
CREATE TABLE "Organization"
(
  "OrgID" character(20) NOT NULL,
  "OrgName" character(20),
  "Parent" character(20),
  CONSTRAINT "Org_pkey" PRIMARY KEY ("OrgID")
)
CREATE TABLE "\
"
(
  "OrgID" character(20) NOT NULL,
  "OrgName" character(20),
  "Parent" character(20),
  CONSTRAINT "Org_pkey" PRIMARY KEY ("OrgID")
)