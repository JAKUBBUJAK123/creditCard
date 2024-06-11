package pl.jbujak.ecommerse.catalog.Sales.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jbujak.ecommerse.catalog.Sales.SalesFacade;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.AcceptOfferRequest;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculator;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;

import  static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class OfferActeptanceTest {

    private ReservationRepository reservationRepository;
    private SpyPaymentGateway spyPaymentGateway;

    @BeforeEach
    void SetUp(){
        spyPaymentGateway = new SpyPaymentGateway();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void itAllowsToAcceptAnOffer() {
        SalesFacade sales = thereIsSales();
        String customerId  = thereIsCustomr("Kuba");
        String productId = thereIsProduct("X" , BigDecimal.valueOf(10));

        sales.addToCart(customerId,productId);
        sales.addToCart(customerId,productId);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest.setFirstname("john").setLastname("doe")
                .setEmail("john.doe@example.com");

        sales.acceptOffer(customerId,acceptOfferRequest);
        ReservationDetails reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();

        assertPaymentHasBeenRegistered();
        assertThereIsReservationWithId(reservationDetails.getReservationId());
        assertReservationIsPending(reservationDetails.getReservationId());
        assertReservationIsDoneForCustomer(reservationDetails.getReservationId() , "john" , "doe" , "john.doe@example.com");
        assertReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(20));
    }

    private void assertReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationRepository.load(reservationId).get();
        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void assertReservationIsDoneForCustomer(String reservationId, String fname, String lname, String mail) {
        Reservation loaded = reservationRepository.load(reservationId).get();
        CustomerDetails customerDetails = loaded.getCustomerDetails();
        assertThat(customerDetails.getFirstname()).isEqualTo(fname);
        assertThat(customerDetails.getLastname()).isEqualTo(lname);
        assertThat(customerDetails.getEmail()).isEqualTo(mail);
    }

    private void assertReservationIsPending(String reservationId) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();
        assertThat(loaded.isPending()).isTrue();
    }

    private void assertThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationRepository.load(reservationId);

        assertThat(loaded).isPresent();
    }

    private void assertPaymentHasBeenRegistered() {
        assertThat(spyPaymentGateway.getRequestCount()).isEqualTo(2);
    }

    private String thereIsProduct(String productId, BigDecimal price) {
        return productId;
    }

    private String thereIsCustomr(String id) {
        return id;
    }

    private SalesFacade thereIsSales() {
    return new SalesFacade(new inMemoryCartStorage(), new OfferCalculator() , spyPaymentGateway, reservationRepository);
        }

}
