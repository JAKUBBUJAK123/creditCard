package pl.jbujak.ecommerse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.jbujak.ecommerse.catalog.ProductCatalog;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class,args);
    }
    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog();
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(140));
        return catalog;
    }
}
