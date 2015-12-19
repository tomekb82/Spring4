package pl.training.bank.service;

import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.SessionFactory;

public class SequentialAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    private AtomicLong counter = new AtomicLong();

    public SequentialAccountNumberGenerator(SessionFactory sessionFactory) {
        restoreCounterState(sessionFactory);
    }

    private void restoreCounterState(SessionFactory sessionFactory) {
        String lastAccountNumber = (String) sessionFactory.openSession()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER).uniqueResult();
        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.valueOf(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
