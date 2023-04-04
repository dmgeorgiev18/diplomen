package com.project.motohotel.binding;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionEdit {

    private String id;

    @NotEmpty(message = "Не може да бъде празно")
    private String firstName;

    @NotEmpty(message = "Не може да бъде празно")
    private String lastName;

    @NotEmpty(message = "Не може да бъде празно")
    private String motorId;

    @Pattern(regexp = "([0]|\\+359)[0-9]{2}[0-9]{7}", message = "Трябва да бъде валиден български номер.")
    private String phoneNumber;


    @Positive(message = "Трябва да бъде положително число")
    private int year;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Не може да бъде в миналото")
    private LocalDate startDate;

    private String modelId;

    private String subscriptionTypeId;


}
