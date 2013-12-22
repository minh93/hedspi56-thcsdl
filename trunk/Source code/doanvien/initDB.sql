CREATE DATABASE qldv
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default      
       CONNECTION LIMIT = -1;

\c qldv	
\encoding utf8	  

CREATE TABLE "Department"
(
"DeptID" CHARACTER VARYING(10) NOT NULL,
"DeptName" CHARACTER VARYING(50),
"Tel" CHARACTER VARYING(15),
"Mail" CHARACTER VARYING(50),
CONSTRAINT "deptPry" PRIMARY KEY ("DeptID")
);	
 
CREATE TABLE "Class"
(
"ClaID" CHARACTER VARYING(10) NOT NULL,
"ClaName" CHARACTER VARYING(20),
"Year" INT,
"MoniterID" CHARACTER VARYING(20),
"DeptID" CHARACTER VARYING(10),
CONSTRAINT "claPry" PRIMARY KEY ("ClaID"),
CONSTRAINT "claRef_Dept" FOREIGN KEY ("DeptID")
	REFERENCES "Department" ("DeptID") MATCH SIMPLE	
);
 
CREATE TABLE "Student"
(
"StuID" CHARACTER VARYING(20) NOT NULL,
"F_Name" CHARACTER VARYING(20),
"L_Name" CHARACTER VARYING(20),
"Birth" DATE,
"Gender" BOOLEAN,
"Year" INT,
"Tel" CHARACTER VARYING(15),
"Mail" CHARACTER VARYING(50),
"Address" CHARACTER VARYING(50),
"ClaID" CHARACTER VARYING(10),
"Des" CHARACTER VARYING(100),
"Status" INT,
CONSTRAINT "stuPry" PRIMARY KEY ("StuID"),
CONSTRAINT "stuRef_Cla" FOREIGN KEY ("ClaID")
	REFERENCES "Class" ("ClaID") MATCH SIMPLE
);	

CREATE TABLE "Organization"
(
"OrgID" CHARACTER VARYING(10) NOT NULL,
"OrgName" CHARACTER VARYING(50),
"Par" CHARACTER VARYING(10),
"Manager" CHARACTER VARYING(50),
"Mail" CHARACTER VARYING(50),
"Tel" CHARACTER VARYING(15),
CONSTRAINT "orgPry" PRIMARY KEY ("OrgID")
);

CREATE TABLE "Participation"
(
"StuID" CHARACTER VARYING(20),
"OrgID" CHARACTER VARYING(10),
"Role" CHARACTER VARYING(20),
"Start" DATE,
"End" DATE,
"Description" CHARACTER VARYING(256),
"Status" INT,
CONSTRAINT "partPry" PRIMARY KEY ("StuID","OrgID"),
CONSTRAINT "partRef_Stu" FOREIGN KEY ("StuID")
	REFERENCES "Student" ("StuID") MATCH SIMPLE,
CONSTRAINT "partRef_Org" FOREIGN KEY ("OrgID")
	REFERENCES "Organization" ("OrgID") MATCH SIMPLE
);

CREATE TABLE "Event"
(
"EventID" CHARACTER VARYING(10) NOT NULL,
"EventName" CHARACTER VARYING(50),
"Location" CHARACTER VARYING(50),
"Start" DATE,
"End" DATE,
"NumOfPeople" INT,
"Rating" INT,
CONSTRAINT "eventPry" PRIMARY KEY ("EventID")
);

CREATE TABLE "EvtOrg"
(
"OrgID" CHARACTER VARYING(10),
"EventID" CHARACTER VARYING(10),
"Description" CHARACTER VARYING(256),
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
"UserName" CHARACTER VARYING(100) NOT NULL,
"Contact" CHARACTER VARYING(100),
"Password" CHARACTER VARYING(65),
"Role" INT,
CONSTRAINT "Key" PRIMARY KEY ("UserName")
);

CREATE TABLE "Log"
(
"UserName" CHARACTER VARYING(100) NOT NULL,
"Time" CHARACTER VARYING(30),
"Action" CHARACTER VARYING(65),
CONSTRAINT "Key2" FOREIGN KEY ("UserName")
	REFERENCES "Account" ("UserName") MATCH SIMPLE
);

