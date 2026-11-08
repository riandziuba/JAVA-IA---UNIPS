ALTER TABLE plan
   ADD CONSTRAINT chk_plan_price_non_negative
       CHECK (price >= 0);
