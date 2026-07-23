package com.example.galileo_legacy.feature.product;

import com.example.galileo_legacy.feature.product.dto.ProductRequest;
import com.example.galileo_legacy.feature.product.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    private ProductService service;
    private ProductMapper mapper;
    private ProductController controller;

    @BeforeEach
    public void setUp() {
        service = mock(ProductService.class);
        mapper = mock(ProductMapper.class);
        controller = new ProductController(service, mapper);
    }

    @Test
    public void shouldReturnAllProducts() {
        Product product = Product.builder().id(1L).name("Laptop").build();
        ProductResponse response = ProductResponse.builder().id(1L).name("Laptop").build();

        when(service.listAll()).thenReturn(Collections.singletonList(product));
        when(mapper.toResponse(product)).thenReturn(response);

        ResponseEntity<List<ProductResponse>> result = controller.getAllProducts();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.singletonList(response), result.getBody());
        verify(service).listAll();
        verify(mapper).toResponse(product);
    }

    @Test
    public void shouldCreateProduct() {
        ProductRequest request = ProductRequest.builder()
                .name("Mouse")
                .description("Wireless")
                .price(new BigDecimal("99.99"))
                .build();
        Product created = Product.builder()
                .id(2L)
                .name("Mouse")
                .description("Wireless")
                .price(new BigDecimal("99.99"))
                .build();
        ProductResponse response = ProductResponse.builder()
                .id(2L)
                .name("Mouse")
                .description("Wireless")
                .price(new BigDecimal("99.99"))
                .build();

        when(mapper.toEntity(request)).thenReturn(created);
        when(service.create(created)).thenReturn(created);
        when(mapper.toResponse(created)).thenReturn(response);

        ResponseEntity<ProductResponse> result = controller.createProduct(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(service).create(created);
    }
}
