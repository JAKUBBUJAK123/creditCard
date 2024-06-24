package pl.jbujak.ecommerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.jbujak.ecommerse.catalog.ProductCatalog;
import pl.jbujak.ecommerse.catalog.Sales.Payment.PaymentGateway;
import pl.jbujak.ecommerse.catalog.payU.PayU;
import pl.jbujak.ecommerse.catalog.payU.PayUCredentials;
import pl.jbujak.ecommerse.catalog.payU.PayUGateway;
import pl.jbujak.ecommerse.catalog.Sales.SalesFacade;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculator;
import pl.jbujak.ecommerse.catalog.Sales.reservation.ReservationRepository;
import pl.jbujak.ecommerse.catalog.SqlProductStorage;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    @Autowired
    SqlProductStorage sqlProductStorage;
    public static void main(String[] args){
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class,args);
    }
    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(sqlProductStorage);
        catalog.setUpDatabase();
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(140));

        return catalog;

    }
    @Bean
    SalesFacade createSales(ProductCatalog catalog) {
        return new SalesFacade(new inMemoryCartStorage(), new OfferCalculator(catalog), createPaymentGateway() , new ReservationRepository());
    }
    @Bean
    PaymentGateway createPaymentGateway() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }
}
