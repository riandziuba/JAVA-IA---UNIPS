CREATE MATERIALIZED VIEW mv_mrr_by_plan AS
SELECT
    p.name                          AS plan,
    p.price                         AS unit_price,
    COUNT(s.id)                     AS active_subscriptions,
    SUM(p.price)                    AS plan_mrr,
    ROUND(AVG(p.price), 2)          AS average_ticket,
    NOW()                           AS updated_at
FROM signature s
INNER JOIN plan p ON p.id = s.plan_id
WHERE s.is_active = TRUE
GROUP BY p.name, p.price
ORDER BY plan_mrr DESC
WITH DATA;  -- executa imediatamente e popula o snapshot

-- Índice necessário para REFRESH CONCURRENTLY
CREATE UNIQUE INDEX idx_mv_mrr_by_plan ON mv_mrr_by_plan (plan);

-- Refresh sem lock de leitura (requer o índice único acima)
REFRESH MATERIALIZED VIEW CONCURRENTLY mv_mrr_by_plan;
