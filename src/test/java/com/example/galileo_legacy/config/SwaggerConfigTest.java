package com.example.galileo_legacy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwaggerConfigTest {

    @Test
    public void shouldCreateOpenAPIConfiguration() {
        SwaggerConfig config = new SwaggerConfig();
        OpenAPI openAPI = config.openAPI();

        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
    }

    @Test
    public void shouldHaveCorrectApiTitle() {
        SwaggerConfig config = new SwaggerConfig();
        Info info = config.openAPI().getInfo();

        assertNotNull(info);
        assertNotNull(info.getTitle());
    }
}

