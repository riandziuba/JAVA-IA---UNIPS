CREATE OR REPLACE FUNCTION fn_validate_member_limit()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
   v_limit     INTEGER;
   v_current   INTEGER;
BEGIN
   -- Buscar limite do plano da assinatura destino
   SELECT p.members_limit INTO v_limit
     FROM signature a
     JOIN plan p ON p.id = a.plan_id
    WHERE a.id = NEW.signature_id;


   -- Contar membros atuais
   SELECT COUNT(*) INTO v_current
     FROM "user"
    WHERE signature_id = NEW.signature_id;


   IF v_current >= v_limit THEN
       RAISE EXCEPTION
           'Limite de % membros atingido para esta assinatura',
           v_limit
           USING ERRCODE = 'P0002';
   END IF;


   RETURN NEW;  -- permite a operacao continuar
END;
$$;


CREATE TRIGGER trg_validate_member_limit
   BEFORE INSERT ON "user"
   FOR EACH ROW
   WHEN (NEW.signature_id IS NOT NULL AND NEW.is_holder = FALSE)
   EXECUTE FUNCTION fn_validate_member_limit();
