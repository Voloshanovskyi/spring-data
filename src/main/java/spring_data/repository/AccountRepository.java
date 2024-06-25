package spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_data.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByEmail(String email);

    Account findAccountByName(String name);
    Account findAccountByNameAndBill(String name, Integer bill);
}
