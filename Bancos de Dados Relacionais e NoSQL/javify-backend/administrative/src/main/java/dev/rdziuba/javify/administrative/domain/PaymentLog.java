package dev.rdziuba.javify.administrative.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_log")
public class PaymentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "signature_id", nullable = false)
    private Integer signatureId;

    @Column(nullable = false)
    private String reason;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(Integer signatureId) {
        this.signatureId = signatureId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }
}
