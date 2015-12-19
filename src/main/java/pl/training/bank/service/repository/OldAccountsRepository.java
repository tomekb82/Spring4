package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

/**
 * Created by tomek on 19.12.15.
 */
public interface OldAccountsRepository {

    Account save(Account account);

    void update(Account account);

    Account getByNumber(String accountNumber);

}
