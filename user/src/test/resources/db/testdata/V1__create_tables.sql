CREATE TABLE IF NOT EXISTS clients
(
    password  VARCHAR(255) NOT NULL,
    status    BIT(1)       NOT NULL,
    id_client BIGINT       NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id_client)
);

CREATE TABLE IF NOT EXISTS persons
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    address        VARCHAR(255)          NOT NULL,
    age            INT                   NOT NULL,
    gender         ENUM('MALE', 'FEMALE') NOT NULL,
    identification VARCHAR(50)           NOT NULL,
    `name`         VARCHAR(150)          NOT NULL,
    phone          VARCHAR(255)          NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE persons
    ADD CONSTRAINT UKq9hehhi7b6xsv7sp8cpb4mo21 UNIQUE (identification);

ALTER TABLE clients
    ADD CONSTRAINT FKe9et7k3n2ne6mk4nupsbaj83s FOREIGN KEY (id_client) REFERENCES persons (id) ON DELETE NO ACTION;