--Store procedure 
CREATE FUNCTION getRole(usrName CHARACTER(100),password CHARACTER(65))
RETURNS INT
AS 
$$SELECT "Role" FROM "Account"
WHERE "UserName" = $1 AND "Password" = $2 $$
LANGUAGE SQL;

CREATE FUNCTION insertStudent(stuID CHARACTER VARYING(20),f_name CHARACTER(20),l_name CHARACTER(20),
								birth DATE,gender BOOLEAN, yea int,tel CHARACTER(15),mail CHARACTER(50),address CHARACTER(50),ClaID CHARACTER(10),des CHARACTER(100))
RETURNS VOID
AS
$$INSERT INTO "Student"
VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,1);
$$
LANGUAGE SQL;

CREATE FUNCTION deleteStudent(stuID CHARACTER VARYING(20))
RETURNS VOID
AS
$$UPDATE "Student" SET "Status" = 0
WHERE "StuID" = $1;
$$
LANGUAGE SQL;

CREATE FUNCTION permanentlyDeleteStudent(stuID CHARACTER VARYING(20))
RETURNS VOID
AS
$$
DELETE FROM "Participation" WHERE "StuID" = $1;
UPDATE "Class" SET "MoniterID" = NULL WHERE "MoniterID" = $1;
DELETE FROM "Student" WHERE "StuID" = $1;
$$
LANGUAGE SQL;

CREATE FUNCTION insertUser(usrName CHARACTER VARYING(100), usrContact CHARACTER VARYING(100), usrPass CHARACTER VARYING(65), usrRole INT)
RETURNS VOID
AS
$$INSERT INTO "Account"
VALUES($1,$2,$3,$4)
$$
LANGUAGE SQL;
--Sample data

INSERT INTO "Department" VALUES ('CK','Viện Cơ Khí','2432009809','ck@hust.edu.vn');
INSERT INTO "Department" VALUES ('DTVT','Viện Điện Tử Viễn Thông','987654321','dtvt@hust.edu.vn');
INSERT INTO "Department" VALUES ('GDQP','Khoa Giáo Dục Quốc Phòng','124144123','gdqp@hust.edu.vn');
INSERT INTO "Department" VALUES ('NN','Viện Ngoại Ngữ','125353180','nn@hust.edu.vn');
INSERT INTO "Department" VALUES ('GDTC','Khoa Giáo Dục Thể Chất','21421213123','gdtc@hust.edu.vn');
INSERT INTO "Department" VALUES ('LLCT','Khoa Lý Luận Chính Trị','294020980','llct@hust.edu.vn');
INSERT INTO "Department" VALUES ('CNSH','Viện Công Nghệ Sinh Học','8831990','cnsh@hust.edu.vn');
INSERT INTO "Department" VALUES ('KTQL','Viện Kinh Tế và Quản Lý','9879809889','ktql@hust.edu.vn');
INSERT INTO "Department" VALUES ('KTHH','Viện Kĩ Thuật Hóa Học','97989080','kthh@hust.edu.vn');
INSERT INTO "Department" VALUES ('TDH','Viện Kỹ thuật Điều khiển và Tự động hóa','9798790981','tdh@hust.edu.vn');
INSERT INTO "Department" VALUES ('SPKT','Viện Sư Phạm Kỹ Thuật','323532123','spkt@hust.edu.vn');
INSERT INTO "Department" VALUES ('TTUD','Viện Toán ứng dụng và Tin học','1242142121','ttud@hust.edu.vn');
INSERT INTO "Department" VALUES ('DTQT','Viện Đào tạo Quốc tế','990808217','dtqt@hust.edu.vn');
INSERT INTO "Department" VALUES ('CKDL','Viện Cơ Khí Động Lực','0436252655','ckdl@hust.edu.vn');
INSERT INTO "Department" VALUES ('DMDGTT','Viện Diệt May Da Giày và Thời Trang','0436252641','dmdgtt@hust.edu.vn');
INSERT INTO "Department" VALUES ('VLKT','Viện Vật Lý Kĩ Thuật','043625612','vlkt@hust.edu.vn');
INSERT INTO "Department" VALUES ('CNTT','Viện Công Nghệ Thông Tin và Truyền Thông','043625637','cntt@hust.edu.vn');

