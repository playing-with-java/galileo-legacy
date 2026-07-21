package com.example.galileo_legacy.config;

import org.junit.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertNotNull;

public class SwaggerConfigTest {

    @Test
    public void shouldCreateDocketWithApiConfiguration() {
        SwaggerConfig config = new SwaggerConfig();
        Docket docket = config.api();

        assertNotNull(docket);
        assertNotNull(docket.getDocumentationType());
    }
}
