package spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring_data.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long accountId);
    Account findAccountByEmail(String email);

    Account findAccountByName(String name);
    @Query(nativeQuery = true, value = "select * from Account where name = :name and bill = :bill")
    Account findAccountByNameAndBill(@Param("name") String name, @Param("bill") Integer bill);

    @Modifying
    @Query("update Account a set a.name = :name where a.id = :id")
    int setNameFor(@Param("id") Long id, @Param("name") String name);
}
