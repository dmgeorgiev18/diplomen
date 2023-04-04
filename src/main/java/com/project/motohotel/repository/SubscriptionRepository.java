package com.project.motohotel.repository;

import com.project.motohotel.entity.Subscription;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {


    List<Subscription> findAllByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCaseOrMotorIdStartingWithIgnoreCaseOrPhoneNumberStartingWithIgnoreCase
            (String firstName, String lastName, String motorId,
             String phoneNumber, Sort sort);

}
