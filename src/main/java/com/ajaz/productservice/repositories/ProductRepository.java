package com.ajaz.productservice.repositories;

import com.ajaz.productservice.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

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

     Product findByTitleAndPrice_Price(String title, double price);


    List<Product> findAllByPrice_Currency(String currency);

    @Query(value = "select * from product join product_orders on product.id = product_orders.product_id where title = :title", nativeQuery = true)
//    @Query(value = "select Product from Product where Product.title = :title")
    Product findAllByTitle(String title);

    List<Product> findAllByCategory_Name(String category);
}
