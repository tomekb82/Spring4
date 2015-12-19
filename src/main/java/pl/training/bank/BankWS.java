package pl.training.bank;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;

@WebService(name = "Bank", portName = "Bank", serviceName = "BankService")
public class BankWS extends SpringBeanAutowiringSupport  {
    
    @Autowired
    private Bank bank;


    public Account createAccount() {
        return bank.createAccount();
    }


    public void depositFundsIntoAccount(long funds, String accountNumber) {
        bank.depositFundsIntoAccount(funds, accountNumber);
    }

    public void withdrawFundsFromAccount(long funds, String accountNumber) {
        bank.withdrawFundsFromAccount(funds, accountNumber);
    }


    public void transferFundsBetweenAccounts(long funds, String sourceAccountNumber, String destinationAccountNumber) {
        bank.transferFundsBetweenAccounts(funds, sourceAccountNumber, destinationAccountNumber);
    }


    public void processOperation(Operation operation) {
        bank.processOperation(operation);
    }
    
}
