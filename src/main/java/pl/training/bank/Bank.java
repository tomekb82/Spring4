package pl.training.bank;

import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;

public interface Bank {

    Account createAccount();

    void depositFundsIntoAccount(long funds, String accountNumber);

    void withdrawFundsFromAccount(long funds, String accountNumber);

    void transferFundsBetweenAccounts(long funds, String sourceAccountNumber, String destinationAccountNumber);

    void processOperation(Operation operation);
    
}