INSERT INTO "Class" VALUES ('CK1','Cơ khí 1',2009,NULL,'CK');
INSERT INTO "Class" VALUES ('CK2','Cơ khí 2',2009,NULL,'CK');
INSERT INTO "Class" VALUES ('CK3','Cơ khí 3',2009,NULL,'CK');
INSERT INTO "Class" VALUES ('VN6A','Việt Nhật 6A',2011,NULL,'CNTT');
INSERT INTO "Class" VALUES ('VN6B','Việt Nhật 6B',2011,NULL,'CNTT');
INSERT INTO "Class" VALUES ('VN6C','Việt Nhật 6C',2011,NULL,'CNTT');
INSERT INTO "Class" VALUES ('DT1','Điện tử 1',2010,NULL,'DTVT');
INSERT INTO "Class" VALUES ('DT2','Điện tử 2',2010,NULL,'DTVT');
INSERT INTO "Class" VALUES ('DT3','Điện tử 3',2010,NULL,'DTVT');
INSERT INTO "Class" VALUES ('QSC1','Quân sự chung 1',2012,NULL,'GDQP');
INSERT INTO "Class" VALUES ('QSC2','Quân sự chung 2',2012,NULL,'GDQP');
INSERT INTO "Class" VALUES ('QSC3','Quân sự chung 3',2012,NULL,'GDQP');
INSERT INTO "Class" VALUES ('NB01','Nhật Bản 1',2010,NULL,'NN');
INSERT INTO "Class" VALUES ('NB02','Nhật Bản 2',2010,NULL,'NN');
INSERT INTO "Class" VALUES ('NB03','Nhật Bản 3',2010,NULL,'NN');
INSERT INTO "Class" VALUES ('NC1','Nhảy cao 1',2010,NULL,'GDTC');
INSERT INTO "Class" VALUES ('NC2','Nhảy cao 2',2010,NULL,'GDTC');
INSERT INTO "Class" VALUES ('NC3','Nhảy cao 3',2010,NULL,'GDTC');
INSERT INTO "Class" VALUES ('DL1','Đường lối 1',2010,NULL,'LLCT');
INSERT INTO "Class" VALUES ('DL2','Đường lối 2',2010,NULL,'LLCT');
INSERT INTO "Class" VALUES ('DL3','Đường lối 3',2010,NULL,'LLCT');
INSERT INTO "Class" VALUES ('VS1','Vi sinh 1',2011,NULL,'CNSH');
INSERT INTO "Class" VALUES ('VS2','Vi sinh 2',2011,NULL,'CNSH');
INSERT INTO "Class" VALUES ('VS3','Vi sinh 3',2011,NULL,'CNSH');
INSERT INTO "Class" VALUES ('DG1','Da giầy 1',2013,NULL,'DMDGTT');
INSERT INTO "Class" VALUES ('DG2','Da giầy 2',2013,NULL,'DMDGTT');
INSERT INTO "Class" VALUES ('DG3','Da giầy 3',2013,NULL,'DMDGTT');
INSERT INTO "Class" VALUES ('TC1','Tài chính 1',2013,NULL,'KTQL');
INSERT INTO "Class" VALUES ('TC2','Tài chính 2',2013,NULL,'KTQL');
INSERT INTO "Class" VALUES ('TC3','Tài chính 3',2013,NULL,'KTQL');
INSERT INTO "Class" VALUES ('HDC1','Hóa đại cương 1',2013,NULL,'KTHH');
INSERT INTO "Class" VALUES ('HDC2','Hóa đại cương 2',2013,NULL,'KTHH');
INSERT INTO "Class" VALUES ('HDC3','Hóa đại cương 3',2013,NULL,'KTHH');
INSERT INTO "Class" VALUES ('DK1','Điều khiển 1',2009,NULL,'TDH');
INSERT INTO "Class" VALUES ('DK2','Điều khiển 2',2009,NULL,'TDH');
INSERT INTO "Class" VALUES ('DK3','Điều khiển 3',2009,NULL,'TDH');
INSERT INTO "Class" VALUES ('VL1','Vật liệu 1',2010,NULL,'SPKT');
INSERT INTO "Class" VALUES ('VL2','Vật liệu 2',2010,NULL,'SPKT');
INSERT INTO "Class" VALUES ('VL3','Vật liệu 3',2010,NULL,'SPKT');
INSERT INTO "Class" VALUES ('TDC1','Toán đại cương 1',2010,NULL,'TTUD');
INSERT INTO "Class" VALUES ('TDC2','Toán đại cương 2',2010,NULL,'TTUD');
INSERT INTO "Class" VALUES ('TDC3','Toán đại cương 3',2010,NULL,'TTUD');
INSERT INTO "Class" VALUES ('VM1','Việt Mỹ 1',2010,NULL,'DTQT');
INSERT INTO "Class" VALUES ('VM2','Việt Mỹ 2',2010,NULL,'DTQT');
INSERT INTO "Class" VALUES ('VM3','Việt Mỹ 3',2010,NULL,'DTQT');

