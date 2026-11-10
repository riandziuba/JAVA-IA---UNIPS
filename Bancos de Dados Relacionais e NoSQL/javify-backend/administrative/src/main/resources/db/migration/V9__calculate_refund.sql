-- V9__calculate_refund.sql
CREATE OR REPLACE FUNCTION calculate_refund(p_signature_id INTEGER)
RETURNS NUMERIC(10,2)
LANGUAGE plpgsql
AS $$
DECLARE
   v_price             NUMERIC(10,2);
   v_next_billing_date DATE;
   v_remaining_days    INTEGER;
   v_cycle_days        INTEGER := 30;
   v_refund_amount     NUMERIC(10,2);
BEGIN
   -- Buscar dados da assinatura com lock pessimista
   SELECT p.price, a.next_billing_date
     INTO v_price, v_next_billing_date
     FROM signature a
     JOIN plan p ON p.id = a.plan_id
    WHERE a.id = p_signature_id
      AND a.is_active = TRUE
      FOR UPDATE;


   IF NOT FOUND THEN
       RAISE EXCEPTION
           'Assinatura % nao encontrada ou ja cancelada',
           p_signature_id
           USING ERRCODE = 'P0001';
   END IF;


   -- Calcular dias restantes no ciclo atual
   v_remaining_days := GREATEST(0, v_next_billing_date - CURRENT_DATE);


   -- Proporcao: dias restantes / 30 * preco
   v_refund_amount := ROUND(
       v_price * v_remaining_days::NUMERIC / v_cycle_days,
       2
   );


   RETURN v_refund_amount;
END;
$$;
