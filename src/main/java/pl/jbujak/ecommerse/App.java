package pl.jbujak.ecommerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.jbujak.ecommerse.catalog.ProductCatalog;
import pl.jbujak.ecommerse.catalog.Sales.SalesFacade;
import pl.jbujak.ecommerse.catalog.Sales.cart.inMemoryCartStorage;
import pl.jbujak.ecommerse.catalog.Sales.offering.OfferCalculaotr;
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
    SalesFacade createSales() {
        return new SalesFacade(new inMemoryCartStorage(), new OfferCalculaotr());
    }
}
