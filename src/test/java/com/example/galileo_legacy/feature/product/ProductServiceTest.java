package com.example.galileo_legacy.feature.product;

import com.example.galileo_legacy.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @Before
    public void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    public void shouldListAllProducts() {
        Product product = Product.builder().id(1L).name("Laptop").build();
        when(repository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> result = service.listAll();

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void shouldCreateProductAndResetId() {
        Product product = Product.builder().id(10L).name("Keyboard").build();
        when(repository.save(product)).thenReturn(product);

        Product result = service.create(product);

        assertNull(product.getId());
        assertEquals(product, result);
        verify(repository).save(product);
    }

    @Test
    public void shouldUpdateExistingProduct() {
        Product existing = Product.builder()
                .id(1L)
                .name("Old")
                .description("Old desc")
                .price(new BigDecimal("10.00"))
                .build();
        Product input = Product.builder()
                .name("New")
                .description("New desc")
                .price(new BigDecimal("20.00"))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Product result = service.update(1L, input);

        assertEquals("New", existing.getName());
        assertEquals("New desc", existing.getDescription());
        assertEquals(new BigDecimal("20.00"), existing.getPrice());
        assertEquals(existing, result);
        verify(repository).save(existing);
    }

    @Test
    public void shouldThrowWhenProductDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        try {
            service.getById(99L);
            fail("Expected ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertEquals("Product not found with id 99", ex.getMessage());
        }
    }
}
