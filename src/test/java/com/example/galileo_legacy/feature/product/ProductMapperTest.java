package com.example.galileo_legacy.feature.product;

import com.example.galileo_legacy.feature.product.dto.ProductRequest;
import com.example.galileo_legacy.feature.product.dto.ProductResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductMapperTest {

    private final ProductMapper mapper = new ProductMapper();

    @Test
    public void shouldMapProductToResponse() {
        Product product = Product.builder()
                .id(1L)
                .name("Laptop")
                .description("Gaming")
                .price(new BigDecimal("1500.00"))
                .build();

        ProductResponse response = mapper.toResponse(product);

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getPrice(), response.getPrice());
    }

    @Test
    public void shouldMapRequestToEntity() {
        ProductRequest request = ProductRequest.builder()
                .name("Mouse")
                .description("Wireless")
                .price(new BigDecimal("99.99"))
                .build();

        Product entity = mapper.toEntity(request);

        assertNotNull(entity);
        assertEquals(request.getName(), entity.getName());
        assertEquals(request.getDescription(), entity.getDescription());
        assertEquals(request.getPrice(), entity.getPrice());
    }

    @Test
    public void shouldReturnNullWhenProductIsNull() {
        assertNull(mapper.toResponse(null));
    }
}
