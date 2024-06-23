package spring_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
//        // Создайте таблицу Account, если она не существует
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Account (" +
//                             "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
//                             "name VARCHAR(255)," +
//                             "email VARCHAR(255)," +
//                             "bill INTEGER)");

        // Вставка данных в таблицу Account
        jdbcTemplate.execute("INSERT INTO Account (id, name, email, bill) VALUES (1, 'Lori', 'lori@gmail.com', 2000)");

        // Извлечение данных из таблицы Account
        Map<String, Object> resultSet = jdbcTemplate.queryForMap("SELECT * FROM Account WHERE id = 1");
        System.out.println(resultSet.get("email"));

    }
}
