package pl.jbujak.ecommerse.catalog.payU;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import pl.jbujak.ecommerse.catalog.Product;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;

import java.util.Arrays;
import java.util.UUID;

public class PayU implements PaymentGateway {

    RestTemplate http;
    private final PayUCredentials payUCredentials;

    public PayU(RestTemplate http, PayUCredentials payUCredentials) {
        this.http = http;
        this.payUCredentials = payUCredentials;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {
        //Authorize
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));

        HttpEntity<OrderCreateRequest> request = new HttpEntity<>(orderCreateRequest, headers);

        ResponseEntity<OrderCreateResponse> orderCreateResponse = http.postForEntity(
                String.format("%s/api/v2_1/orders", payUCredentials.getBaseUrl()),
                request,
                OrderCreateResponse.class
        );
        //Create order
        return orderCreateResponse.getBody();
    }

    private String getToken() {
        String body = String.format(
                "grant_type=client_credentials&client_id=%s&client_secret=%s",
                payUCredentials.getClientId(),
                payUCredentials.getClientSecret()
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<AccessTokenResponse> atResponse = http.postForEntity(
                String.format("%s/pl/standard/user/oauth/authorize", payUCredentials.getBaseUrl()),
                request,
                AccessTokenResponse.class
        );

        return atResponse.getBody().getAccessToken();
    }


    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest of) {
        var request = new OrderCreateRequest();
        var buyer = new Buyer();
        var Product = new ProductU();
        Product.setName("Product X");
        Product.setQuantity(1);
        Product.setUnitPrice(210000);
        buyer.setEmail(of.getEmail());
        buyer.setFirstName(of.getFirstname());
        buyer.setLastName(of.getLastname());
        buyer.setLanguage("pl");
        buyer.setProducts(Arrays.asList(Product));




        request
                .setNotifyUrl("https://my.example.shop.wbub.pl/api/order");
                request.setCustomerIp("127.0.0.1");
                request.setMerchantPostIp("300746");
                request.setDescription("My ebook");
                request.setCurrencyCode("PLN");
                request.setTotalAmount(of.getTotalAsPennies());
                request.setExtraOrderId(UUID.randomUUID().toString());
                request.setBuyer((buyer));

        OrderCreateResponse response = this.handle(request);
        return new PaymentDetails(response.getOrderId(), response.getOrderId());
    }
}