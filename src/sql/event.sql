CREATE TABLE workshopcoordinator (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  emailid varchar(255) DEFAULT NULL,
  phoneno varchar(255) DEFAULT NULL,
  workshopid int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY workshopid (workshopid),
  CONSTRAINT coordinator_ibfk_2 FOREIGN KEY (workshopid) REFERENCES workshop (workshopid)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

CREATE TABLE workshop
(
workshopid int(11)  NOT NULL AUTO_INCREMENT,
name varchar(255) NOT NULL,
dateOfEvent TIMESTAMP,
regEmail varchar(255) NOT NULL,
content TEXT,	
createDate TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (workshopid)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;





CREATE TABLE eventcoordinator (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  emailid varchar(255) DEFAULT NULL,
  phoneno varchar(255) DEFAULT NULL,
  eventid int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY eventid (eventid),
  CONSTRAINT coordinator_ibfk_4 FOREIGN KEY (eventid) REFERENCES genericevent (eventid)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

CREATE TABLE genericevent
(
eventid int(11)  NOT NULL AUTO_INCREMENT,
name varchar(255) NOT NULL,
dateOfEvent datetime,
regEmail varchar(255) NOT NULL,
eventType varchar(255) NOT NULL,
department varchar(255) NOT NULL,
organizationName varchar(255) NOT NULL,
userId varchar(255) NOT NULL,
content TEXT,	
createDate TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (eventid)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


alter table genericevent add paymentDetail varchar(255)  NULL;

alter table genericevent add imageUrl varchar(255)  NULL;



CREATE TABLE event_registration
(
 id int(11)  NOT NULL AUTO_INCREMENT,
 fullName tinyint(1),
 phoneNumber   tinyint(1),
 email         tinyint(1), 
 collegeName   tinyint(1),   
 collegeId     tinyint(1),  
 registrationDate TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
 eventid   int(11) NOT NULL,
 PRIMARY KEY (id),
 INDEX eve_ind(eventid),
 FOREIGN KEY (eventid) REFERENCES genericevent(eventid) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;