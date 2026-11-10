SELECT setval('user_id_seq', (SELECT COALESCE(MAX(id), 1) FROM "user"), true);
