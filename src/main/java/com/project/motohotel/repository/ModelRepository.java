package com.project.motohotel.repository;

import com.project.motohotel.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    Model findByModel(String name);
}
