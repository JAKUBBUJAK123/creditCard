package pl.jbujak.ecommerse.catalog.payU;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;

import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayU implements PaymentGateway {
    private static final Logger LOGGER = Logger.getLogger(PayU.class.getName());

    RestTemplate http;
    private final PayUCredentials payUCredentials;

    public PayU(RestTemplate http, PayUCredentials payUCredentials) {
        this.http = http;
        this.payUCredentials = payUCredentials;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {
        try {
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

            return orderCreateResponse.getBody();
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.SEVERE, "HTTP error occurred: " + e.getStatusCode());
            LOGGER.log(Level.SEVERE, "Response body: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred: ", e);
            throw e;
        }
    }

    private String getToken() {
        try {
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

            LOGGER.log(Level.INFO, "Token retrieved successfully: " + atResponse.getBody().getAccessToken());
            return atResponse.getBody().getAccessToken();
        } catch (HttpClientErrorException e) {
            LOGGER.log(Level.SEVERE, "HTTP error occurred while retrieving token: " + e.getStatusCode());
            LOGGER.log(Level.SEVERE, "Response body: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while retrieving token: ", e);
            throw e;
        }
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        var request = new OrderCreateRequest();
        request.setNotifyUrl("https://my.example.shop.jbuj.pl/api/order");
        request.setCustomerIp("127.0.0.1");
        request.setMerchantPosId("300746");
        request.setDescription("My ebook");
        request.setCurrencyCode("PLN");
        request.setTotalAmount(registerPaymentRequest.getTotalAsPennies());
        request.setExtOrderId(UUID.randomUUID().toString());
        var buyer = new Buyer();
        var product = new ProductU();
        product.setName("Product X");
        product.setQuantity(1);
        product.setUnitPrice(210000);
        buyer.setEmail(registerPaymentRequest.getEmail());
        buyer.setFirstName(registerPaymentRequest.getFirstname());
        buyer.setLastName(registerPaymentRequest.getLastname());
        buyer.setLanguage("PL");
        request.setBuyer(buyer);
        request.setProducts(Arrays.asList(product));

        OrderCreateResponse response = this.handle(request);
        return new PaymentDetails(response.getRedirectUri(), response.getOrderId());
    }
}
