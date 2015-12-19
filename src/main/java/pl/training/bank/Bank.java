package pl.training.bank;

import org.springframework.security.access.annotation.Secured;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;

public interface Bank {

    @Secured("ROLE_ADMIN")
    Account createAccount();

    void depositFundsIntoAccount(long funds, String accountNumber);

    void withdrawFundsFromAccount(long funds, String accountNumber);

    void transferFundsBetweenAccounts(long funds, String sourceAccountNumber, String destinationAccountNumber);

    void processOperation(Operation operation);
    
}
