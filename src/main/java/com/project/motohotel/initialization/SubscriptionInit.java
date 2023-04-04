package com.project.motohotel.initialization;

import com.project.motohotel.entity.Subscription;
import com.project.motohotel.entity.SubscriptionType;
import com.project.motohotel.entity.User;
import com.project.motohotel.repository.ModelRepository;
import com.project.motohotel.repository.SubscriptionRepository;
import com.project.motohotel.repository.SubscriptionTypeRepository;
import com.project.motohotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(6)
public class SubscriptionInit implements CommandLineRunner {
    private SubscriptionRepository repository;
    private UserRepository userRepository;
    private ModelRepository modelRepository;

    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    public SubscriptionInit(SubscriptionRepository repository, UserRepository userRepository, ModelRepository modelRepository, SubscriptionTypeRepository subscriptionTypeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            User admin = userRepository.findByUsername("Admin");
            SubscriptionType byDays = subscriptionTypeRepository.findByDays(365);
            Subscription subscription = new Subscription("Ivan","Stoqnov", "PB0039TT", "0882020431",
                    2020, LocalDate.of(2023,3,2),LocalDate.of(2024,3,2),
                    modelRepository.findByModel("Enduro"),byDays,admin);
            SubscriptionType second = subscriptionTypeRepository.findByDays(90);
            Subscription subscriptionTwo = new Subscription("Nikolay","Enchev", "PB6161TT", "0896719561",
                    2022, LocalDate.of(2023,1,5),LocalDate.of(2024,4,5),
                    modelRepository.findByModel("Enduro"),second,admin);

            repository.save(subscription);
            repository.save(subscriptionTwo);
        }
    }
}
