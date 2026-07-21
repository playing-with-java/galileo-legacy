package com.example.galileo_legacy.feature.root;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String body = "<!DOCTYPE html>"
                + "<html lang=\"es\">"
                + "<head><meta charset=\"UTF-8\"><title>Galileo Legacy</title></head>"
                + "<body>"
                + "<h1>Galileo Legacy API</h1>"
                + "<p>La aplicación está activa.</p>"
                + "<p>Prueba los endpoints en <code>/api/products</code> o <code>/api/users</code>.</p>"
                + "</body></html>";

        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body(body);
    }
}
