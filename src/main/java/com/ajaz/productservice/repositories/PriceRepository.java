package com.ajaz.productservice.repositories;

import com.ajaz.productservice.models.Price;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    @Override
    <S extends Price> S save(S entity);


    Optional<Price> findByPrice(double price);
}
