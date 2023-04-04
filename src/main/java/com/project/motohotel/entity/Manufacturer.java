package com.project.motohotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Manufacturer extends BaseEntity {

    public Manufacturer(String name) {
        this.name = name;

    }

    @Column
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Model> models;


    @Override
    public String toString() {
        return String.format("%s", name);
    }


}
