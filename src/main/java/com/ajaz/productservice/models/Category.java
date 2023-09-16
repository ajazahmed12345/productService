package com.ajaz.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseModel{

    @Column
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;


}
