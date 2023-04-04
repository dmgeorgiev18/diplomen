package com.project.motohotel.repository;

import com.project.motohotel.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, String> {

    SubscriptionType findByDays(int days);
}
