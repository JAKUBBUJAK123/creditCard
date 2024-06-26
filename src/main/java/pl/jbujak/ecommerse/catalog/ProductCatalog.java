package pl.jbujak.ecommerse.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    SqlProductStorage productStorage;

    @Autowired
    public ProductCatalog(SqlProductStorage productStorage){
        this.productStorage = productStorage;
    }

    public void setUpDatabase(){
        productStorage.setUpDatabase();
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String description, BigDecimal price) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description,price);


        productStorage.add(newProduct);
        return newProduct.getId();
    }

    public void changePrice(String id, BigDecimal newPrice) {
        productStorage.changePrice(id, newPrice);
    }

    public Product getProductBy(String id) {
        return productStorage.getProductBy(id);
    }
}
