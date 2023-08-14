DROP DATABASE TeamA_{{USERNAME}};
CREATE DATABASE TeamA_{{USERNAME}};
use TeamA_{{USERNAME}};

-- US001 view job roles
create table IF NOT EXISTS JobRoles (
    job_role_id INT primary key AUTO_INCREMENT,
    job_role_title varchar(100)
);

-- US024 registration system
CREATE TABLE IF NOT EXISTS `User` (
id INT primary key AUTO_INCREMENT,
email varchar(64) NOT NULL UNIQUE,
password varchar(64) NOT NULL,
role varchar(64) NOT NULL
);

-- US013 add new capability
CREATE TABLE IF NOT EXISTS  Capability (
    id INT PRIMARY KEY AUTO_INCREMENT,
    capabilityName VARCHAR(15),
    leadName VARCHAR(15),
    leadPhoto LONGBLOB,
    leadMessage TEXT
);
