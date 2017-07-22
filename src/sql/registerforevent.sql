Create table registrations(

  id int(11) NOT NULL AUTO_INCREMENT,
  collegeId varchar(255) DEFAULT NULL,
  collegeName varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  fullName varchar(255) DEFAULT NULL,
  phoneNumber varchar(255) DEFAULT NULL,
  registerDate datetime DEFAULT NULL,
  eventid int(11) DEFAULT NULL,
  isshow tinyint(1) DEFAULT NULL,
  isMailSent tinyint(1) DEFAULT '0',
  PRIMARY KEY (id),
  KEY eventid (eventid),
  CONSTRAINT `registrationsfk` FOREIGN KEY (`eventid`) REFERENCES `genericevent` (`eventid`)
)ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ;