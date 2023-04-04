package com.project.motohotel.service;

import com.project.motohotel.entity.Role;
import com.project.motohotel.repository.RoleRepository;
import com.project.motohotel.repository.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAllRoles() {
        return repository.findAll();
    }
}
