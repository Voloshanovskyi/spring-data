package spring_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring_data.entity.Account;
import spring_data.repository.AccountRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Autowired

    public SpringDataApplication(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        for (int i = 1; i < 11; i++) {
            accountRepository.save(new Account((long) i, "Lori" + i, "lori" + i + "@gmail.com", 2000 + i));
        }
        // Получаем и выводим все аккаунты
        System.out.println("All accounts:");
        accountRepository.findAll().forEach(System.out::println);

        // Получаем аккаунт по email и выводим его
        Account foundAccount = accountRepository.findByEmail("lori2@gmail.com");
        System.out.println("Found account by email: " + foundAccount);
        Account foundByName = accountRepository.findByName("Lori5");
        System.out.println("Found account by name: " + (foundByName != null ? foundByName : "Not found"));
    }
}
