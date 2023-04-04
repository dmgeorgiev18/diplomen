package com.project.motohotel.service;

import com.project.motohotel.entity.Model;
import com.project.motohotel.repository.ModelRepository;
import com.project.motohotel.repository.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    private ModelRepository repository;

    @Autowired
    public ModelServiceImpl(ModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Model> findAllMogels() {
        return this.repository.findAll();
    }

    @Override
    public Model findById(String id) {
        return repository.findById(id).get();
    }
}
