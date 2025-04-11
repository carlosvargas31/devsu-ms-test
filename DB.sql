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

INSERT INTO `persons` (`id`, `address`, `age`, `gender`, `identification`, `name`, `phone`)
VALUES
    ('1', 'Carrera 17', '25', 'MALE', '1234645344', 'Carlos Vargas', '+573117362461');


INSERT INTO `clients` (`password`, `status`, `id_client`)
VALUES
    ('carlos', b'1', '1');

CREATE TABLE accounts
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    number_account  VARCHAR(10)           NOT NULL,
    type_account    VARCHAR(255)          NOT NULL,
    initial_balance DECIMAL(10, 2)        NOT NULL,
    status          BIT(1)                NOT NULL,
    client_id       BIGINT                NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE movements
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    date          datetime              NOT NULL,
    movement_type VARCHAR(255)          NOT NULL,
    value         DECIMAL(10, 2)        NOT NULL,
    balance       DECIMAL(10, 2)        NOT NULL,
    account_id    BIGINT                NOT NULL,
    CONSTRAINT pk_movements PRIMARY KEY (id)
);

ALTER TABLE accounts
    ADD CONSTRAINT uc_accounts_number_account UNIQUE (number_account);

ALTER TABLE movements
    ADD CONSTRAINT FK_MOVEMENTS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (id);

INSERT INTO `accounts` (`id`, `number_account`, `type_account`, `initial_balance`, `status`, `client_id`)
VALUES
    ('1', '0948944179', 'SAVINGS', '1000.00', b'1', '1');


INSERT INTO `movements` (`id`, `date`, `movement_type`, `value`, `balance`, `account_id`)
VALUES
    ('1', '2025-04-05 21:19:19', 'DEPOSIT', '12.99', '1012.99', '1'),
    ('2', '2025-04-10 21:23:28', 'DEPOSIT', '500.99', '1513.98', '1'),
    ('3', '2025-04-12 21:23:40', 'DEPOSIT', '500.99', '2014.97', '1'),
    ('4', '2025-04-13 21:24:35', 'WITHDRAWAL', '1000.00', '1014.97', '1');


