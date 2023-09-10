package com.ajaz.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String desc;
    private String image;
    private Category category;
    private double price;
}
