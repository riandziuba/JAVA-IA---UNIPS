package dev.rdziuba.javify.administrative.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "signature")
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "signature",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<CreditCard> creditCards = new ArrayList<>();

    @Column(name = "next_billing_date")
    private LocalDate nextBillingDate;

    @Version
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public LocalDate getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(LocalDate nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Signature{id=%d, active=%s}".formatted(id, active);
    }
}
