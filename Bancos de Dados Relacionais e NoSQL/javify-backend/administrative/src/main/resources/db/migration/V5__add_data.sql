-- V5__insere_dados_restantes.sql
-- Inclui os dados iniciais que trabalhamos nas aulas de fundamentos


INSERT INTO plan (name, price, has_advertising, members_limit, offline_mode, description)
VALUES
    ('Family',  59.90, false, 6, true, 'O plano ideal para toda família.'),
    ('Student', 10.90, false, 1, true, 'O plano para você ouvir por no máximo duas horas por dia');


INSERT INTO adress (postal_code, street, neighborhood) VALUES
    ('01001000', 'Praça da Sé', 'Sé'),
    ('01311000', 'Av. Paulista', 'Bela Vista'),
    ('01402000', 'Rua Oscar Freire', 'Jardins'),
    ('04534000', 'Av. Brigadeiro Faria Lima', 'Itaim Bibi'),
    ('05424000', 'Rua Teodoro Sampaio', 'Pinheiros'),
    ('01239000', 'Av. Brigadeiro Luís Antônio', 'Bela Vista'),
    ('04014000', 'Rua da Consolação', 'Consolação'),
    ('05422000', 'Rua Pamplona', 'Bela Vista'),
    ('01415000', 'Rua Haddock Lobo', 'Jardins'),
    ('01508000', 'Rua da Glória', 'Bela Vista');


INSERT INTO "user" (id, name, email, password_hash, is_holder, postal_code, birthday) VALUES
    (1, 'João Silva', 'joao.silva@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01001000', '1990-05-15'),
    (2, 'Maria Santos', 'maria.santos@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01311000', '1985-08-22'),  -- Titular Family
    (3, 'Pedro Oliveira', 'pedro.oliveira@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01402000', '1992-03-10'), -- Membro Family
    (4, 'Ana Costa', 'ana.costa@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '04534000', '1988-11-30'),
    (5, 'Carlos Ferreira', 'carlos.ferreira@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '05424000', '1995-07-08'), -- Membro Family
    (6, 'Lucia Rodrigues', 'lucia.rodrigues@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01239000', '1991-02-14'), -- Membro Family
    (7, 'Ricardo Almeida', 'ricardo.almeida@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '04014000', '1987-09-25'),
    (8, 'Fernanda Lima', 'fernanda.lima@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '05422000', '1993-12-03'),
    (9, 'Marcos Pereira', 'marcos.pereira@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01415000', '1989-06-18'),
    (10, 'Juliana Souza', 'juliana.souza@gmail.com', '2a10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01508000', '1994-04-12');


INSERT INTO user_phones (user_id, number, type) VALUES
    (1, '(11) 98765-4321', 'CELLPHONE'),
    (1, '(11) 3456-7890', 'LANDLINE'),
    (2, '(11) 91234-5678', 'CELLPHONE'),
    (3, '(11) 92345-6789', 'CELLPHONE'),
    (3, '(11) 4567-8901', 'BUSINESS'),
    (4, '(11) 93456-7890', 'CELLPHONE'),
    (5, '(11) 94567-8901', 'CELLPHONE'),
    (5, '(11) 5678-9012', 'LANDLINE'),
    (6, '(11) 95678-9012', 'CELLPHONE'),
    (7, '(11) 96789-0123', 'CELLPHONE'),
    (8, '(11) 97890-1234', 'CELLPHONE'),
    (8, '(11) 6789-0123', 'BUSINESS'),
    (9, '(11) 98901-2345', 'CELLPHONE'),
    (10, '(11) 99012-3456', 'CELLPHONE');


INSERT INTO user_phones (user_id, number, type) VALUES
    (9, '(11) 99999-5566', 'LANDLINE');


INSERT INTO user_phones (user_id, number, type) VALUES
    (10, '(11) 99999-8888', 'LANDLINE');


INSERT INTO signature (plan_id, is_active) VALUES
    (2, true),
    (3, true),
    (1, true),
    (2, true),
    (3, true),
    (4, true),
    (2, false),
    (3, true),
    (4, true),
    (2, true);


UPDATE "user" SET signature_id = 1 WHERE id = 1;
UPDATE "user" SET signature_id = 2 WHERE id = 2;
UPDATE "user" SET signature_id = 2 WHERE id = 3;
UPDATE "user" SET signature_id = 4 WHERE id = 4;
UPDATE "user" SET signature_id = 2 WHERE id = 5;
UPDATE "user" SET signature_id = 2 WHERE id = 6;
UPDATE "user" SET signature_id = 7 WHERE id = 7;
UPDATE "user" SET signature_id = 8 WHERE id = 8;
UPDATE "user" SET signature_id = 9 WHERE id = 9;
UPDATE "user" SET signature_id = 10 WHERE id = 10;


INSERT INTO plan (name, price, has_advertising, members_limit, offline_mode) VALUES
    ('Duo', 25.90, false, 2, false);


INSERT INTO credit_card (signature_id, cardholder_name, last_four_digits, gateway_token, expiration_date) VALUES
    (1,  'João Silva',       '1234', 'tok_gtw_8f2a1c9e0b1d4f6a', '2027-05-31'),
    (2,  'Maria Santos',     '5678', 'tok_gtw_3b7d2e4f9a0c1b8e', '2026-11-30'),
    (3,  'Pedro Oliveira',   '9012', 'tok_gtw_a1c3e5f7b9d0e2f4', '2025-03-31'),
    (4,  'Ana Costa',        '3456', 'tok_gtw_c9e1a3b5d7f9e0c2', '2028-01-31'),
    (5,  'Carlos Ferreira',  '7890', 'tok_gtw_e2f4a6c8b0d1e3f5', '2026-07-31'),
    (6,  'Lucia Rodrigues',  '2345', 'tok_gtw_f5e3d1c9b7a5e3d1', '2027-09-30'),
    (7,  'Ricardo Almeida',  '6789', 'tok_gtw_1a2b3c4d5e6f7g8h', '2024-12-31'),
    (8,  'Fernanda Lima',    '0123', 'tok_gtw_8h7g6f5e4d3c2b1a', '2029-02-28'),
    (9,  'Marcos Pereira',   '4567', 'tok_gtw_9i8h7g6f5e4d3c2b', '2026-06-30'),
    (10, 'Juliana Souza',    '8901', 'tok_gtw_2b3c4d5e6f7g8h9i', '2027-10-31');
