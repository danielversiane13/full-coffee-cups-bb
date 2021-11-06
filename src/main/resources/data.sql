INSERT INTO CUSTOMER 
(ID, ACTIVE, CREATED_AT, UPDATED_AT, BIRTH_DATE, CPF_CNPJ, EMAIL, NAME, PHONE)
VALUES 
('9777d8b24f7648ee8168d8e7ac424cb8', 1, CURRENT_TIME(), CURRENT_TIME(), '2020-01-31', '123456789012', 'tonico@outlook.com', 'Tonico', '11-2136-1401'),
('111f46323fa44dc288d51d359b8dc902', 1, CURRENT_TIME(), CURRENT_TIME(), '2020-02-01', '123456789013', 'chico@outlook.com', 'Chico', '11-2136-1402'),
('6bb1de1ccc1f435bbfbdb003b1e2d67f', 1, CURRENT_TIME(), CURRENT_TIME(), '2020-03-02', '123456789014', 'francisco@outlook.com', 'Francisco', '11-2136-1403'),
('b59e80dc9bc54534bd82c703ecbd870c', 1, CURRENT_TIME(), CURRENT_TIME(), '2020-04-03', '123456789015', 'xico@outlook.com', 'Xico', '11-2136-1404'),
('38c667da06b144608428ff32d7e93b92', 1, CURRENT_TIME(), CURRENT_TIME(), '2020-05-04', '123456789016', 'joao@outlook.com', 'João', '11-2136-1405');

INSERT INTO BANK_ACCOUNT
(ID, ACTIVE, CREATED_AT, UPDATED_AT, ACCOUNT, BALANCE, CUSTOMER_ID)
VALUES 
('ef4f7210cf7f4b808d3088990922dc4d', 1, CURRENT_TIME(), CURRENT_TIME(), '123456', 150, '9777d8b24f7648ee8168d8e7ac424cb8'),
('f64254293200438685ee324a0e317b04', 1, CURRENT_TIME(), CURRENT_TIME(), '789012', 300, '111f46323fa44dc288d51d359b8dc902');
('d8c4a19a8dfb41ab9864e4ade1c3031c', 1, CURRENT_TIME(), CURRENT_TIME(), '256415', 500, '111f46323fa44dc288d51d359b8dc902');