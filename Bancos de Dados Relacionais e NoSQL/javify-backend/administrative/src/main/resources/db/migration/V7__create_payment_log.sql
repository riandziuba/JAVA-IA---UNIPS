CREATE TABLE payment_log (
    id           SERIAL PRIMARY KEY,
    signature_id INT          NOT NULL,
    reason       VARCHAR(255) NOT NULL,
    occurred_at  TIMESTAMP    NOT NULL,

    CONSTRAINT fk_payment_log_signature
        FOREIGN KEY (signature_id)
            REFERENCES signature(id)
);
