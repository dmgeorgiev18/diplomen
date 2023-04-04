package com.project.motohotel.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private String firstName;

    private String lastName;

    private String motorId;

    private String phonenumber;

    private int year;

    private LocalDate startDate;

    private LocalDate finishDate;



    private String model;

    private String subscriptionType;

    private String addedBy;

}
