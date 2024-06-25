package spring_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import spring_data.entity.Account;
import spring_data.repository.AccountRepository;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {"spring_data.entity"})
public class SpringDataApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Autowired

    public SpringDataApplication(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) {
        for (int i = 1; i < 11; i++) {
            accountRepository.save(new Account((long) i, "Lori" + i, "lori" + i + "@gmail.com", 2000 + i));
        }
        // Получаем и выводим все аккаунты
        System.out.println("All accounts:");
        accountRepository.findAll().forEach(System.out::println);

        // Получаем аккаунт по email и выводим его
        Account foundAccount = accountRepository.findAccountByEmail("lori2@gmail.com");
        System.out.println("Found account by email: " + foundAccount);

        Account foundByName = accountRepository.findAccountByName("Lori5");
        System.out.println("Found account by name: " + (foundByName != null ? foundByName : "Not found"));

        accountRepository.setNameFor(7L, "Baxter");

        Account foundById = accountRepository.findAccountById(7L);
        Account updatedAccount = accountRepository.findById(7L).orElse(null);
        System.out.println("Found account by id: " + (foundById != null ? foundById : "Not found"));

        Account fondAccountByNameAndBill = accountRepository.findAccountByNameAndBill("Lori6", 2006);
        System.out.println("Found account by name and bill: " + (fondAccountByNameAndBill != null ? fondAccountByNameAndBill : "Not found"));
    }
}
