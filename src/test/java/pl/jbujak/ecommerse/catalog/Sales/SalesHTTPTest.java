package pl.jbujak.ecommerse.catalog.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.jbujak.ecommerse.catalog.ProductCatalog;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.jbujak.ecommerse.catalog.Sales.offering.AcceptOfferRequest;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void itAcceptOfferHappyPath() {
        var productId = thereIsExampleProduct("Example product" , BigDecimal.valueOf(10));
        var uri = String.format("api/add-to-cart/%s" , productId);
        var addProductToCartUrl = String.format("http://localhost:%s/%s", port, uri);
        http.postForEntity(addProductToCartUrl,null,Object.class);

        AcceptOfferRequest acceptOfferReques = new AcceptOfferRequest();
        acceptOfferReques
                .setFirstname("Jakub")
                .setLastname("Bujak")
                .setEmail("jakbuj3@gmail.com");
        var acceptOfferUrl = String.format("http://localhost:%s/%s" , port, "api/accept-offer");
        ResponseEntity<ReservationDetails> reservationResponse = http.postForEntity(acceptOfferUrl, acceptOfferReques, ReservationDetails.class);

        assertEquals(HttpStatus.OK, reservationResponse.getStatusCode());
        assertEquals(BigDecimal.valueOf(10), reservationResponse.getBody().getTotal());
        assertNotNull(reservationResponse.getBody().getReservationId());
        assertNotNull(reservationResponse.getBody().getPayment());

    }

    private Object thereIsExampleProduct(String name, BigDecimal price) {
    var prodId = catalog.addProduct(name, name , BigDecimal.valueOf(0));
    catalog.changePrice(prodId,price);
    return prodId;
    }

}
