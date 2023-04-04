package com.project.motohotel.repository;

import com.project.motohotel.binding.SubscriptionEdit;
import com.project.motohotel.binding.SubscriptionRegistration;
import com.project.motohotel.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubscriptions();

    void createSubscription(SubscriptionRegistration registration);

    void deleteById(String id);

    List<Subscription> findAllBySearch(String firstName,
                                       String lastName,
                                       String motorId,
                                       String phoneNumber,
                                       String sortType);

    List<Subscription> findAll(String sortType);

    Subscription findById(String id);

    void editSubscription(SubscriptionEdit subscription, String id);
}
