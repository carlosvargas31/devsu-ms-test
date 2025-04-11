INSERT INTO `accounts` (`id`, `number_account`, `type_account`, `initial_balance`, `status`, `client_id`)
VALUES
    ('1', '0948944179', 'SAVINGS', '1000.00', b'1', '1');


INSERT INTO `movements` (`id`, `date`, `movement_type`, `value`, `balance`, `account_id`)
VALUES
    ('1', '2025-04-05 21:19:19', 'DEPOSIT', '12.99', '1012.99', '1'),
    ('2', '2025-04-10 21:23:28', 'DEPOSIT', '500.99', '1513.98', '1'),
    ('3', '2025-04-12 21:23:40', 'DEPOSIT', '500.99', '2014.97', '1'),
    ('4', '2025-04-13 21:24:35', 'WITHDRAWAL', '1000.00', '1014.97', '1');


