package com.ajaz.productservice.security;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Role{
    private Long id;
    private String name;
}
