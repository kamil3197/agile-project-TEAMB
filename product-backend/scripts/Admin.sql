DELIMITER $$
DROP PROCEDURE IF EXISTS create_band_table $$
CREATE PROCEDURE create_band_table()
BEGIN
    START TRANSACTION;


    CREATE TABLE IF NOT EXISTS Band (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        level VARCHAR(255) NOT NULL,
        responsibilities varchar(255) NOT NULL
    );

	INSERT INTO Band (name, level, responsibilities) VALUES
	('Andrzej Duda', 'Head of Cloud Practice', 'something'),
    ('Mariusz Rumunowicz', 'Head of Cloud Practice', 'something'),
    ('Marian Pazdzioch', 'Head of Cloud Practice', 'something');

    GET DIAGNOSTICS @rows = ROW_COUNT;
    IF @row = 0 THEN
        ROLLBACK;
        SELECT 'Transaction rolled back due to error';
    ELSE
        COMMIT;
        SELECT 'Transaction commited succesffully';
    END IF;

END $$
DELIMITER ;
CALL create_band_table();