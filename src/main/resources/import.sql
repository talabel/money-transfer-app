INSERT INTO `money-transfer-app`.users (id, created_at, first_name, last_name, status, updated_at) VALUES (1, null, 'Tal', 'Abel', 1, null);
INSERT INTO `money-transfer-app`.users (id, created_at, first_name, last_name, status, updated_at) VALUES (2, null, 'Aziza', 'Abykeeva', 0, null);
INSERT INTO `money-transfer-app`.users (id, created_at, first_name, last_name, status, updated_at) VALUES (3, null, 'Mars', 'Abykeev', 0, null);

INSERT INTO `money-transfer-app`.transactions (id, created_at, transaction_id, updated_at, user_id) VALUES (1, null, 'Transaction 1', null, 1);
INSERT INTO `money-transfer-app`.transactions (id, created_at, transaction_id, updated_at, user_id) VALUES (2, null, 'Transaction 2', null, 1);
INSERT INTO `money-transfer-app`.transactions (id, created_at, transaction_id, updated_at, user_id) VALUES (3, null, 'Transaction 3', null, 2);