﻿CREATE DATABASE qldv
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
"Birth" DATE,
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
CONSTRAINT "partPry" PRIMARY KEY ("StuID","OrgID"),
CONSTRAINT "partRef_Stu" FOREIGN KEY ("StuID")
	REFERENCES "Student" ("StuID") MATCH SIMPLE,
CONSTRAINT "partRef_Org" FOREIGN KEY ("OrgID")
	REFERENCES "Organization" ("OrgID") MATCH SIMPLE
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
CONSTRAINT "evtOrgPry" PRIMARY KEY ("OrgID","EventID"),
CONSTRAINT "evtOrgRef_Stu" FOREIGN KEY ("OrgID")
	REFERENCES "Organization" ("OrgID") MATCH SIMPLE,
CONSTRAINT "evtOrgRef_Dept" FOREIGN KEY ("EventID")
	REFERENCES "Event" ("EventID") MATCH SIMPLE
);
ALTER TABLE "Class" ADD FOREIGN KEY ("MoniterID")
	REFERENCES "Student"("StuID") MATCH SIMPLE;

--System table 
CREATE TABLE "Account"
(
"UserName" CHARACTER(100) NOT NULL,
"Contact" CHARACTER(100),
"Password" CHARACTER(65),
"Role" INT,
CONSTRAINT "Key" PRIMARY KEY ("UserName")
);

CREATE TABLE "Log"
(
"UserName" CHARACTER(100) NOT NULL,
"LoginTime" CHARACTER(30),
"LogoutTime" CHARACTER(30),
"Action" CHARACTER(65),
CONSTRAINT "Key2" FOREIGN KEY ("UserName")
	REFERENCES "Account" ("UserName") MATCH SIMPLE
);

--Store procedure 
CREATE FUNCTION getRole(usrName character(100),password character(65))
RETURNS INT
AS 
$$SELECT "Role" FROM "Account"
WHERE "UserName" = $1 AND "Password" = $2 $$
LANGUAGE SQL;

--Sample data
INSERT INTO "Department" VALUES ('CK','Viện Cơ Khí','0255155','ck@gmail.com');
INSERT INTO "Department" VALUES ('DTVT','Viện Điện Tử Viễn Thông','01522566','dtvt@gmail.com');
INSERT INTO "Department" VALUES ('CNTT','Viện Công Nghệ Thông Tin','0123456789','cntt@gmail.com');
INSERT INTO "Department" VALUES ('NN','Viện Ngoại Ngữ','024185655','nn@gmail.com');

INSERT INTO "Class" VALUES ('CK10','Cơ khí 10',2009,NULL,'CK');
INSERT INTO "Class" VALUES ('DT2','Điện tử 2',2011,NULL,'DTVT');
INSERT INTO "Class" VALUES ('NB04','Nhật Bản 4',2010,NULL,'NN');
INSERT INTO "Class" VALUES ('VN6A','Việt Nhật 6A',2011,NULL,'CNTT');
INSERT INTO "Class" VALUES ('VN6B','Việt Nhật 6B',2011,NULL,'CNTT');

INSERT INTO "Student" VALUES ('20042152','Lê Anh','Ngọc','1989-10-21','0123211089','20042152@gmail.com','Bắc Giang','VN6A','Còn học',1);
INSERT INTO "Student" VALUES ('20042366','Nguyễn Hữu','Phước','1985-04-25','0123250485','20042366@gmail.com','Thái Bình','VN6B','Còn học',1);
INSERT INTO "Student" VALUES ('20042415','Phạm Lê Duy','Quang','1986-12-01','0123011286','20042415@gmail.com','Hà Nội','DT2','Còn học',1);
INSERT INTO "Student" VALUES ('20042443','Nguyễn Hồng','Quân','1986-09-02','0123020986','20042443@gmail.com','Hà Nội','NB04','Còn học',1);
INSERT INTO "Student" VALUES ('20042486','Vũ Văn','Quyền','1986-06-06','0123060686','20042486@gmail.com','Hải Phòng','CK10','Còn học',1);
INSERT INTO "Student" VALUES ('20042619','Vũ Hoàng','Sơn','1986-03-19','01231903086','20042619@gmail.com','An Giang','DT2','Còn học',1);
INSERT INTO "Student" VALUES ('20042947','Bùi Văn','Thịnh','1986-03-07','0123030786','20042947@gmail.com','Thanh Hóa','CK10','Còn học',1);
INSERT INTO "Student" VALUES ('20042950','Dương Đức','Thịnh','1986-03-05','0123030586','20042950@gmail.com','Thái Nguyên','VN6A','Còn học',1);
INSERT INTO "Student" VALUES ('20042966','Bùi Đức','Thọ','1986-03-18','0123180386','20042966@gmail.com','Bắc Ninh','NB04','Còn học',1);
INSERT INTO "Student" VALUES ('20043050','Lê Văn','Thục','1986-03-24','0123240386','20043050@gmail.com','Cao Bằng','VN6B','Còn học',1);

UPDATE "Class" SET "MoniterID" = '20042947' WHERE "ClaID" = 'CK10';
UPDATE "Class" SET "MoniterID" = '20042619' WHERE "ClaID" = 'DT2';
UPDATE "Class" SET "MoniterID" = '20042443' WHERE "ClaID" = 'NB04';
UPDATE "Class" SET "MoniterID" = '20042152' WHERE "ClaID" = 'VN6A';
UPDATE "Class" SET "MoniterID" = '20043050' WHERE "ClaID" = 'VN6B';

INSERT INTO "Organization" VALUES ('CLBTA','Câu lạc bộ Tiếng Anh',NULL,'Nguyễn Văn B','clbta@gmail.com','23949204032');
INSERT INTO "Organization" VALUES ('CLBTN','Câu lạc bộ Tiếng Nhật',NULL,'Nguyễn Văn A','clbtn@gmail.com','1294023851');
INSERT INTO "Organization" VALUES ('DTN','Đội tình nguyện',NULL,'Bùi Đức Thọ','dtn@gmail.com','2349829089');

INSERT INTO "Participation" VALUES ('20042152','CLBTN','Thành viên','2011-09-19','2012-09-19','Xuất Sắc');

INSERT INTO "Account" VALUES ('admin','hanoi','81dc9bdb52d04dc2036dbd8313ed055',1);

--
\c postgres
\encoding utf8
--DROP DATABASE qldv;