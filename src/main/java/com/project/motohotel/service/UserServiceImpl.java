package com.project.motohotel.service;

import com.project.motohotel.binding.UserAdd;
import com.project.motohotel.entity.User;
import com.project.motohotel.repository.RoleRepository;
import com.project.motohotel.repository.UserRepository;
import com.project.motohotel.repository.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository repository;

    private ModelMapper mapper;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void addUser(UserAdd user) {
        User entity = mapper.map(user, User.class);
        setAdditionalDetails(user, entity);
        repository.save(entity);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public void editUser(String username, String password, String id) {
        User byId = repository.findById(id).get();
        byId.setUsername(username);
        byId.setPassword(passwordEncoder.encode(password));
        repository.save(byId);
    }

    private void setAdditionalDetails(UserAdd user, User entity) {
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().equals("ADMIN")) {
            entity.setRoles(roleRepository.findAll());
        } else {
            entity.setRoles(List.of(roleRepository.findByName("USER")));
        }
    }
}
