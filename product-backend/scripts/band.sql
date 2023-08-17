DELIMITER $$

DROP PROCEDURE IF EXISTS create_band_table $$

CREATE PROCEDURE create_band_table()
BEGIN
    DECLARE @rows INT;
    DECLARE @message VARCHAR(1000);

    START TRANSACTION;

    CREATE TABLE IF NOT EXISTS Band (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        level INT(1) NOT NULL,
        responsibilities varchar(255) NOT NULL,
        capability_id INT,
        FOREIGN KEY (capability_id) REFERENCES Capability (capability_id)
    );

    GET DIAGNOSTICS @rows = ROW_COUNT;
    IF @rows = 0 THEN
        SET @message = 'Transaction rolled back due to error';
        ROLLBACK;
    ELSE
        SET @message = 'Transaction committed successfully';
        COMMIT;
    END IF;

    SELECT @message;

END $$

DELIMITER ;

CALL create_band_table();
