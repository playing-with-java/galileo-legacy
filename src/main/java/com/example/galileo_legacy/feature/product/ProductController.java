package com.example.galileo_legacy.feature.product;

import com.example.galileo_legacy.feature.product.dto.ProductRequest;
import com.example.galileo_legacy.feature.product.dto.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Products", description = "Operations for managing products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "List all products",
            notes = "Returns all available products",
            response = ProductResponse.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Products returned successfully")
    })
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = service.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product by id",
            notes = "Finds a product by its identifier",
            response = ProductResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<ProductResponse> getProduct(@ApiParam(value = "Product identifier", required = true)
                                                            @PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }

    @PostMapping
    @ApiOperation(value = "Create a product",
            notes = "Creates a new product",
            response = ProductResponse.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product created successfully")
    })
    public ResponseEntity<ProductResponse> createProduct(@ApiParam(value = "Product payload", required = true)
                                                            @RequestBody ProductRequest request) {
        Product created = service.create(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a product",
            notes = "Updates an existing product",
            response = ProductResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated successfully"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<ProductResponse> updateProduct(@ApiParam(value = "Product identifier", required = true)
                                                            @PathVariable Long id,
                                                            @ApiParam(value = "Product payload", required = true)
                                                            @RequestBody ProductRequest request) {
        Product updated = service.update(id, mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product", notes = "Removes a product by its identifier")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product deleted successfully"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product identifier", required = true)
                                               @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
