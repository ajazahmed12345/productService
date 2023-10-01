package com.ajaz.productservice.repositories;

import com.ajaz.productservice.models.Category;
import com.ajaz.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    <S extends Category> S save(S entity);


    Optional<Category> findByName(String name);

    @Override
    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAll();


    List<Category>  findAllByName(String categoryName);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);
}
