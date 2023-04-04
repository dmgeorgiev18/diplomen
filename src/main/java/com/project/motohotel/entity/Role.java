package com.project.motohotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Table
@Entity
@Data
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    public Role(String name) {
        this.name = name;
    }



    @Column
    private String name;

    //vrushtame imeto na rolqta
    @Override
    public String getAuthority() {
        return name;
    }
}
