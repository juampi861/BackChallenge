INSERT INTO companies (cuit, name, created_time)
VALUES ('30123456789', 'Empresa Reciente 1', DATEADD('MONTH', -1, CURRENT_TIMESTAMP)),
       ('30987654321', 'Empresa Reciente 2', DATEADD('MONTH', -1, CURRENT_TIMESTAMP)),
       ('30111111111', 'Empresa Antigua', DATEADD('MONTH', -6, CURRENT_TIMESTAMP));

-- Insertar transacciones
INSERT INTO transactions (uid, transaction_date, amount, company_cuit, debit_account, credit_account)
VALUES (random_uuid(), DATEADD('DAY', -5, CURRENT_TIMESTAMP), 1000.50, '30123456789', 'ACC123', 'ACC456'),
       (random_uuid(), DATEADD('DAY', -10, CURRENT_TIMESTAMP), 2500.75, '30987654321', 'ACC789', 'ACC012'),
       (random_uuid(), DATEADD('MONTH', -3, CURRENT_TIMESTAMP), 500.25, '30111111111', 'ACC345', 'ACC678');