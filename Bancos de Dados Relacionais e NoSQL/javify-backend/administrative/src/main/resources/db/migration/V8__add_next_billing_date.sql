ALTER TABLE signature
   ADD COLUMN next_billing_date DATE;


-- Popular as assinaturas ativas com data futura para demo
UPDATE signature
  SET next_billing_date = CURRENT_DATE + INTERVAL '15 days'
WHERE is_active = TRUE;
