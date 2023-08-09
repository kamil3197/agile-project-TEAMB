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

-- US014 Specification table
CREATE TABLE IF NOT EXISTS Specifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_id INT,
    summary VARCHAR(500),
    description TEXT,
    sharepoint_link VARCHAR(500),
    FOREIGN KEY (role_id) REFERENCES JobRoles(job_role_id)
)
