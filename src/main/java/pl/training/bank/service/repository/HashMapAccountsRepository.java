package pl.training.bank.service.repository;

import java.util.HashMap;
import java.util.Map;

import pl.training.bank.entity.Account;

public class HashMapAccountsRepository implements OldAccountsRepository {

    private long counter;
    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account save(Account account) {
        ensureIdForAccount(account);
        accounts.put(account.getNumber(), account);
        return account;
    }

    private void ensureIdForAccount(Account account) {
        if (account.getId() == null) {
            account.setId(++counter);
        }
    }

    @Override
    public void update(Account account) {
        checkIfAccountExists(account.getNumber());
        accounts.put(account.getNumber(), account);
    }

    @Override
    public Account getByNumber(String accountNumber) {
        checkIfAccountExists(accountNumber);
        return accounts.get(accountNumber);
    }

    private void checkIfAccountExists(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException();
        }
    }

}
