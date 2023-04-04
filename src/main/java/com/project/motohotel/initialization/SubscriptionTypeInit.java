package com.project.motohotel.initialization;

import com.project.motohotel.entity.SubscriptionType;
import com.project.motohotel.repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class SubscriptionTypeInit implements CommandLineRunner {

    private SubscriptionTypeRepository repository;

    @Autowired
    public SubscriptionTypeInit(SubscriptionTypeRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            SubscriptionType month = new SubscriptionType(30, 60);
            SubscriptionType three = new SubscriptionType(90, 150);
            SubscriptionType six = new SubscriptionType(180,270 );
            SubscriptionType year = new SubscriptionType(365, 480);
            repository.save(month);
            repository.save(three);
            repository.save(six);
            repository.save(year);

        }
    }
}
