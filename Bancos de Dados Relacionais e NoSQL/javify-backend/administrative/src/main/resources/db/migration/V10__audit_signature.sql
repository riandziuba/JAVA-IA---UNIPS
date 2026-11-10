CREATE TABLE signature_audit (
   id              BIGSERIAL       PRIMARY KEY,
   signature_id    INTEGER         NOT NULL,
   previous_plan   VARCHAR(50),
   new_plan        VARCHAR(50),
   previous_price  NUMERIC(10,2),
   new_price       NUMERIC(10,2),
   previous_active BOOLEAN,
   new_active      BOOLEAN,
   changed_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
   source          TEXT            NOT NULL DEFAULT current_user
);


-- Função de trigger: retorno TRIGGER, sem parâmetros
CREATE OR REPLACE FUNCTION fn_audit_signature()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
   -- Só audita se houve mudança real (evita registro de no-op)
   IF OLD.plan_id IS DISTINCT FROM NEW.plan_id
   OR OLD.is_active IS DISTINCT FROM NEW.is_active THEN


       INSERT INTO signature_audit (
           signature_id,
           previous_plan, new_plan,
           previous_price, new_price,
           previous_active, new_active
       )
       SELECT
           NEW.id,
           p_old.name,    p_new.name,
           p_old.price,   p_new.price,
           OLD.is_active, NEW.is_active
       FROM plan p_old, plan p_new
       WHERE p_old.id = OLD.plan_id
         AND p_new.id = NEW.plan_id;


   END IF;
   RETURN NEW;
END;
$$;


-- Criar o trigger na tabela assinatura
CREATE TRIGGER trg_audit_signature
   AFTER UPDATE ON signature
   FOR EACH ROW
   EXECUTE FUNCTION fn_audit_signature();
