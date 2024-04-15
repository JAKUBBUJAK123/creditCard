package pl.jbujak.ecommerse.catalog.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import  static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class SqlTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
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

}
