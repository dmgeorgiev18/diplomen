package com.project.motohotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Model extends BaseEntity{

    public Model(String model, Manufacturer manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Column
    private String model;

    @ManyToOne
    @JoinColumn(name = "mamufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;



    @Override
    public String toString() {
        return String.format("%s", model);
    }
}
