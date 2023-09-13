CREATE TABLE `tech`(
    `tech_id` INT AUTO_INCREMENT PRIMARY KEY,
    `tech_number` VARCHAR(255) NOT NULL UNIQUE,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL
);

CREATE TABLE `quote`(
    `quote_id` INT AUTO_INCREMENT PRIMARY KEY,
    `tech_id` INT NOT NULL,
    `source` VARCHAR(25) NOT NULL,
    `text` VARCHAR(512) NOT NULL,
    `date_recorded` DATE NOT NULL,
    foreign key (tech_id) references tech(tech_id)
);

CREATE SEQUENCE `tech_seq` INCREMENT BY 50;

CREATE SEQUENCE `quote_seq` INCREMENT BY 50;
