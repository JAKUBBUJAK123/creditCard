package pl.jbujak.ecommerse.catalog.Sales;

import pl.jbujak.ecommerse.catalog.ArrayListProductStorage;
import pl.jbujak.ecommerse.catalog.Sales.cart.Cart;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.AcceptOfferRequest;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculaotr;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;
import pl.jbujak.ecommerse.catalog.Sales.reservation.Reservation;
import pl.jbujak.ecommerse.catalog.Sales.reservation.ReservationRepository;

import java.util.UUID;


public class SalesFacade {
    private inMemoryCartStorage cartStorage;
    private OfferCalculaotr Calculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(inMemoryCartStorage cartStorage , OfferCalculaotr calculator, PaymentGateway paymentGateway , ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.Calculator = calculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);
        return OfferCalculaotr.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(reservationId, customerId, acceptOfferRequest, offer ,paymentDetails);
        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId , paymentDetails.getPaymentUrl());
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }



    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
