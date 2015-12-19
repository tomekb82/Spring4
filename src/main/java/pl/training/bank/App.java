package pl.training.bank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.training.bank.config.BeansConfig;
import pl.training.bank.entity.Account;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansConfig.class);
        Bank bank = applicationContext.getBean(Bank.class);

        Account firstAccount = bank.createAccount();
        bank.depositFundsIntoAccount(1000, firstAccount.getNumber());
        bank.withdrawFundsFromAccount(10, firstAccount.getNumber());
        Account secondAccount = bank.createAccount();
        bank.transferFundsBetweenAccounts(10, firstAccount.getNumber(), secondAccount.getNumber());

        applicationContext.close();
    }

}
