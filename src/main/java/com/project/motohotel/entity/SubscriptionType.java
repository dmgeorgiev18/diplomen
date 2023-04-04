package com.project.motohotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubscriptionType extends BaseEntity {

    public SubscriptionType(int days, double price) {
        this.days = days;
        this.price = price;
    }

    @Column
    private int days;

    @Column
    private double price;

    @Override
    public String toString() {
        return String.format("%d дни - %.2fлв.", days, price);
    }
}