INSERT INTO "Student" VALUES ('20080001','LÊ ANH','NGỌC','1990-01-16',true,2008,'0123211001','20040001@gmail.com','Bắc Giang','CK1','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20080002','NGUYỄN HỮU','PHƯỚC','1990-01-19',true,2008,'0123211001','20040002@gmail.com','Hưng Yên','CK1','Còn học',1);
INSERT INTO "Student" VALUES ('20080003','PHẠM LÊ DUY','QUANG','1990-02-07',true,2008,'0123211003','20040003@gmail.com','Thái Bình','CK2','Còn học',1);
INSERT INTO "Student" VALUES ('20080004','NGUYỄN HỒNG','QUÂN','1990-02-16',true,2008,'0123211004','20040004@gmail.com','Hà Nội','CK2','Còn học',1);
INSERT INTO "Student" VALUES ('20080005','VŨ VĂN','QUYỀN','1990-04-28',true,2008,'0123211005','20040005@gmail.com','Thanh Hóa','CK3','Còn học',1);
INSERT INTO "Student" VALUES ('20080006','BÙI VĂN','THỊNH','1990-05-25',true,2008,'0123211006','20040006@gmail.com','Thái Nguyên','VN6A','Còn học',1);
INSERT INTO "Student" VALUES ('20080007','DƯƠNG ĐỨC','THỊNH','1990-06-05',true,2008,'0123211007','20040007@gmail.com','Bắc Ninh','VN6A','Còn học',1);
INSERT INTO "Student" VALUES ('20080008','BÙI ĐỨC','THỌ','1990-06-07',true,2008,'0123211008','20040008@gmail.com','Thanh Hóa','VN6B','Còn học',1);
INSERT INTO "Student" VALUES ('20080009','LÊ VĂN','THỤC','1990-06-09',true,2008,'0123211009','20040009@gmail.com','Nam Định','VN6B','Còn học',1);
INSERT INTO "Student" VALUES ('20080010','DƯƠNG VĂN','TRÌNH','1990-07-01',true,2008,'0123211010','20040010@gmail.com','Bắc Giang','VN6C','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20080011','NGUYỄN NGỌC','TRUNG','1990-07-17',true,2008,'0123211011','20040011@gmail.com','Bắc Giang','DT1','Còn học',1);
INSERT INTO "Student" VALUES ('20080012','NGUYỄN LÊ','TUẤN','1990-08-28',true,2008,'0123211012','20040012@gmail.com','Hưng Yên','DT1','Còn học',1);
INSERT INTO "Student" VALUES ('20080013','PHẠM MINH','TUẤN','1990-08-30',true,2008,'0123211013','20040013@gmail.com','Thái Bình','DT2','Còn học',1);
INSERT INTO "Student" VALUES ('20080014','TRẦN ANH','TUẤN','1990-09-03',true,2008,'0123211014','20040014@gmail.com','Hà Nội','DT2','Còn học',1);
INSERT INTO "Student" VALUES ('20080015','VŨ THANH','TUẤN','1990-10-01',true,2008,'0123211015','20040015@gmail.com','Thanh Hóa','DT3','Còn học',1);
INSERT INTO "Student" VALUES ('20080016','AN SƠN','TÙNG','1990-10-13',true,2008,'0123211016','20040016@gmail.com','Thái Nguyên','QSC1','Còn học',1);
INSERT INTO "Student" VALUES ('20080017','NGUYỄN HOÀNG','TÙNG','1990-10-27',true,2008,'0123211017','20040017@gmail.com','Bắc Ninh','QSC1','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20080018','TRƯƠNG THANH','TÙNG','1990-11-12',true,2008,'0123211018','20040018@gmail.com','Thanh Hóa','QSC2','Còn học',1);
INSERT INTO "Student" VALUES ('20080019','TRẦN VĂN','TƯỜNG','1990-11-30',true,2008,'0123211019','20040019@gmail.com','Nam Định','QSC2','Còn học',1);
INSERT INTO "Student" VALUES ('20080020','NGUYỄN XUÂN','TƯỞNG','1990-12-04',true,2008,'0123211020','20040020@gmail.com','Bắc Giang','QSC3','Còn học',1);
INSERT INTO "Student" VALUES ('20080021','NGUYỄN VĂN','UÂN','1990-12-17',true,2008,'0123211021','20040021@gmail.com','Hưng Yên','NB01','Còn học',1);
INSERT INTO "Student" VALUES ('20080022','LƯU VĂN','VINH','1990-12-22',true,2008,'0123211022','20040022@gmail.com','Thái Bình','NB01','Còn học',1);
INSERT INTO "Student" VALUES ('20090023','LÊ TÂM','HUY','1991-01-15',true,2008,'0123211023','20040023@gmail.com','Hà Nội','NB02','Còn học',1);
INSERT INTO "Student" VALUES ('20090024','NGUYỄN BÁ','MINH','1991-01-20',true,2008,'0123211024','20040024@gmail.com','Thanh Hóa','NB02','Còn học',1);
INSERT INTO "Student" VALUES ('20090025','NGUYỄN HỮU TUẤN','ANH','1991-02-24',true,2008,'0123211025','20040025@gmail.com','Thái Nguyên','NB03','Còn học',1);
INSERT INTO "Student" VALUES ('20090026','NGUYỄN THỊ PHƯƠNG','ANH','1991-03-29',true,2008,'0123211026','20040026@gmail.com','Bắc Ninh','NC1','Còn học',1);
INSERT INTO "Student" VALUES ('20090027','NGUYỄN TUẤN','ANH','1991-03-31',true,2008,'0123211027','20040027@gmail.com','Thanh Hóa','NC1','Còn học',1);
INSERT INTO "Student" VALUES ('20090028','PHẠM TUẤN','ANH','1991-04-10',true,2008,'0123211028','20040028@gmail.com','Nam Định','NC2','Còn học',1);
INSERT INTO "Student" VALUES ('20090029','CHU NGỌC','ÁNH','1991-04-12',true,2008,'0123211029','20040029@gmail.com','Bắc Giang','NC2','Còn học',1);
INSERT INTO "Student" VALUES ('20090030','PHÙNG XUÂN','BÁCH','1991-04-18',true,2008,'0123211030','20040030@gmail.com','Bắc Giang','NC3','Còn học',1);
INSERT INTO "Student" VALUES ('20090031','NGUYỄN VĂN','BIÊN','1991-05-19',true,2008,'0123211031','20040031@gmail.com','Bắc Giang','DL1','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20090032','NGUYỄN VĂN','BÌNH','1991-06-07',true,2008,'0123211032','20040032@gmail.com','Hưng Yên','DL1','Còn học',1);
INSERT INTO "Student" VALUES ('20090033','THÁI DUY','CHIẾN','1991-06-26',true,2008,'0123211033','20040033@gmail.com','Thái Bình','DL2','Còn học',1);
INSERT INTO "Student" VALUES ('20090034','NGUYỄN XUÂN','CHÍNH','1991-07-02',true,2008,'0123211034','20040034@gmail.com','Hà Nội','DL2','Còn học',1);
INSERT INTO "Student" VALUES ('20090035','BÙI CÔNG','CƯỜNG','1991-07-08',true,2008,'0123211035','20040035@gmail.com','Thanh Hóa','DL3','Còn học',1);
INSERT INTO "Student" VALUES ('20090036','CÙ XUÂN','CƯỜNG','1991-07-20',true,2008,'0123211036','20040036@gmail.com','Thái Nguyên','VS1','Còn học',1);
INSERT INTO "Student" VALUES ('20090037','VŨ VĂN','CÔNG','1991-07-21',true,2008,'0123211037','20040037@gmail.com','Bắc Ninh','VS1','Còn học',1);
INSERT INTO "Student" VALUES ('20090038','NGUYỄN HUY','CƯỜNG','1991-07-25',true,2008,'0123211038','20040038@gmail.com','Thanh Hóa','VS2','Còn học',1);
INSERT INTO "Student" VALUES ('20090039','BÙI VĂN','DOANH','1991-07-30',true,2008,'0123211039','20040039@gmail.com','Nam Định','VS2','Còn học',1);
INSERT INTO "Student" VALUES ('20090040','ĐẶNG QUANG','DUY','1991-09-16',true,2008,'0123211040','20040040@gmail.com','Bắc Giang','VS3','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20090041','NGUYỄN PHƯƠNG','DUY','1991-10-04',true,2008,'0123211041','20040041@gmail.com','Bắc Giang','DG1','Còn học',1);
INSERT INTO "Student" VALUES ('20090042','DƯƠNG TUẤN','DŨNG','1991-10-09',true,2008,'0123211042','20040042@gmail.com','Bắc Giang','DG1','Còn học',1);
INSERT INTO "Student" VALUES ('20090043','NGUYỄN HOÀNG','DŨNG','1991-10-11',true,2008,'0123211043','20040043@gmail.com','Hưng Yên','DG2','Còn học',1);
INSERT INTO "Student" VALUES ('20090044','TRẦN THẾ','DŨNG','1991-10-18',true,2008,'0123211044','20040044@gmail.com','Thái Bình','DG2','Còn học',1);
INSERT INTO "Student" VALUES ('20090045','ĐỖ CÔNG','DƯƠNG','1991-11-14',true,2008,'0123211045','20040045@gmail.com','Hà Nội','DG3','Còn học',1);
INSERT INTO "Student" VALUES ('20090046','TRẦN QUANG','DƯƠNG','1991-11-28',true,2008,'0123211046','20040046@gmail.com','Thanh Hóa','TC1','Còn học',1);
INSERT INTO "Student" VALUES ('20090047','CHU ĐỨC','ĐẠO','1991-12-03',true,2008,'0123211047','20040047@gmail.com','Thái Nguyên','TC2','Còn học',1);
INSERT INTO "Student" VALUES ('20090048','NGUYỄN BÁ','ĐỊNH','1991-12-10',true,2008,'0123211048','20040048@gmail.com','Bắc Ninh','TC3','Còn học',1);
INSERT INTO "Student" VALUES ('20090049','VŨ QUÝ','ĐÔN','1992-01-09',true,2008,'0123211049','20040049@gmail.com','Thanh Hóa','TC1','Nghỉ học',1);
INSERT INTO "Student" VALUES ('20100050','PHẠM VĂN','ĐÔNG','1992-01-24',true,2008,'0123211050','20040050@gmail.com','Nam Định','TC2','Còn học',1);

