package com.project.motohotel.initialization;

import com.project.motohotel.entity.Manufacturer;
import com.project.motohotel.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ManufacturerInit implements CommandLineRunner {
    private ManufacturerRepository repository;

    @Autowired
    public ManufacturerInit(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(new Manufacturer("BMW"));
            repository.save(new Manufacturer("Yamaha"));
            repository.save(new Manufacturer("Kawasaki"));
            repository.save(new Manufacturer("Peugeot"));
            repository.save(new Manufacturer("KTM"));
        }

    }
}
