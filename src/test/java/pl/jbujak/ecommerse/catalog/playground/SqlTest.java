package pl.jbujak.ecommerse.catalog.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.jbujak.ecommerse.catalog.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import  static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class SqlTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUPDB(){
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS;");
        var createTableSql = """
                CREATE TABLE `product_catalog__products` (
                `id` VARCHAR(255) NOT NULL,
                `name` VARCHAR(100) NOT NULL,
                `price` DECIMAL(12,2),
                PRIMARY KEY(id)
                );
                """;
        jdbcTemplate.execute(createTableSql);
    }

    @Test
    void itSelectForCurrentState() {
        var myDate = jdbcTemplate.queryForObject("Select now() myCurrentDate"
        ,String.class);
        assertThat(myDate).contains("2024");
    };
    @Test
    void iAmAbleToCreateTable() {
        var sql = "Create table xyz ()";
    }

    @Test
    void itCreatesTable() {
        var countSQL = "select count(*) from `product_catalog__products`;";
        var results = jdbcTemplate.queryForObject(countSQL,Integer.class);
        assertThat(results).isEqualTo(0);
    }
    @Test
    void itStoresProducts() {
        var myInsertSQL = """
                INSERT INTO `product_catalog__products` (id, name, price)
                VALUES
                ('product_id_1', 'My lego set', 20.20),
                ('product_id_2', 'My cobi set', 10.20);
                """;
        var countSQL = "select count(*) from `product_catalog__products`;";
        jdbcTemplate.execute(myInsertSQL);
        var results = jdbcTemplate.queryForObject(countSQL,Integer.class);

        assertThat(results).isEqualTo(2);
    }
    @Test
    void itStoresDynamicProducts() {
        var product = new Product(UUID.randomUUID(),"Lego set", "Nice one", BigDecimal.valueOf(0));
        product.changePrice(BigDecimal.valueOf(10.10));
        var myInsertSQL = """
                INSERT INTO `product_catalog__products` (id, name, price)
                VALUES
                    (?, ?, ?)
                ;
                """;
        jdbcTemplate.update(myInsertSQL,product.getId(),product.getName(),product.getPrice());
        var countSQL = "select count(*) from `product_catalog__products`;";
        var results = jdbcTemplate.queryForObject(countSQL,Integer.class);

        assertThat(results).isEqualTo(1);
    }
    @Test
    void loadProductById() {
        var product = new Product(UUID.randomUUID(),"Lego set", "Nice one", BigDecimal.valueOf(0));
        product.changePrice(BigDecimal.valueOf(10.10));
        var myInsertSQL = """
                INSERT INTO `product_catalog__products` (id, name, price)
                VALUES
                    (?, ?, ?)
                ;
                """;
        jdbcTemplate.update(myInsertSQL,product.getId(),product.getName(),product.getPrice());

        var productId = product.getId();
        var query = "select * from `product_catalog__products` where id = ?";
        Product loadedProduct = jdbcTemplate.queryForObject(
                query,
                new Object[]{productId},
                (rs,i) -> {
                    var myProduct = new Product(
                            UUID.fromString(rs.getString("id")),
                            rs.getString(("name")),
                            rs.getString(("name")),
                            BigDecimal.valueOf(10.10)
                    );
                    myProduct.changePrice(BigDecimal.valueOf(rs.getDouble("price")));
                    return myProduct;
                }
        );
        assertThat(loadedProduct.getId()).isEqualTo(productId);
        assertThat(loadedProduct.getName()).isEqualTo("Lego set");
    }

    @Test
    void itLoadsAllProductsAtOnce() {
        var myInsertSQL = """
                INSERT INTO `product_catalog__products` (id, name, price)
                VALUES
                ('product_id_1', 'My lego set', 20.20),
                ('product_id_2', 'My cobi set', 10.20);
                """;
        jdbcTemplate.execute(myInsertSQL);
        List<Map<String,Object>> products = jdbcTemplate.queryForList("select * from `product_catalog__products`");
    }
}
