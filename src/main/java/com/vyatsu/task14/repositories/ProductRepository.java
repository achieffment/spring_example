package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    @Query(
            value = "SELECT * FROM products p WHERE p.title LIKE %?1%",
            nativeQuery = true
    )
    Collection<Product> findByTitle(String title);

    @Query(
            value = "SELECT * FROM products p WHERE p.price > ?1",
            nativeQuery = true
    )
    Collection<Product> findByPriceFrom(int priceFrom);

    @Query(
            value = "SELECT * FROM products p WHERE p.price < ?1",
            nativeQuery = true
    )
    Collection<Product> findByPriceTo(int priceTo);

    @Query(
            value = "SELECT * FROM products p WHERE p.price BETWEEN :start AND :end",
            nativeQuery = true
    )
    Collection<Product> findByPriceBoth(@Param("start") int start, @Param("end") int end);

}