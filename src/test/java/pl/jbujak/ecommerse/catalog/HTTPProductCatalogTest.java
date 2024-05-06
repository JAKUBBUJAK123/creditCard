package pl.jbujak.ecommerse.catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class HTTPProductCatalogTest {

    @Autowired
    ProductCatalog catalog;

    @LocalServerPort
    private int localPort;

    @Autowired
    TestRestTemplate http;

    @Test
    void itLoadsProducts(){
        var url = String.format("http://localhost:%s/%s",
                localPort,
                "api/products"
        );

        catalog.setUpDatabase();
        catalog.addProduct("Example product", "name" , BigDecimal.valueOf(10));

        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .hasSize(1)
                .extracting("description")
                .contains("name");
    }

    @Test
    void itLoadsHomePage(){
        var url = String.format("http://localhost:%s/%s",
                localPort,
                ""
        );
        ResponseEntity<String> response = http.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Welcome");
    }

}
