package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    public void add(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        Iterable<Product> products = productRepository.findAll();
        Iterator<Product> iter = products.iterator();
        List<Product> copy = new ArrayList<Product>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Long count() {
        return productRepository.count();
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getFilteredProducts(String title, String priceFrom, String priceTo) {

        if (title != null && !title.isEmpty())
            return new ArrayList<>(productRepository.findByTitle(title));

        if (
                (priceFrom != null && !priceFrom.isEmpty()) && (priceTo == null || priceTo.isEmpty())
        )
            return new ArrayList<>(productRepository.findByPriceFrom(Integer.parseInt(priceFrom)));
        else if (
                (priceFrom == null || priceFrom.isEmpty()) && (priceTo != null && !priceTo.isEmpty())
        ) {
            return new ArrayList<>(productRepository.findByPriceTo(Integer.parseInt(priceTo)));
        } else if (
                (priceFrom != null && !priceFrom.isEmpty()) && (priceTo != null && !priceTo.isEmpty())
        ) {
            return new ArrayList<>(productRepository.findByPriceBoth(Integer.parseInt(priceFrom), Integer.parseInt(priceTo)));
        }
        return this.getAll();
    }

    public void change(Product product) {
        productRepository.save(product);
    }

}
