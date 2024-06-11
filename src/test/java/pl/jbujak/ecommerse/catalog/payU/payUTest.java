package pl.jbujak.ecommerse.catalog.payU;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;


@SpringBootTest
public class payUTest {
    @Test
    void creatingNewPayment(){
        PayU payu = thereIsPayU();
        OrderCreateRequest orderCreateRequest = createExampleOrderRequest();

        OrderCreateResponse response = payu.handle(orderCreateRequest);

        assertNotNull(response.getRedirectId());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest createExampleOrderRequest() {
        var createRequest = new OrderCreateRequest();
        createRequest.setNotifyUrl("https://my.example.shop.wbub.pl/api/order");
        createRequest.setCustomerIp("127.0.0.1");
        createRequest.setMerchantPostIp("300746");
        createRequest.setDescription("My ebook");
        createRequest.setCurrencyCode("PLN");
        createRequest.setTotalAmount(21000);
        createRequest.setExtraOrderId(UUID.randomUUID().toString());
        Buyer buyer = new Buyer();
        buyer.setFirstName("John");
        buyer.setLastName("Doe");
        buyer.setEmail("john.doe@example.com");
        buyer.setLanguage("pl");
        createRequest.setBuyer(buyer);
        ProductU product = new ProductU();
        product.setName("Product X");
        product.setQuantity(1);
        product.setUnitPrice(210000);
        createRequest.setProducts(Arrays.asList(product));
        return createRequest;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }
}
