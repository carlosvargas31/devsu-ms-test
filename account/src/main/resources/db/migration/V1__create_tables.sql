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