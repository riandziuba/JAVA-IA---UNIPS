INSERT INTO `Order` (id, dateTime, status, customerName, customerDocument, customerPhone, customerAddress)
VALUES
(1, '2024-10-13 12:30:00', 'DONE', 'Chaves', '123.456.789-00', '(11) 91234-5678', 'Vila 8, Barril do Chaves'),
(2, '2024-10-13 13:00:00', 'DONE', 'Seu Madruga', '234.567.890-11', '(11) 92345-6789', 'Vila 8, Casa 72'),
(3, '2024-10-13 13:15:00', 'DONE', 'Seu Madroga, Primo do Seu Madruga', '345.678.901-22', '(11) 93456-7890', 'Vila 8, Casa 14'),
(4, '2024-10-13 14:00:00', 'DONE', 'Quico', '456.789.012-33', '(11) 94567-8901', 'Vila 8, Casa 14'),
(5, '2024-10-13 14:30:00', 'DONE', 'Professor Girafales', '567.890.123-44', '(11) 95678-9012', 'Vila 8, Sala dos Professores'),
(6, '2024-10-13 15:00:00', 'DONE', 'Dona Clotilde', '678.901.234-55', '(11) 96789-0123', 'Vila 8, Casa 71'),
(7, '2024-10-13 15:15:00', 'DONE', 'Seu Barriga', '789.012.345-66', '(11) 97890-1234', 'Vila 8, Casa 20'),
(8, '2024-10-13 15:30:00', 'DONE', 'Nhonho', '890.123.456-77', '(11) 98901-2345', 'Vila 8, Casa 20'),
(9, '2024-10-13 16:00:00', 'DONE', 'Paty', '901.234.567-88', '(11) 99012-3456', 'Vila 8, Casa 15'),
(10, '2024-10-13 16:30:00', 'DONE', 'Godinez', '012.345.678-99', '(11) 90123-4567', 'Vila 8, Casa 13'),
(11, '2024-10-13 17:00:00', 'DONE', 'Dona Neves', '123.678.901-00', '(11) 91123-4567', 'Vila 8, Casa 72'),
(12, '2024-10-13 17:15:00', 'DONE', 'Pópis', '234.789.012-11', '(11) 92234-5678', 'Vila 8, Casa 14'),
(13, '2024-10-13 17:30:00', 'DONE', 'Jaiminho', '345.890.123-22', '(11) 93345-6789', 'Correios, Vila 8'),
(14, '2024-10-13 18:00:00', 'DONE', 'Chiquinha', '456.901.234-33', '(11) 94456-7890', 'Vila 8, Casa 73'),
(15, '2024-10-13 18:30:00', 'DONE', 'Malicha', '567.012.345-44', '(11) 95567-8901', 'Vila 8, Casa 71');

INSERT INTO Order_SEQ (next_val) VALUES (16);

INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(1, 1, 3.50, 'Com bastante presunto, por favor!', 1, 1),
(2, 2, 2.99, 'Bem gelado', 1, 2);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(3, 1, 5.99, 'Pode ser bem queimadinho!', 2, 3),
(4, 1, 1.99, NULL, 2, 8);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(5, 1, 12.99, 'Sem pimenta, por favor.', 3, 5),
(6, 1, 5.99, NULL, 3, 11);

INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(7, 1, 4.99, 'Capriche na pipoca!', 4, 6),
(8, 1, 2.50, 'Bem doce', 4, 7);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(9, 1, 4.99, NULL, 5, 9),
(10, 1, 5.99, 'Para levar', 5, 11);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(11, 2, 6.99, 'Bem temperada, por favor.', 6, 4),
(12, 1, 3.50, 'A sobremesa favorita!', 6, 10);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(13, 1, 5.99, 'Extra doce, por favor.', 7, 11),
(14, 2, 2.99, 'Sem gelo.', 7, 2);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(15, 1, 4.99, 'Recheado com extra doce de leite.', 8, 9),
(16, 3, 4.99, NULL, 8, 6);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(17, 1, 12.99, 'Sem cebola, por favor.', 9, 5),
(18, 2, 2.50, 'Com pouco açúcar.', 9, 7);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(19, 2, 3.50, 'Para viagem.', 10, 10),
(20, 1, 4.99, 'Com cobertura extra.', 10, 9);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(21, 1, 3.50, 'Extra crocante', 11, 1),
(22, 2, 5.99, 'Bem servido', 11, 11);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(23, 1, 6.99, 'Com limão extra', 12, 4),
(24, 1, 2.50, 'Bem doce', 12, 7);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(25, 1, 4.99, 'Para viagem', 13, 9),
(26, 1, 3.50, NULL, 13, 10);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(27, 1, 5.99, 'Sem açúcar', 14, 11),
(28, 1, 4.99, 'Bem salgada', 14, 6);


INSERT INTO ItemOrder (id, amount, unitaryPrice, observation, order_id, itemMenu_id)
VALUES
(29, 3, 2.99, 'Com gelo', 15, 2),
(30, 1, 3.50, 'Colorida', 15, 10);

INSERT INTO ItemOrder_SEQ (next_val) VALUES (31);
