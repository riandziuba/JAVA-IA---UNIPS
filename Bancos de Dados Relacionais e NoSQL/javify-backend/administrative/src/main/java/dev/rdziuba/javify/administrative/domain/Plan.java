package dev.rdziuba.javify.administrative.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "has_advertising", nullable = false)
    private boolean hasAdvertising;

    @Column(name = "members_limit", nullable = false)
    private int membersLimit;

    @Column(name = "offline_mode", nullable = false)
    private boolean offlineMode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHasAdvertising() {
        return hasAdvertising;
    }

    public void setHasAdvertising(boolean hasAdvertising) {
        this.hasAdvertising = hasAdvertising;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

    public boolean isOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(boolean offlineMode) {
        this.offlineMode = offlineMode;
    }

    @Override
    public String toString() {
        return "Plan{id=%d, name='%s', price=%s}".formatted(id, name, price);
    }
}
