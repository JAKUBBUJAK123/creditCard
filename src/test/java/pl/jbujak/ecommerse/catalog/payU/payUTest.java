package pl.jbujak.ecommerse.catalog.payU;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pl.jbujak.ecommerse.catalog.ProductCatalog;

import java.util.Arrays;
import java.util.UUID;



public class payUTest {
    @Test
    void itRegisterNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getOrderId());
        assertNotNull(response.getRedirectUri());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var request = new OrderCreateRequest();
        ProductU product = new ProductU();
        product.setName("Nice product");
        product.setUnitPrice(210000);
        product.setQuantity(1);
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My digital product")
                .setCurrencyCode("PLN")
                .setTotalAmount(15500)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("kuba.doe@example.com")
                        .setFirstName("john")
                        .setLastName("doe")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(product
                ));

        return request;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                ));
    }
}
