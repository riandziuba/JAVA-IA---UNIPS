package dev.rdziuba.javify.administrative.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signature_id", nullable = false)
    private Signature signature;

    @Column(name = "cardholder_name", length = 100)
    private String cardholderName;

    @Column(name = "last_four_digits", length = 4)
    private String lastFourDigits;

    @Column(name = "gateway_token")
    private String gatewayToken;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public boolean isExpired() {
        return expirationDate != null && expirationDate.isBefore(LocalDate.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getGatewayToken() {
        return gatewayToken;
    }

    public void setGatewayToken(String gatewayToken) {
        this.gatewayToken = gatewayToken;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCard{id=%d, cardholderName='%s', lastFourDigits=%s, expirationDate=%s}"
                .formatted(id, cardholderName, lastFourDigits, expirationDate);
    }
}