UPDATE "Class" SET "MoniterID" = '20080001' WHERE "ClaID" = 'CK1';
UPDATE "Class" SET "MoniterID" = '20080003' WHERE "ClaID" = 'CK2';
UPDATE "Class" SET "MoniterID" = '20080005' WHERE "ClaID" = 'CK3';
UPDATE "Class" SET "MoniterID" = '20080006' WHERE "ClaID" = 'VN6A';
UPDATE "Class" SET "MoniterID" = '20080008' WHERE "ClaID" = 'VN6B';
UPDATE "Class" SET "MoniterID" = '20080010' WHERE "ClaID" = 'VN6C';
UPDATE "Class" SET "MoniterID" = '20080011' WHERE "ClaID" = 'DT1';
UPDATE "Class" SET "MoniterID" = '20080013' WHERE "ClaID" = 'DT2';
UPDATE "Class" SET "MoniterID" = '20080015' WHERE "ClaID" = 'DT3';
UPDATE "Class" SET "MoniterID" = '20080016' WHERE "ClaID" = 'QSC1';
UPDATE "Class" SET "MoniterID" = '20080018' WHERE "ClaID" = 'QSC2';
UPDATE "Class" SET "MoniterID" = '20080020' WHERE "ClaID" = 'QSC3';
UPDATE "Class" SET "MoniterID" = '20080021' WHERE "ClaID" = 'NB01';
UPDATE "Class" SET "MoniterID" = '20090023' WHERE "ClaID" = 'NB02';
UPDATE "Class" SET "MoniterID" = '20090025' WHERE "ClaID" = 'NB03';
UPDATE "Class" SET "MoniterID" = '20090026' WHERE "ClaID" = 'NC1';
UPDATE "Class" SET "MoniterID" = '20090028' WHERE "ClaID" = 'NC2';
UPDATE "Class" SET "MoniterID" = '20090030' WHERE "ClaID" = 'NC3';
UPDATE "Class" SET "MoniterID" = '20090031' WHERE "ClaID" = 'DL1';
UPDATE "Class" SET "MoniterID" = '20090033' WHERE "ClaID" = 'DL2';
UPDATE "Class" SET "MoniterID" = '20090035' WHERE "ClaID" = 'DL3';
UPDATE "Class" SET "MoniterID" = '20090036' WHERE "ClaID" = 'VS1';
UPDATE "Class" SET "MoniterID" = '20090038' WHERE "ClaID" = 'VS2';
UPDATE "Class" SET "MoniterID" = '20090040' WHERE "ClaID" = 'VS3';
UPDATE "Class" SET "MoniterID" = '20090041' WHERE "ClaID" = 'DG1';
UPDATE "Class" SET "MoniterID" = '20090043' WHERE "ClaID" = 'DG2';
UPDATE "Class" SET "MoniterID" = '20090045' WHERE "ClaID" = 'DG3';
UPDATE "Class" SET "MoniterID" = '20090046' WHERE "ClaID" = 'TC1';
UPDATE "Class" SET "MoniterID" = '20090048' WHERE "ClaID" = 'TC2';
UPDATE "Class" SET "MoniterID" = '20100050' WHERE "ClaID" = 'TC3';

