package com.ajaz.productservice.dtos;

import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Price;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private UUID id;
    private String title;
    private String description;
    private String image;

    private CategoryDto category;

    private Price price;
}
