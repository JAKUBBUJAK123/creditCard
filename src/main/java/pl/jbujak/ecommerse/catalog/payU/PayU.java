package pl.jbujak.ecommerse.catalog.payU;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

public class PayU {
    RestTemplate http;
    private final PayUCredentials payUCredentials;

    public PayU(RestTemplate http, PayUCredentials payUCredentials) {
        this.http = http;
        this.payUCredentials = payUCredentials;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type" , "application/json");
        headers.add("Authorization" , String.format("Bearer %s", getToken()));

        HttpEntity<OrderCreateRequest> request = new HttpEntity<>(orderCreateRequest, headers);

        ResponseEntity<OrderCreateResponse> orderCreateResponse = http.postForEntity(
                String.format("%s/api/v2_1/orders", payUCredentials.getBaseUrl()),
                request,
                OrderCreateResponse.class
        );
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
        HttpEntity<String> request = new HttpEntity<>(body,headers);
        ResponseEntity<AccessTokenResponse> atResponse = http.postForEntity(
                String.format("%s/pl/standard/user/oauth/authorize", payUCredentials.getBaseUrl()),
                request,
                AccessTokenResponse.class
        );
        return atResponse.getBody().getAccessToken();
    }
}