CREATE OR REPLACE VIEW v_mrr_by_plan AS
SELECT
    p.name                          AS plan,
    p.price                         AS unit_price,
    COUNT(s.id)                     AS active_subscriptions,
    SUM(p.price)                    AS plan_mrr,
    ROUND(AVG(p.price), 2)          AS average_ticket
FROM signature s
INNER JOIN plan p ON p.id = s.plan_id
WHERE s.is_active = TRUE
GROUP BY p.name, p.price
ORDER BY plan_mrr DESC;

-- Uso: identico a consultar uma tabela
SELECT * FROM v_mrr_by_plan;