INSERT INTO "Organization" VALUES ('CLBTA','Câu lạc bộ Tiếng Anh',NULL,'Nguyễn Văn B','clbta@gmail.com','23949204032');
INSERT INTO "Organization" VALUES ('CLBTN','Câu lạc bộ Tiếng Nhật',NULL,'Nguyễn Văn A','clbtn@gmail.com','1294023851');
INSERT INTO "Organization" VALUES ('DTN','Đội tình nguyện',NULL,'PHẠM VĂN ĐÔNG','dtn@gmail.com','2349829089');
INSERT INTO "Organization" VALUES ('LCNTT','Liên chi đoàn viện CNTT&TT',NULL,'Lê Xuân Thành','thanhlx@hust.edu.vn','09236521456');

INSERT INTO "Participation" VALUES ('20092045','CLBTN','Thành viên','2011-09-19','2012-09-19','Xuất Sắc',1);
INSERT INTO "Participation" VALUES ('20090024','CLBTA','Thành viên','2011-07-24','2012-12-19','Nghiêm Túc',1);
INSERT INTO "Participation" VALUES ('20080003','DTN','Đội trưởng','2009-09-19','2012-04-19','Nhiệt tình',1);

INSERT INTO "Event" VALUES ('OSHOU11','Tết truyền thống Nhật Bản 2011','Kí túc xá B6','2011-01-19','2011-01-19',1000,3);
INSERT INTO "Event" VALUES ('OSHOU12','Lễ hội Tết truyền thống Nhật Bản 2012','Kí túc xá B6','2012-01-19','2012-01-19',3000,4);
INSERT INTO "Event" VALUES ('NTS','November Talent Show','Kí túc xá B6','2013-12-23','2013-12-24',3000,4);
INSERT INTO "Event" VALUES ('SHCD','Sinh hoạt công dân','Hội trường C2','2013-12-23','2013-12-24',5000,1);

INSERT INTO "EvtOrg" VALUES ('CLBTN','OSHOU11','Vui vẻ');
INSERT INTO "EvtOrg" VALUES ('CLBTN','OSHOU12','Náo nhiệt');
INSERT INTO "EvtOrg" VALUES ('LCNTT','NTS', 'Thành Công');

--Tài khoản đăng nhập mặc định: admin pass:1234
INSERT INTO "Account" VALUES ('admin','hanoi','81dc9bdb52d04dc2036dbd8313ed055',1);
INSERT INTO "Account" VALUES ('minh','hanoi','81dc9bdb52d04dc2036dbd8313ed055',2);
INSERT INTO "Account" VALUES ('guest','hanoi','84e343a0486ff05530df6c705c8bb4',3);

--
\c postgres
\encoding utf8
--DROP DATABASE qldv;