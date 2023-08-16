
-- US001: view job roles
create table IF NOT EXISTS JobRoles (
    job_role_id INT primary key AUTO_INCREMENT,
    job_role_title varchar(100)
);

-- US024: registration system
CREATE TABLE IF NOT EXISTS `User` (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,
    email varchar(125) NOT NULL UNIQUE,
    password varchar(64) NOT NULL,
    role varchar(64) NOT NULL
);

-- US014 Specification table
CREATE TABLE IF NOT EXISTS Specifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_id INT,
    summary VARCHAR(500),
    sharepoint_link VARCHAR(500),
    FOREIGN KEY (role_id) REFERENCES JobRoles(job_role_id)
);

CREATE TABLE IF NOT EXISTS Capability (
    id INT AUTO_INCREMENT PRIMARY KEY,
    capability_name VARCHAR(15),
    lead_name VARCHAR(15),
    lead_photo BLOB,
    lead_message VARCHAR(255)
);