package com.project.motohotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subscription extends BaseEntity {

    public Subscription(String firstName, String lastName, String motorId, String phonenumber, int year, LocalDate startDate, LocalDate finishDate, Model model, SubscriptionType subscriptionType, User addedBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.motorId = motorId;
        this.phoneNumber = phonenumber;
        this.year = year;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.model = model;
        this.subscriptionType = subscriptionType;
        this.addedBy = addedBy;
    }

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String motorId;
    @Column
    private String phoneNumber;

    @Column
    private int year;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate finishDate;


    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id", referencedColumnName = "id")
    private SubscriptionType subscriptionType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User addedBy;


}
