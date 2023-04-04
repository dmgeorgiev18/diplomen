package com.project.motohotel.service;

import com.project.motohotel.entity.SubscriptionType;
import com.project.motohotel.repository.SubscriptionTypeRepository;
import com.project.motohotel.repository.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    private SubscriptionTypeRepository repository;

    @Autowired
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubscriptionType> findAll() {
        return this.repository.findAll();
    }

    @Override
    public SubscriptionType findById(String id){
        return repository.findById(id).get();
    }
}
