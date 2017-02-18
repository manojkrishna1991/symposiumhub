ALTER TABLE symposium ADD compressedPath varchar(255);

ALTER TABLE symposium ADD linkPath varchar(255);


CREATE TABLE papers
(
id int(11)  NOT NULL AUTO_INCREMENT,
filePath varchar(255) DEFAULT NULL,
symposiumid varchar(255) DEFAULT NULL,
userName varchar(255) DEFAULT NULL,
collegeName varchar(255) DEFAULT NULL,
contactNo varchar(255) DEFAULT NULL,
emailId varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY symposiumid (symposiumid)
);



CREATE TABLE symposiumcomment
(
id int(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
comment varchar(255),
symposiumid varchar(255),
SymposiumComment varchar(255),
KEY symposiumid (symposiumid),
FOREIGN KEY (symposiumid) REFERENCES symposium(symposiumid)
)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

alter table symposiumcomment add column  userId  varchar(255) NOT NULL;

ALTER TABLE symposiumcomment ADD KEY userId (userId) ;

ALTER TABLE symposiumcomment
ADD CONSTRAINT fk_Percomment
FOREIGN KEY (userId)
REFERENCES user(USER_ID);


CREATE TABLE symposiumcommentsreply
(
symposiumReplyId int(11)  NOT NULL AUTO_INCREMENT,
reply varchar(255) ,
symposiumcomments int ,
 userId  varchar(255) ,
PRIMARY KEY (symposiumReplyId),
KEY symposiumcomments (symposiumcomments),
KEY userId (userId)  ,
FOREIGN KEY (symposiumcomments) REFERENCES symposiumcomment(id),
FOREIGN KEY (userId) REFERENCES user(USER_ID)
 
)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

alter table symposiumcomment add postedDate TIMESTAMP;

alter table symposiumcommentsreply add postedDate TIMESTAMP;

CREATE TABLE profile
(
id int(11)  NOT NULL AUTO_INCREMENT,
aboutMe TEXT,
gender varchar(255)  ,
photo varchar(255) ,
place varchar(255) ,
userId varchar(255) ,
PRIMARY KEY (id),
KEY userId (userId)  ,
FOREIGN KEY (userId) REFERENCES user(USER_ID)
)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

CREATE TABLE review
(
id int(11)  NOT NULL AUTO_INCREMENT,
review TEXT,	
rating int(11)  ,
userId varchar(255) ,
collegeId int(11),
PRIMARY KEY (id),
KEY userId (userId)  ,
FOREIGN KEY (userId) REFERENCES user(USER_ID)
)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;


CREATE TABLE college
(
id int(11)  NOT NULL AUTO_INCREMENT,
name TEXT,	
createDate TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

alter table college add column rating int(11);

alter table college add column compressedPath varchar(255);

alter table college add column compressedPathOne varchar(255);

update college set compressedPath='/resources/assets/images/wireframe/image.png';


create table friends(

id int(11) AUTO_INCREMENT,

toprofileid  int(11),

fromprofileid int(11),

relationstatus varchar(255),

createdDate  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,

primary key(id)

)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

CREATE TABLE `updates` (
`update_id` INT(11) AUTO_INCREMENT ,
`message` VARCHAR(234),
`profile_id_fk` int(11),
`created` INT(11) ,
`ip` VARCHAR(45),
PRIMARY KEY (`update_id`),
KEY profile_id_fk (profile_id_fk)  ,
FOREIGN KEY (profile_id_fk) REFERENCES profile(id))ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

CREATE  TABLE `logindb`.`notification` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT ,
  `notification` VARCHAR(234) NULL ,
  `Type` VARCHAR(45) NULL ,
  PRIMARY KEY (`Id`) )ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;


alter table notification add column updatedDate timestamp  DEFAULT CURRENT_TIMESTAMP;
alter table notification add column isread int(11) DEFAULT 0;
alter table notification add column toprofileid int(11) ;


create table notificationLastViewed(

id int(11) NOT NULL AUTO_INCREMENT,

NotificationLastViewed TIMESTAMP ,

profileid  int(11),

primary key(id)

)ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
 	


