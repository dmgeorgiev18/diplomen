package com.project.motohotel.initialization;

import com.project.motohotel.entity.User;
import com.project.motohotel.entity.Role;
import com.project.motohotel.repository.RoleRepository;
import com.project.motohotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class UserInit implements CommandLineRunner {

    private UserRepository repository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserInit(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = repository.findAll();
        if(users.size() == 0){
            List<Role> all = roleRepository.findAll();
            User admin = new User("Admin",passwordEncoder.encode("1234"),all);
            repository.save(admin);
        }
    }
}
