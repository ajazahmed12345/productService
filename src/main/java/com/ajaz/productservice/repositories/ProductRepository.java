package com.ajaz.productservice.repositories;

import com.ajaz.productservice.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
//    @Override
//    <S extends GenericProductDto> S save(S entity);
//
//    Optional<GenericProductDto> findById(Long id);
//
//    @Override
//    List<GenericProductDto> findAll();
//
//    @Override
//    void deleteById(Long id);


    @Override
    Product save(Product entity);

    @Override
    Optional<Product> findById(UUID id);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(UUID id);
}
