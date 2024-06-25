package spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_data.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Account findByName(String name);
}
