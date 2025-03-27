INSERT INTO companies (cuit, name, created_time)
VALUES ('30-12345678-9', 'Empresa Reciente 1', DATEADD('MONTH', -1, CURRENT_TIMESTAMP)),
       ('30-98765432-1', 'Empresa Reciente 2', DATEADD('MONTH', -1, CURRENT_TIMESTAMP)),
       ('30-11111111-1', 'Empresa Antigua', DATEADD('MONTH', -6, CURRENT_TIMESTAMP));

-- Insertar transacciones
INSERT INTO transactions (uid, transaction_date, amount, company_cuit, debit_account, credit_account)
VALUES (random_uuid(), DATEADD('DAY', -5, CURRENT_TIMESTAMP), 1000.50, '30-12345678-9', 'ACC123', 'ACC456'),
       (random_uuid(), DATEADD('DAY', -10, CURRENT_TIMESTAMP), 2500.75, '30-98765432-1', 'ACC789', 'ACC012'),
       (random_uuid(), DATEADD('MONTH', -3, CURRENT_TIMESTAMP), 500.25, '30-11111111-1', 'ACC345', 'ACC678');