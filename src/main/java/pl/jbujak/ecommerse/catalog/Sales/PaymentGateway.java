package pl.jbujak.ecommerse.catalog.Sales;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest of);
}
