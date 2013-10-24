CREATE DATABASE qldv
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default      
       CONNECTION LIMIT = -1;

\c qldv	
\encoding utf8	  

CREATE TABLE "Department"
(
"DeptID" CHARACTER(10) NOT NULL,
"DeptName" CHARACTER(50),
"Tel" CHARACTER(15),
"Mail" CHARACTER(50),
CONSTRAINT "deptPry" PRIMARY KEY ("DeptID")
);	
 
CREATE TABLE "Class"
(
"ClaID" CHARACTER(10) NOT NULL,
"ClaName" CHARACTER(20),
"Year" INT,
"MoniterID" CHARACTER(20),
"DeptID" CHARACTER(10),
CONSTRAINT "claPry" PRIMARY KEY ("ClaID"),
CONSTRAINT "claRef_Dept" FOREIGN KEY ("DeptID")
	REFERENCES "Department" ("DeptID") MATCH SIMPLE	
);
 
CREATE TABLE "Student"
(
"StuID" CHARACTER(20) NOT NULL,
"F_Name" CHARACTER(20),
"L_Name" CHARACTER(20),
"Tel" CHARACTER(15),
"Mail" CHARACTER(50),
"Address" CHARACTER(50),
"ClaID" CHARACTER(10),
"Des" CHARACTER(100),
"Status" INT,
CONSTRAINT "stuPry" PRIMARY KEY ("StuID"),
CONSTRAINT "stuRef_Cla" FOREIGN KEY ("ClaID")
	REFERENCES "Class" ("ClaID") MATCH SIMPLE
);	

CREATE TABLE "Organization"
(
"OrgID" CHARACTER(10) NOT NULL,
"OrgName" CHARACTER(50),
"Par" CHARACTER(10),
"Manager" CHARACTER(50),
"Mail" CHARACTER(50),
"Tel" CHARACTER(15),
CONSTRAINT "orgPry" PRIMARY KEY ("OrgID")
);

CREATE TABLE "Participation"
(
"StuID" CHARACTER(20),
"OrgID" CHARACTER(10),
"Role" CHARACTER(20),
"Start" DATE,
"End" DATE,
"Description" CHARACTER(256),
CONSTRAINT "partPry" PRIMARY KEY ("StuID","OrgID")
);

CREATE TABLE "Event"
(
"EventID" CHARACTER(10) NOT NULL,
"EventName" CHARACTER(50),
"Location" CHARACTER(50),
"Start" DATE,
"End" DATE,
"NumOfPeople" INT,
"Rating" INT,
CONSTRAINT "eventPry" PRIMARY KEY ("EventID")
);

CREATE TABLE "EvtOrg"
(
"OrgID" CHARACTER(10),
"EventID" CHARACTER(10),
"Description" CHARACTER(256),
CONSTRAINT "evtOrgRef_Stu" FOREIGN KEY ("OrgID")
	REFERENCES "Organization" ("OrgID") MATCH SIMPLE,
CONSTRAINT "evtOrgRef_Dept" FOREIGN KEY ("EventID")
	REFERENCES "Event" ("EventID") MATCH SIMPLE
);

ALTER TABLE "Class" ADD FOREIGN KEY ("MoniterID")
	REFERENCES "Student"("StuID") MATCH SIMPLE;

--Sample data
INSERT INTO "Department" VALUES ('CNTT&TT','Vien cong nghe thong tin va truyen thong','is.hust.edu.vn','01255462');
SELECT * FROM "Department";
\c postgres
DROP DATABASE qldv;