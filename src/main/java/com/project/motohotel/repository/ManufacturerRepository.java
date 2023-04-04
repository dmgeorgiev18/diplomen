package com.project.motohotel.repository;

import com.project.motohotel.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {

    Manufacturer findByName(String name);
}
