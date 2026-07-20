package com.example.galileo_legacy.feature.product;

import com.example.galileo_legacy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product create(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existing = getById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Product existing = getById(id);
        repository.delete(existing);
    }
}
