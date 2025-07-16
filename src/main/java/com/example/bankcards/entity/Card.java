package com.example.bankcards.entity;

import com.example.bankcards.entity.enums.StatusCard;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "card_number", unique = true)
    private String cardNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Column(nullable = false, name = "validity_period")
    private LocalDate validityPeriod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_card")
    private StatusCard statusCard;
    @Column(nullable = false, name = "balance")
    private BigDecimal balance;

}
