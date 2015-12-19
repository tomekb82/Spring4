package pl.training.bank.service;

import java.text.NumberFormat;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import pl.training.bank.BankException;

@Aspect
public class ConsoleOperationLogger {

    @Pointcut("execution(* pl.training.bank.Bank.*Funds*(..))")
    public void operations() {
    }

    private static final String SEPARATOR = "######################################################################";

    private String formatCurrency(long value) {
        return NumberFormat.getCurrencyInstance().format((double) value / 100);
    }

    @Before("execution(* pl.training.bank.Bank.depositFundsIntoAccount(..)) && args(funds,accountNumber)")
    public void beforeDepositFundsIntoAccount(long funds, String accountNumber) {
        System.out.format("%s\n%s <=== %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }

    @Before("execution(* pl.training.bank.Bank.withdrawFundsFromAccount(..)) && args(funds,accountNumber)")
    public void beforeWithdrawFundsFromAccount(long funds, String accountNumber) {
        System.out.format("%s\n%s ===> %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }

    @Before("execution(* pl.training.bank.Bank.transferFundsBetweenAccounts(..)) && args(funds,sourceAccountNumber,destinationAccountNumber)")
    public void beforeTransferFundsBetweenAccounts(long funds, String sourceAccountNumber, String destinationAccountNumber) {
        System.out.format("%s\n%s ===> %s ===> %s\n", SEPARATOR, sourceAccountNumber, formatCurrency(funds), destinationAccountNumber);
    }

    @AfterReturning("operations()")
    public void onSuccess() {
        System.out.format("Operation status: SUCCESS\n%s\n", SEPARATOR);
    }

    @AfterThrowing(value = "operations()", throwing = "ex")
    public void onFailure(BankException ex) {
        System.out.format("Operation status: FAILURE (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}
