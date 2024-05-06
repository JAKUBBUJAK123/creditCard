package pl.jbujak.ecommerse.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@SpringBootTest
public class ProductCatalogTest {
    @Autowired
    SqlProductStorage sqlProductStorage;
    @Autowired
    ProductCatalog catalog;

    @BeforeEach
     void setUpDb(){
        ProductCatalog catalog = new ProductCatalog(sqlProductStorage);
        catalog.setUpDatabase();
    };
    @Test
    void isListAvailableProducts() {
        ProductCatalog catalog = new ProductCatalog(sqlProductStorage);
        List<Product> products = catalog.allProducts();
        assert products.isEmpty();
    }

    @Test
    void itAllowsToAddProducts() {
        ProductCatalog catalog = new ProductCatalog(sqlProductStorage);

        List<Product> products = catalog.allProducts();

        catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        catalog.allProducts();
        products = catalog.allProducts();

        assertThat(products)
                .hasSize(4);

    }

    @Test
    void itAllowsChangePrice() {
        ProductCatalog catalog = new ProductCatalog(sqlProductStorage);
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(50));

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

    @Test
    void itLoadsSingleProductById(){
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        String id2 = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));
        String id3 = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100));

        Product loaded = catalog.getProductBy(id);
        assertThat(id).isEqualTo(loaded.getId());
    }

}
