package pl.training.bank.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.training.bank.entity.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c join c.accounts a where a.balance >= :balance")
    List<Customer> getCustomerWithBalanceEqualOrGreater(long balance);

}
