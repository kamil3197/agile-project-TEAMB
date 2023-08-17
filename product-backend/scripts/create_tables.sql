
-- US014: create band

CREATE TABLE IF NOT EXISTS Band (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	level INT(1) NOT NULL,
	responsibilities varchar(255) NOT NULL
);
    
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
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_id INT,
    summary VARCHAR(500),
    sharepoint_link VARCHAR(500),
    FOREIGN KEY (role_id) REFERENCES JobRoles(job_role_id)

    );
  -- US013 add new capability
    CREATE TABLE IF NOT EXISTS  Capability (
        capability_id INT PRIMARY KEY AUTO_INCREMENT,
        capability_name VARCHAR(80),
        lead_name VARCHAR(80),
        lead_photo MEDIUMBLOB,
        lead_message VARCHAR(500)
    );

ALTER TABLE JobRoles
ADD COLUMN band_id INT,
ADD CONSTRAINT fk_band
    FOREIGN KEY (band_id)
    REFERENCES Band(id);

ALTER TABLE Band
ADD COLUMN capability_id INT,
ADD CONSTRAINT fk_band
    FOREIGN KEY (capability_id)
    REFERENCES Capability (capability_id);
