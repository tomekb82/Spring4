package pl.training.bank.service;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManagerFactory;

public class SequentialAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    private AtomicLong counter = new AtomicLong();

    public SequentialAccountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        restoreCounterState(entityManagerFactory);
    }

    private void restoreCounterState(EntityManagerFactory entityManagerFactory) {
        String lastAccountNumber = entityManagerFactory.createEntityManager()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER, String.class)
                .getSingleResult();
        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.valueOf(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
