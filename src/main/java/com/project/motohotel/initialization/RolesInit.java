package com.project.motohotel.initialization;

import com.project.motohotel.entity.Role;
import com.project.motohotel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RolesInit implements CommandLineRunner {

    private RoleRepository repository;


    @Autowired
    public RolesInit(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            repository.save(adminRole);
            repository.save(userRole);
        }
    }
}
