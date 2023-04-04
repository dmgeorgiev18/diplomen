package com.project.motohotel.repository;
import com.project.motohotel.entity.SubscriptionType;

import java.util.List;
public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();
    SubscriptionType findById(String id);
}
