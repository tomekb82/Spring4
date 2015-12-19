package pl.training.bank;

import org.springframework.transaction.annotation.Transactional;

import pl.training.bank.entity.Account;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

@Transactional()
public class SpringBank implements Bank {

    private AccountNumberGenerator accountNumberGenerator;
    private AccountsRepository accountsRepository;

    public SpringBank(AccountNumberGenerator accountNumberGenerator, AccountsRepository accountsRepository) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Account createAccount() {
        Account account = new Account(accountNumberGenerator.getNext());
        return accountsRepository.save(account);
    }

    @Override
    public void depositFundsIntoAccount(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        account.depositFunds(funds);
        accountsRepository.save(account);
    }

    @Override
    public void withdrawFundsFromAccount(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        account.confirmFunds(funds);
        account.withdrawFunds(funds);
        accountsRepository.save(account);
    }

    @Override
    public void transferFundsBetweenAccounts(long funds, String sourceAccountNumber, String destinationAccountNumber) {
        withdrawFundsFromAccount(funds, sourceAccountNumber);
        depositFundsIntoAccount(funds, destinationAccountNumber);
    }

}
