package pl.jbujak.ecommerse.catalog.Sales.ui;

import org.springframework.web.bind.annotation.*;
import pl.jbujak.ecommerse.catalog.Sales.SalesFacade;
import pl.jbujak.ecommerse.catalog.Sales.offering.AcceptOfferRequest;
import pl.jbujak.ecommerse.catalog.Sales.offering.Offer;
import pl.jbujak.ecommerse.catalog.Sales.order.ReservationDetails;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        String customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId,acceptOfferRequest);
    }
    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable(name = "productId") String productId){
        String customerId = getCurrentCustomerId();
        sales.addToCart(customerId,productId);
    }


    private String getCurrentCustomerId(){
        return "Kuba";
    }

}
