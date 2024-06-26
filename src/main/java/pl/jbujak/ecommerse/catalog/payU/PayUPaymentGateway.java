package pl.jbujak.ecommerse.catalog.payU;

import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentDetails;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.Sales.Payment.RegisterPaymentRequest;

import java.util.Arrays;
import java.util.UUID;

public class PayUPaymentGateway implements PaymentGateway {
    PayU payU;

    public PayUPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        var createRequest = new OrderCreateRequest();
        createRequest.setNotifyUrl("https://my.example.shop.wbub.pl/api/order");
        createRequest.setCustomerIp("127.0.0.1");
        createRequest.setMerchantPosId("300746");
        createRequest.setDescription("My ebook");
        createRequest.setCurrencyCode("PLN");
        createRequest.setTotalAmount(21000);
        createRequest.setExtOrderId(UUID.randomUUID().toString());
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
        return null;
    }
}
